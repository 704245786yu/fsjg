package com.basic.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basic.biz.EnterpriseBiz;
import com.basic.vo.AuthEnterpriseVo;
import com.basic.vo.StrengthEnterpriseVo;

@Controller
public class MainCtrl {

	@Autowired
	private EnterpriseBiz enterpriseBiz;
	
	@RequestMapping({"/","home"})
	public String home(Model model){
		//实力工厂
		List<StrengthEnterpriseVo> strengths = enterpriseBiz.getStrength(6);
		model.addAttribute("strengths", strengths);
		//认证工厂
		List<AuthEnterpriseVo> auths = enterpriseBiz.getNewAuth(6,false);
		model.addAttribute("auths", auths);
		return "../home";
	} 
}
