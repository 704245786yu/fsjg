package com.ctrl;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biz.PersonBiz;
import com.po.Person;
import com.util.BaseCtrl;

@Controller
@RequestMapping("person")
public class PersonCtrl extends BaseCtrl<PersonBiz, Person> {

	public PersonCtrl(){
		defaultPage = "manage/person";
	}
	
	@RequestMapping("manage")
	public String showManagePerson(){
		return "manage/person";
	}
	
	@RequestMapping("getAllByPage")
	public List<Person> getAllByPage(int pageNo, int pageSize){
		return biz.getAllByPage(pageNo, pageSize);
	}
}