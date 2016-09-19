package com.basic.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.util.JacksonJson;
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
		JacksonJson.printBeanToJson(e);
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
			return  new ReturnValueVo(ReturnValueVo.ERROR, "请先登录");
		}
		
		//验证文件类型、大小是否符合
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
		if(errorMsg.length() > 0)
			return  new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		String fileName = logoImg.getOriginalFilename();//获取上传文件的原名
		String ary[] = fileName.split("\\.");
		String suffix = ary[ary.length-1];
		return new ReturnValueVo(ReturnValueVo.ERROR, "我草");
		//通过transferTo()将文件存储到硬件中
		/*try {
			String uploadDir = session.getServletContext().getInitParameter("uploadDir");
			Date date = new Date();
			String newFileName = basicUser.getId()+""+date.getTime()+"."+suffix;
			file.transferTo(new File(uploadDir + newFileName));
			return new ReturnValueVo(ReturnValueVo.SUCCESS, newFileName);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, null);
		}*/
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
}
