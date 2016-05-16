package com.ctrl.basic;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.biz.basic.PersonContractorBiz;
import com.common.BaseCtrl;
import com.po.basic.Person;
import com.po.basic.PersonContractor;
import com.sys.biz.ConstantDictBiz;
import com.sys.po.ConstantDict;
import com.sys.po.User;
import com.util.MicroOfficeFile;

@Controller
@RequestMapping("personContractor")
public class PersonContractorCtrl extends BaseCtrl<PersonContractorBiz, Integer, PersonContractor> {

	@Autowired
	private ConstantDictBiz constantDictBiz;
	
	public PersonContractorCtrl(){
		defaultPage = "backstage/personContractor";
	}
	
	/**后台个人用户管理页面
	 * */
	@RequestMapping("manage")
	public ModelAndView showManagePerson(){
		List<ConstantDict> auditState = constantDictBiz.findByConstantTypeCode("audit_state");
		List<ConstantDict> personState = constantDictBiz.findByConstantTypeCode("person_state");
		ModelAndView mav = new ModelAndView();
		mav.addObject("auditState", auditState);
		mav.addObject("personState", personState);
		mav.setViewName("backstage/person");
		return mav;
	}

	@RequestMapping("uploadExcel")
	@ResponseBody
	public Integer uploadExcel(@RequestParam("file")MultipartFile file,HttpSession httpSession){
		try{
			MicroOfficeFile mof = new MicroOfficeFile();
			Workbook wb = mof.readExcel(file);
			List<String[]> data = mof.getAllData(wb,0);
			User loginUser = (User)httpSession.getAttribute("loginUser");
			biz.batchSavePerson(data.subList(2, data.size()),1);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public PersonContractor update(PersonContractor personContractor,HttpSession httpSession){
		biz.update(personContractor);
		return personContractor;
	}
	
	/**根据搜索条件分页查询数据。searchText用于模糊匹配查询常量名称和常量类型名称。
	 * @param offset 偏移量，即记录索引位置
	 * @param pageSize 每页记录数
	 * @param constantName 要模糊查询的常量名称
	 * */
	/*@RequestMapping("findByPageAndParams")
	@ResponseBody
	public BootTablePageDto<ConstantDict> findByPageAndParams(int offset, int pageSize, String constantName){
		return biz.findByPageAndParams(offset,pageSize,constantName);
	}*/
}