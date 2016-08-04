package com.basic.ctrl;

import java.io.UnsupportedEncodingException;
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
import com.basic.po.BasicUser;
import com.basic.po.District;
import com.basic.po.Enterprise;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.sys.biz.ConstantDictBiz;
import com.sys.ctrl.UserCtrl;
import com.sys.po.ConstantDict;
import com.sys.po.User;
import com.util.MicroOfficeFile;

@Controller
@RequestMapping("enterprise")
public class EnterpriseCtrl extends BaseCtrl<EnterpriseBiz,Integer,Enterprise>{

	@Autowired
	private ConstantDictBiz constantDictBiz;
	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	@Autowired
	private DistrictBiz districtBiz;
	
	public EnterpriseCtrl(){
		defaultPage = "main/enterprise";
	}
	
	public ModelAndView showDefaultPage(HttpSession session){
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(defaultPage);
		mav.addObject("processTypes", processTypes);
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
		return biz.getNewest();
	}
	
	/**最新认证加工厂*/
	@RequestMapping("getNewAuth")
	@ResponseBody
	public List<Enterprise> getNewAuth(){
		return biz.getNewAuth();
	}
	
	/**查找工厂*/
	@RequestMapping("search")
	public ModelAndView search(String enterpriseKeyword){
		BootTablePageDto<Enterprise> result = biz.search(enterpriseKeyword);
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
	
	@RequestMapping(value="search2",method=RequestMethod.POST)
	public ModelAndView search2(Long province,Long city,Long county,Long town, Integer[] costumeCode, String keyword){
		try {
			keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		keyword = keyword.trim();
		return null;
	}
	
	@RequestMapping("showDetail/{id}")
	public ModelAndView showDetail(@PathVariable int id){
		Enterprise e = biz.getById(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("enterprise", e);
		mav.setViewName("main/enterpriseDetail");
		return mav;
	}
	
	@RequestMapping("showList/{costumeCode}")
	public ModelAndView showList(@PathVariable int costumeCode){
		HashMap<Integer,String> costumeCategoryMap = costumeCategoryBiz.getAllCodeNameMap();
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		List<District> districts =	districtBiz.getProvinceAndCity();
		ModelAndView mav = new ModelAndView("main/enterpriseList");
		mav.addObject("costumeCategoryMap", costumeCategoryMap);
		mav.addObject("processTypes", processTypes);
		mav.addObject("districts", districts);
		BootTablePageDto<Enterprise> result = biz.getByCostumeCode(costumeCode);
		mav.addObject("result", result);
		return mav;
	}
}
