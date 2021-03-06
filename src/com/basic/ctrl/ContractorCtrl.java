package com.basic.ctrl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ad.biz.AdPositionBiz;
import com.ad.po.AdPosition;
import com.basic.biz.ContractorBiz;
import com.basic.biz.CostumeCategoryBiz;
import com.basic.biz.DistrictBiz;
import com.basic.dto.ContractorDto;
import com.basic.po.Contractor;
import com.basic.po.Person;
import com.basic.vo.ContractorHomeVo;
import com.basic.vo.ContractorSimpleVo;
import com.basic.vo.ContractorVo;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.sys.biz.ConstantDictBiz;
import com.sys.ctrl.UserCtrl;
import com.sys.po.ConstantDict;
import com.sys.po.User;
import com.util.DateTransform;
import com.util.FileUtil;
import com.util.JacksonJson;
import com.util.MicroOfficeFile;

@Controller
@RequestMapping("contractor")
public class ContractorCtrl extends BaseCtrl<ContractorBiz, Integer, Contractor> {

	@Autowired
	private ConstantDictBiz constantDictBiz;
	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	@Autowired
	private DistrictBiz districtBiz;
	@Autowired
	private AdPositionBiz adPositionBiz;
	
	private static final long imgMaxSize = 200000;//文档最大200kb
	
	public ContractorCtrl(){
		defaultPage = "main/contractorList";
	}
	
	public ModelAndView showDefaultPage(HttpSession session){
		ModelAndView mav = new ModelAndView(defaultPage);
		//推荐快产团队
		List<ContractorHomeVo> list = biz.getRecommend();
		mav.addObject("recommends",list);
		//广告位
		List<AdPosition> adPositions = adPositionBiz.getByCode("contractor_list");
		mav.addObject("adPositions", JacksonJson.beanToJson(adPositions));
		return mav;
	}
	
	/**后台快产专家管理页面
	 * */
	@RequestMapping("backstage")
	public ModelAndView showBackstageManage(){
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		ModelAndView mav = new ModelAndView();
		mav.addObject("processTypes", processTypes);
		mav.setViewName("backstage/contractor/contractor");
		return mav;
	}

	@RequestMapping("showDetail/{id}")
	public ModelAndView showDetail(@PathVariable int id){
		ModelAndView mav = new ModelAndView("main/contractorDetail");
		ContractorDto contractorDto = biz.getById(id);
		String[] codes = contractorDto.getContractor().getCostumeCode().split(",");
		ArrayList<Integer> codeAry = new ArrayList<Integer>(codes.length);
		for(int i=0;i<codes.length;i++){
			codeAry.add(Integer.parseInt(codes[i]));
		}
		List<String> costumeNames = costumeCategoryBiz.getNameByCode(codeAry);
		StringBuilder sb = new StringBuilder(costumeNames.get(0));
		for(int i=1;i<costumeNames.size();i++){
			sb.append(",").append(costumeNames.get(i));
		}
		contractorDto.getContractor().setCostumeCode(sb.toString());
		List<String> districts = districtBiz.getNameByUser(contractorDto.getPerson());
		sb = new StringBuilder();
		for(int i=0;i<districts.size();i++){
			sb.append(districts.get(i));
		}
		mav.addObject("contractorDto", contractorDto);
		mav.addObject("district", sb.toString());
		return mav;
	}
	
	@RequestMapping("uploadExcel")
	@ResponseBody
	public ReturnValueVo uploadExcel(@RequestParam("file")MultipartFile file,HttpSession session){
		try{
			MicroOfficeFile mof = new MicroOfficeFile();
			Workbook wb = mof.readExcel(file);
			List<String[]> data = mof.getAllData(wb,0);//data是从第二行读取的
			if(data.size() < 4){
				List<String> errorInfo = new ArrayList<String>();
				errorInfo.add("读取文件出错，文件内容可能有误");
				return new ReturnValueVo(ReturnValueVo.ERROR,errorInfo);
			}
			User loginUser = UserCtrl.getLoginUser(session);
			return biz.batchSaveContractor(data.subList(3, data.size()),loginUser.getId());
		}catch(Exception e){
			e.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION,"读取文件发生错误，请与管理员联系");
		}
	}
	
	/**新建只有后台管理人员用*/
	@RequestMapping("saveData")
	@ResponseBody
	public ReturnValueVo saveData(ContractorDto contractorDto,
			String costumeCode,
			@RequestParam(value="frontPhoto",required=false)MultipartFile frontPhoto,
			@RequestParam(value="backPhoto",required=false)MultipartFile backPhoto,
			HttpSession session){
		Person person = contractorDto.getPerson();
		Contractor contractor = contractorDto.getContractor();
		
		//检查是否登录
		Integer createBy = null;
		User user = UserCtrl.getLoginUser(session);
		if(user!=null)
			createBy = user.getId();
		else
			return new ReturnValueVo(ReturnValueVo.ERROR, "请先登录");

		//验证文件类型、大小是否符合
		String errorMsg = this.verifyImg(frontPhoto,backPhoto);
		if(errorMsg.length() > 0)
			return  new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/person/").getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		//转储图片
		try{
			if(frontPhoto != null){
				person.setIdFrontPhoto(this.transferFile(frontPhoto,uploadDir,"front"));
			}
			if(backPhoto != null){
				person.setIdBackPhoto(this.transferFile(backPhoto,uploadDir,"back"));
			}
		} catch (IllegalStateException | IOException ex) {
			ex.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "上传图片出错,请重试");
		}
		
		person.getBasicUser().setCreateBy(createBy);
		contractor.setCostumeCode(costumeCode);
		biz.save(person, contractor);
		return new ReturnValueVo(ReturnValueVo.SUCCESS,contractorDto);
	}
	
	@RequestMapping("updateData")
	@ResponseBody
	public ReturnValueVo updateData(ContractorDto contractorDto,
			String costumeCode,
			@RequestParam(value="frontPhoto",required=false)MultipartFile frontPhoto,
			@RequestParam(value="backPhoto",required=false)MultipartFile backPhoto,
			HttpSession session){
		Person person = contractorDto.getPerson();
		Contractor contractor = contractorDto.getContractor();
		
		//检查是否登录
		Integer createBy = null;
		User user = UserCtrl.getLoginUser(session);
		if(user!=null)
			createBy = user.getId();
		else
			return new ReturnValueVo(ReturnValueVo.ERROR, "请先登录");

		//验证文件类型、大小是否符合
		String errorMsg = this.verifyImg(frontPhoto,backPhoto);
		if(errorMsg.length() > 0)
			return  new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/person/").getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		//转储图片
		try{
			if(frontPhoto != null){
				//删除旧图片
				String front = person.getIdFrontPhoto();
				if(front!=null && front.length()!=0){
					FileUtil.delImg(uploadDir, front);
				}
				person.setIdFrontPhoto(this.transferFile(frontPhoto,uploadDir,"front"));
			}
			if(backPhoto != null){
				//删除旧图片
				String back = person.getIdBackPhoto();
				if(back!=null && back.length()!=0){
					FileUtil.delImg(uploadDir, back);
				}
				person.setIdBackPhoto(this.transferFile(backPhoto,uploadDir,"back"));
			}
		} catch (IllegalStateException | IOException ex) {
			ex.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "上传图片出错,请重试");
		}
		
		person.getBasicUser().setUpdateBy(createBy);
		contractor.setCostumeCode(costumeCode);
		biz.update(person, contractor);
		return new ReturnValueVo(ReturnValueVo.SUCCESS,contractorDto);
	}
	
	/**根据ID获取快产专家DTO，快产专家信息同时包括Person信息和自身信息*/
	@RequestMapping("getById/{id}")
	@ResponseBody
	public ContractorDto getById(@PathVariable int id){
		return biz.getById(id);
	}
	
	@RequestMapping("search")
	@ResponseBody
	public BootTablePageDto<ContractorSimpleVo> search(Long province,Long city,Long county,Long town, 
			Integer[] costumeCode,Byte processYear,int offset,Long total){
		int limit = 20;
		BootTablePageDto<ContractorSimpleVo> result = biz.search(province, city, county, town, costumeCode, processYear, offset, limit, total);
		return result;
	}
	
	/**根据搜索条件分页查询数据。
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * @param total 可为null
	 * */
	@RequestMapping("findByPage")
	@ResponseBody
	public BootTablePageDto<ContractorVo> findByPage(String userName,Long telephone,Byte auditState,String beginDate,String endDate,int offset, int limit, Long total){
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length()>0 && endDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		return biz.findByPage(userName,telephone,auditState,beginTime,endTime,offset,limit,total);
	}
	
	/**验证照片
	 * */
	private String verifyImg(MultipartFile frontPhoto, MultipartFile backPhoto){
		String errorMsg = "";
		String contentType = null;
		if(frontPhoto != null){
			contentType = frontPhoto.getContentType();
			if( frontPhoto.getSize()>imgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
				errorMsg = "身份证照(正面)不符合上传要求";
			}
		}
		if(backPhoto != null){
			contentType = backPhoto.getContentType();
			if( backPhoto.getSize()>imgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
				errorMsg += "身份证照(反面)不符合上传要求";
			}
		}
		return errorMsg;
	}
	
	/**保存文件到磁盘
	 * @param srcFile 原文件
	 * @param uploadDir 保存路径
	 * @return 返回新文件名
	 * @throws IOException 
	 * @throws IllegalStateException */
	private String transferFile(MultipartFile srcFile, String uploadDir, String newName) throws IllegalStateException, IOException{
		String suffix = null;
		String fileName = srcFile.getOriginalFilename();//获取上传文件的原名
		String ary[] = fileName.split("\\.");
		suffix = ary[ary.length-1];
		//通过transferTo()将文件存储到硬件中
		long time = System.currentTimeMillis();
		String newFileName = time+newName+"."+suffix;
		srcFile.transferTo(new File(uploadDir + newFileName));
		return newFileName;
	}
}