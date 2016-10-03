package com.sys.ctrl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sys.po.User;

@Controller
@RequestMapping("backstage")
public class BackstageCtrl {

	/**显示默认的页面*/
	@RequestMapping
	public ModelAndView showDefaultPage(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("../JSP/backstage/login");	//默认显示登录页面
		return mav;
	}
	
	/**登录成功后显示主界面
	 * */
	@RequestMapping("index")
	public ModelAndView showIndex(HttpSession session){
		User user = UserCtrl.getLoginUser(session);
		ModelAndView mav = new ModelAndView();
		if(user==null)
			mav.setViewName("redirect:../backstage");
		else
			mav.setViewName("../JSP/backstage/index");
		return mav;
	}
}
