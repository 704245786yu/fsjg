package com.sys.ctrl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.basic.biz.BasicUserBiz;
import com.basic.biz.EnterpriseBiz;
import com.basic.po.BasicUser;
import com.sys.biz.UserBiz;
import com.sys.po.User;

@Controller
@RequestMapping("login")
public class LoginCtrl {

	public static final String loginMngUser = "loginMngUser";
	public static final String loginBasicUser = "loginBasicUser";
	
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private BasicUserBiz basicUserBiz;
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	
	/**登录检测
	 * */
	@RequestMapping(value="loginCheck", method=RequestMethod.POST)
	@ResponseBody
	public Integer loginCheck(String userName, String password, HttpSession session){
		User user = userBiz.loginCheck(userName, password);
		if(user != null){
			session.setAttribute(loginMngUser, user);
			return 1;
		}else{
			return 0;
		}
	}
	
	@RequestMapping(value="showSignUp")
	public String showSignUp(){
		return "signUp";
	}
	
	/**注册*/
	@RequestMapping(value="signUp", method=RequestMethod.POST)
	public ModelAndView signUp(BasicUser basicUser, String enterpriseName,RedirectAttributes attr){
		//此处有乱码问题
//		System.out.println(enterpriseName);
		String errorMsg = basicUserBiz.signUp(basicUser, enterpriseName);
		ModelAndView mav = new ModelAndView();
		if(basicUser == null){
			mav.setViewName("redirect:showSignUp");
			mav.addObject("errorMsg", errorMsg);
		}else{
			mav.setViewName("redirect:../login.jsp");
			attr.addAttribute("signUpStatus", 200);
		}
		return mav;
	}
	
	/**校验用户名是否存在*/
	@RequestMapping(value="nameIsExist")
	@ResponseBody
	public String nameIsExist(String userName){
		if(basicUserBiz.nameIsExist(userName)){
			return  "{\"valid\":false}";
		}else{
			return  "{\"valid\":true}";
		}
	}
	
	/**校验手机号是否存在*/
	@RequestMapping(value="teleIsExist")
	@ResponseBody
	public String teleIsExist(Long telephone){
		if(basicUserBiz.teleIsExist(telephone)){
			return  "{\"valid\":false}";
		}else{
			return  "{\"valid\":true}";
		}
	}
	
	/**校验企业名称是否存在*/
	@RequestMapping(value="enterpriseIsExist")
	@ResponseBody
	public String enterpriseIsExist(String enterpriseName){
		if(enterpriseBiz.isExsit(enterpriseName)){
			return  "{\"valid\":false}";
		}else{
			return  "{\"valid\":true}";
		}
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ModelAndView login(String userName, String password, HttpSession session){
		BasicUser basicUser = basicUserBiz.login(userName, password);
		ModelAndView mav = new ModelAndView();
		if(basicUser == null){
			mav.setViewName("../login");
			mav.addObject("errorMsg", "用户名或密码错误");
		}else{
			session.setAttribute(loginBasicUser, basicUser);
//			mav.setViewName("../home");//此种方式会导致显示home.jsp页面，刷新时登录表单会重复提交
			mav.setViewName("redirect:../home.jsp");
		}
		return mav;
	}
	
	//退出
	@RequestMapping("mngLogout")
	public ModelAndView mngLogout(HttpSession session){
		session.removeAttribute(loginMngUser);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:../login.jsp");
		return mav;
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session){
		session.removeAttribute(loginBasicUser);
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:../login.jsp");
		return mav;
	}
}
