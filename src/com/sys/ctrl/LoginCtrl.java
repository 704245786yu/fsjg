package com.sys.ctrl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.biz.BasicUserBiz;
import com.basic.biz.EnterpriseBiz;
import com.basic.po.BasicUser;
import com.common.vo.ReturnValueVo;
import com.sys.biz.UserBiz;
import com.sys.po.User;

@Controller
@RequestMapping("login")
public class LoginCtrl {

	public static String loginUserKey = "loginUser";
	
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
			session.setAttribute(loginUserKey, user);
			return 1;
		}else{
			return 0;
		}
	}
	
	/**注册*/
	@RequestMapping(value="signUp", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValueVo signUp(BasicUser basicUser, String enterpriseName){
		return basicUserBiz.signUp(basicUser, enterpriseName);
	}
	
	/**校验用户名是否存在*/
	@RequestMapping(value="nameIsExist", method=RequestMethod.POST)
	@ResponseBody
	public boolean nameIsExist(String userName){
		return basicUserBiz.nameIsExist(userName);
	}
	
	/**校验手机号是否存在*/
	@RequestMapping(value="teleIsExist", method=RequestMethod.POST)
	@ResponseBody
	public boolean teleIsExist(Long telephone){
		return basicUserBiz.teleIsExist(telephone);
	}
	
	/**校验企业名称是否存在*/
	@RequestMapping(value="enterpriseIsExist", method=RequestMethod.POST)
	@ResponseBody
	public boolean enterpriseIsExist(String enterpriseName){
		return enterpriseBiz.isExsit(enterpriseName);
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
