package com.sys.ctrl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.biz.UserBiz;
import com.sys.po.User;

@Controller
@RequestMapping("login")
public class LoginCtrl {

	public static String loginUserKey = "loginUser";
	@Autowired
	private UserBiz userBiz;
	
	/**登录检测
	 * */
	@RequestMapping("loginCheck")
	@ResponseBody
	public Integer loginCheck(String userName, String password, HttpSession session){
		User user = userBiz.loginCheck(userName, password);
		if(user != null){
			session.setAttribute(loginUserKey, user);
			return 1;
		}else{
			return 0;
		}
	}
	
	/**登录成功后显示主界面
	 * */
	@RequestMapping("index")
	public ModelAndView showIndex(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("../index");
		return mav;
	}
	
	//退出
	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session){
		session.removeAttribute(loginUserKey);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:../login.jsp");
		return mav;
	}
}
