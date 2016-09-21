package com.basic.ctrl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.basic.biz.CostumeCategoryBiz;
import com.basic.biz.DistrictBiz;
import com.basic.biz.EnterpriseBiz;
import com.basic.biz.EnterpriseCostumeRelaBiz;
import com.basic.po.BasicUser;
import com.basic.po.District;
import com.basic.po.Enterprise;
import com.basic.vo.AuthEnterpriseVo;
import com.basic.vo.StrengthEnterpriseVo;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.sys.biz.ConstantDictBiz;
import com.sys.ctrl.UserCtrl;
import com.sys.po.ConstantDict;
import com.sys.po.User;
import com.util.FileUtil;
import com.util.MicroOfficeFile;

@Controller
@RequestMapping("enterprise")
public class EnterpriseCtrl extends BaseCtrl<EnterpriseBiz,Integer,Enterprise>{

	@Autowired
	private ConstantDictBiz constantDictBiz;
	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	@Autowired
	private EnterpriseCostumeRelaBiz enterpriseCostumeRelaBiz;
	@Autowired
	private DistrictBiz districtBiz;
	
	private static final long logoImgMaxSize = 50000;//logo图片最大50kb
	private static final long imgMaxSize = 200000;//文档最大200kb
	
	public EnterpriseCtrl(){
		defaultPage = "main/enterprise";
	}
	
	public ModelAndView showDefaultPage(HttpSession session){
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
//		HashMap<Integer,String> costumeCategoryMap = costumeCategoryBiz.getAllCodeNameMap();
		//实力工厂
		List<StrengthEnterpriseVo> enterprises = biz.getStrength(12);
		
		ModelAndView mav = new ModelAndView(defaultPage);
		mav.addObject("enterprises",enterprises);
		mav.addObject("processTypes", processTypes);
//		mav.addObject("costumeCategoryMap", costumeCategoryMap);
		return mav;
	}
	
	/**显示后台管理页面*/
	@RequestMapping("showManage")
	public ModelAndView showManage(){
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backstage/enterprise/enterprise");
		mav.addObject("processTypes", processTypes);
		return mav;
	}
	
	/**批量导入工厂信息
	 * */
	@RequestMapping("uploadExcel")
	@ResponseBody
	public ReturnValueVo uploadExcel(@RequestParam("file")MultipartFile file,HttpSession session){
		try{
			MicroOfficeFile mof = new MicroOfficeFile();
			Workbook wb = mof.readExcel(file);
			List<String[]> data = mof.getAllData(wb,0);
			if(data.size() < 4){
				List<String> errorInfo = new ArrayList<String>();
				errorInfo.add("读取文件出错，文件内容可能有误");
				return new ReturnValueVo(ReturnValueVo.ERROR,errorInfo);
			}
			User loginUser = UserCtrl.getLoginUser(session);
		    return biz.batchSaveEnterprise(data.subList(3, data.size()),loginUser.getId());
		}catch(Exception e){
			e.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION,"读取文件发生错误，请与管理员联系");
		}
	}
	
	@Override
	public Enterprise update(Enterprise enterprise, HttpSession session){
		User user = (User)UserCtrl.getLoginUser(session);
		BasicUser basicUser = enterprise.getBasicUser();
		basicUser.setUpdateBy(user.getId());
		biz.update(enterprise);
		return enterprise;
	}
	
	/**获取加工企业的主营产品类型*/
	@RequestMapping("getCostumeCode")
	@ResponseBody
	public List<Integer> getCostumeCode(int enterpriseId){
		return biz.getCostumeCode(enterpriseId);
	}
	
	/**获取优秀企业*/
	@RequestMapping("getExcellent")
	@ResponseBody
	public List<Enterprise> getExcellent(){
		return biz.getExcellent();
	}
	
	/**最新入住的企业*/
	@RequestMapping("getNewest")
	@ResponseBody
	public List<Enterprise> getNewest(){
		return biz.getNewest(6);//获取六个工厂
	}
	
	/**最新认证加工厂*/
	@RequestMapping("getNewAuth")
	@ResponseBody
	public List<AuthEnterpriseVo> getNewAuth(){
		return biz.getNewAuth(6,true);//获取六个工厂
	}
	
	/**页面顶部的全局搜索：搜索工厂*/
	@RequestMapping("search")
	public ModelAndView search(String enterpriseKeyword){
		BootTablePageDto<Enterprise> result = biz.search(enterpriseKeyword);
		HashMap<Integer,String> costumeCategoryMap = costumeCategoryBiz.getAllCodeNameMap();
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		List<District> districts =	districtBiz.getProvinceAndCity();
		
		ModelAndView mav = new ModelAndView("main/enterpriseList");
		mav.addObject("result", result);
		mav.addObject("costumeCategoryMap", costumeCategoryMap);
		mav.addObject("processTypes", processTypes);
		mav.addObject("districts", districts);
		
		//保留页面顶部搜索框的状态
		mav.addObject("tabIndex",1);
		mav.addObject("enterpriseKeyword",enterpriseKeyword);
		return mav;
	}
	
	@RequestMapping(value="search2",method=RequestMethod.POST)
	public ModelAndView search2(Long province,Long city,Long county,Long town, Integer[] costumeCode, String keyword){
		BootTablePageDto<Enterprise> result = biz.search2(province,city,county,town,costumeCode,keyword);
		HashMap<Integer,String> costumeCategoryMap = costumeCategoryBiz.getAllCodeNameMap();
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		List<District> districts =	districtBiz.getProvinceAndCity();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.addObject("costumeCategoryMap", costumeCategoryMap);
		mav.addObject("processTypes", processTypes);
		mav.addObject("districts", districts);
		mav.setViewName("main/enterpriseList");
		return mav;
	}
	
	@RequestMapping(value="search3")
	@ResponseBody
	public BootTablePageDto<Enterprise> search3(Integer costumeCode, Long province, Long city, Long county, Long town, Integer processType, Integer staffNumber){
		return biz.search3(costumeCode, province, city, county, town, processType, staffNumber);
	}
	
	@RequestMapping("showDetail/{id}")
	public ModelAndView showDetail(@PathVariable int id){
		Enterprise e = biz.getById(id);
		List<String> costumeNames = enterpriseCostumeRelaBiz.getCostumeNameByEnterpriseId(e.getId());
		ModelAndView mav = new ModelAndView();
		mav.addObject("enterprise", e);
		mav.addObject("costumeNames", costumeNames);
		mav.setViewName("main/enterpriseDetail");
		return mav;
	}
	
	@RequestMapping("showList/{costumeCode}")
	public ModelAndView showList(@PathVariable int costumeCode){
		HashMap<Integer,String> costumeCategoryMap = costumeCategoryBiz.getAllCodeNameMap();
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		List<District> districts = districtBiz.getProvinceAndCity();
		ModelAndView mav = new ModelAndView("main/enterpriseList");
		mav.addObject("costumeCategoryMap", costumeCategoryMap);
		mav.addObject("processTypes", processTypes);
		mav.addObject("districts", districts);
		BootTablePageDto<Enterprise> result = biz.getByCostumeCode(costumeCode);
		mav.addObject("result", result);
		return mav;
	}
	
	/**@param enterprisePic 最多选择6张图片
	 * */
	@RequestMapping("saveEnterprise")
	@ResponseBody
	public ReturnValueVo saveEnterprise(
			Enterprise e,
			@RequestParam(value="logoImg",required=false)MultipartFile logoImg,
			@RequestParam(value="licensePic",required=false)MultipartFile licensePic,
			@RequestParam(value="enterprisePic",required=false)MultipartFile[] enterprisePic,
			HttpSession session){
		//检查是否登录,判断操作用户是管理员还是普通用户自己
		Integer createBy = null;
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser != null){
			createBy = basicUser.getId();
		}else{
			User user = UserCtrl.getLoginUser(session);
			if(user!=null)
				createBy = user.getId();
		}
		if(createBy==null){
			return new ReturnValueVo(ReturnValueVo.ERROR, "请先登录");
		}
		e.getBasicUser().setCreateBy(createBy);
		
		//验证文件类型、大小是否符合
		String errorMsg = this.verifyImg(logoImg,licensePic,enterprisePic);
		if(errorMsg.length() > 0)
			return  new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		//转储图片
		//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/enterprise/").getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
//		String uploadDir = session.getServletContext().getInitParameter("uploadDir/enterprise");
		try{
			if(logoImg != null)
				e.setLogo(this.transferFile(logoImg,uploadDir,createBy,"logo"));
			else
				e.setLogo("default_logo.png");//设置默认图片
			
			if(licensePic != null)
				e.setLicenseImg(this.transferFile(licensePic,uploadDir,createBy,"lic"));
			
			if(enterprisePic.length > 0){
				String enterpriseImg = null;
				enterpriseImg = this.transferFile(enterprisePic[0],uploadDir,createBy,"pic");
				for(int i=1;i<enterprisePic.length;i++){
					enterpriseImg +=  ","+this.transferFile(enterprisePic[i],uploadDir,createBy,"pic");
				}
				e.setEnterpriseImg(enterpriseImg);
			}
		} catch (IllegalStateException | IOException ex) {
			ex.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "上传图片出错,请重试");
		}
		biz.save(e);
		return new ReturnValueVo(ReturnValueVo.SUCCESS, e);
	}
	
	/**@param enterprisePic 最多选择6张图片
	 * */
	@RequestMapping("updateEnterprise")
	@ResponseBody
	public ReturnValueVo updateEnterprise(
			Enterprise e,
			String[] delImg,
			@RequestParam(value="logoImg",required=false)MultipartFile logoImg,
			@RequestParam(value="licensePic",required=false)MultipartFile licensePic,
			@RequestParam(value="enterprisePic",required=false)MultipartFile[] enterprisePic,
			HttpSession session){
		//检查是否登录,判断操作用户是管理员还是普通用户自己
		Integer updateBy = null;
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser != null){
			updateBy = basicUser.getId();
		}else{
			User user = UserCtrl.getLoginUser(session);
			if(user!=null)
				updateBy = user.getId();
		}
		if(updateBy==null){
			return  new ReturnValueVo(ReturnValueVo.ERROR, "请先登录");
		}
		e.getBasicUser().setUpdateBy(updateBy);
		
		//验证文件类型、大小是否符合
		String errorMsg = this.verifyImg(logoImg,licensePic,enterprisePic);
		if(errorMsg.length() > 0)
			return  new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/enterprise/").getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		//删除旧图片
		if(delImg!=null)
			FileUtil.delImg(uploadDir, delImg);
		
		for(int i=0; i<delImg.length; i++){
			if(delImg[i].indexOf("logo") != -1){
				e.setLogo("default_logo.png");
			}
		}
		
		//转储图片
		try{
			if(logoImg != null)
				e.setLogo(this.transferFile(logoImg,uploadDir,updateBy,"logo"));
			
			if(licensePic != null)
				e.setLicenseImg(this.transferFile(licensePic,uploadDir,updateBy,"lic"));
			
			if(enterprisePic.length > 0){
				String enterpriseImg = null;
				enterpriseImg = this.transferFile(enterprisePic[0],uploadDir,updateBy,"pic");
				for(int i=1;i<enterprisePic.length;i++){
					enterpriseImg +=  ","+this.transferFile(enterprisePic[i],uploadDir,updateBy,"pic");
				}
				e.setEnterpriseImg(e.getEnterpriseImg()+enterpriseImg);
			}
		} catch (IllegalStateException | IOException ex) {
			ex.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "上传图片出错,请重试");
		}
		biz.update(e);
		return new ReturnValueVo(ReturnValueVo.SUCCESS, e);
	}
	
	@Override
	public Integer delete(Integer id, HttpSession session) {
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/enterprise/").getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		try{
			biz.deleteById(id, uploadDir);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	/**保存文件到磁盘
	 * @param srcFile 原文件
	 * @param uploadDir 保存路径
	 * @param createBy 创建人ID
	 * @return 返回新文件名
	 * @throws IOException 
	 * @throws IllegalStateException */
	private String transferFile(MultipartFile srcFile, String uploadDir, int createBy, String newName) throws IllegalStateException, IOException{
		String suffix = null;
		String fileName = srcFile.getOriginalFilename();//获取上传文件的原名
		String ary[] = fileName.split("\\.");
		suffix = ary[ary.length-1];
		//通过transferTo()将文件存储到硬件中
		long time = System.currentTimeMillis();
		String newFileName = createBy+""+time+newName+"."+suffix;
		srcFile.transferTo(new File(uploadDir + newFileName));
		return newFileName;
	}
	
	/**验证图片
	 * */
	private String verifyImg(MultipartFile logoImg,MultipartFile licensePic,MultipartFile[] enterprisePic){
		String errorMsg = "";
		String contentType = null;
		if(logoImg != null){
			contentType = logoImg.getContentType();
			if( logoImg.getSize()>logoImgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
				errorMsg = "工厂logo不符合上传要求";
			}
		}
		if(licensePic != null){
			contentType = licensePic.getContentType();
			if( licensePic.getSize()>imgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
				errorMsg += "营业执照不符合上传要求";
			}
		}
		String enterprisePicError = "";
		for(int i=0;i<enterprisePic.length;i++){
			contentType = enterprisePic[i].getContentType();
			if( licensePic.getSize()>imgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
				enterprisePicError = "工厂照片不符合上传要求";
			}
		}
		errorMsg += enterprisePicError;
		return errorMsg;
	}
	
}
