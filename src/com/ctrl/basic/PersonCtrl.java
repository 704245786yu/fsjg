package com.ctrl.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.biz.basic.PersonBiz;
import com.biz.sys.ConstantDictBiz;
import com.common.BaseCtrl;
import com.po.basic.Person;
import com.po.sys.ConstantDict;

@Controller
@RequestMapping("person")
public class PersonCtrl extends BaseCtrl<PersonBiz, Integer, Person> {

	@Autowired
	private ConstantDictBiz constantDictBiz;
	
	public PersonCtrl(){
		defaultPage = "backstage/person";
	}
	
	/**后台个人用户管理页面
	 * */
	@RequestMapping("manage")
	public ModelAndView showManagePerson(){
		List<ConstantDict> genders = constantDictBiz.findByConstantTypeCode("gender");
		List<ConstantDict> auditStates = constantDictBiz.findByConstantTypeCode("audit_state");
		ModelAndView mav = new ModelAndView();
		mav.addObject("genders", genders);
		mav.addObject("auditStates", auditStates);
		mav.setViewName("backstage/person");
		return mav;
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