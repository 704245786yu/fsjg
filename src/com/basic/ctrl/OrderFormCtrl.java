package com.basic.ctrl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sys.biz.ConstantDictBiz;
import com.sys.po.ConstantDict;

@Controller
@RequestMapping("orderForm")
public class OrderFormCtrl {

	@Autowired
	private ConstantDictBiz constantDictBiz;
	
//	public OrderFormCtrl(){
//		
//	}
	
	@RequestMapping
	public ModelAndView showDefaultPage(HttpSession session){
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/orderForm");
		mav.addObject("processTypes", processTypes);
		return mav;
	}
}
