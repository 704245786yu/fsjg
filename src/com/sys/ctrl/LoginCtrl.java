package com.sys.ctrl;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.basic.biz.BasicUserBiz;
import com.basic.biz.EnterpriseBiz;
import com.basic.po.BasicUser;
import com.common.vo.ReturnValueVo;
import com.common.vo.ValidVo;
import com.sys.biz.UserBiz;
import com.sys.po.User;
import com.util.SMS;

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
	
	/**发送短信验证码
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping("getSmsNum/{telephone}")
	@ResponseBody
	public ReturnValueVo getSmsNum(@PathVariable long telephone, HttpSession session){
		int num = (int)(Math.random()*(9999-1000+1))+1000;//产生1000-9999的随机数
		HashMap<String,LinkedHashMap<String,Object>> map = SMS.sendNum(telephone,"SMS_14761073",num);
		LinkedHashMap<String,Object> map2 = (LinkedHashMap<String,Object>)map.get("alibaba_aliqin_fc_sms_num_send_response").get("result");
		if((boolean)map2.get("success")){
			//发送成功后保存验证码和当前时间到session，用于调用注册方法时进行验证码和时间的比对
			HashMap<String,Long> sms = new HashMap<String,Long>();
			sms.put("smsNum", (long)num);
			sms.put("generateTime", System.currentTimeMillis());
			session.setAttribute("sms",sms);
			return new ReturnValueVo(ReturnValueVo.SUCCESS,null);
		}else{
			System.out.println("发送验证码错误："+map);
			return new ReturnValueVo(ReturnValueVo.ERROR,null);
		}
	}
	
	/**忘记密码-发送短信验证码
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping("getSmsNumOfPwd/{telephone}")
	@ResponseBody
	public ReturnValueVo getSmsNumOfPwd(@PathVariable long telephone, HttpSession session){
		int num = (int)(Math.random()*(9999-1000+1))+1000;//产生1000-9999的随机数
		HashMap<String,LinkedHashMap<String,Object>> map = SMS.sendNum(telephone, "SMS_41655206", num);
		LinkedHashMap<String,Object> map2 = (LinkedHashMap<String,Object>)map.get("alibaba_aliqin_fc_sms_num_send_response").get("result");
		if((boolean)map2.get("success")){
			//发送成功后保存验证码和当前时间到session，用于调用注册方法时进行验证码和时间的比对
			HashMap<String,Long> sms = new HashMap<String,Long>();
			sms.put("smsNum", (long)num);
			sms.put("generateTime", System.currentTimeMillis());
			session.setAttribute("sms",sms);
			return new ReturnValueVo(ReturnValueVo.SUCCESS,null);
		}else{
			System.out.println("发送验证码错误："+map);
			return new ReturnValueVo(ReturnValueVo.ERROR,null);
		}
	}
	
	/**注册
	 * @param smsNum 短信验证码
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="signUp", method=RequestMethod.POST)
	public ModelAndView signUp(BasicUser basicUser, long smsNum, String enterpriseName, RedirectAttributes attr, HttpSession session){
		String redirectSignUp = "redirect:../JSP/signUp.jsp";
		ModelAndView mav = new ModelAndView();
		mav.addObject("userType", basicUser.getRoleId());
		String errorMsg = null;
		//判断验证码是否正确且有效
		HashMap<String,Long> sms = (HashMap<String,Long>)session.getAttribute("sms");
		if(sms==null){
			errorMsg = "请先获取验证码";
			mav.setViewName(redirectSignUp);
			mav.addObject("errorMsg", errorMsg);
			return mav;
		}
		long now = System.currentTimeMillis();
		long generateTime = sms.get("generateTime");
		long generateSmsNum = sms.get("smsNum");	//session里保存的验证码
		if((now-generateTime)>60000){	//60秒有效
			errorMsg = "验证码已失效请重新获取";
			mav.setViewName(redirectSignUp);
			mav.addObject("errorMsg", errorMsg);
			return mav;
		}else if(generateSmsNum != smsNum){
			errorMsg = "验证码错误";
			mav.setViewName(redirectSignUp);
			mav.addObject("errorMsg", errorMsg);
			return mav;
		}

		basicUser.setState((byte)0);
		basicUser.setCreateBy(0);
		basicUser.setUpdateBy(0);
		errorMsg = basicUserBiz.signUp(basicUser, enterpriseName);
		if(errorMsg != null){
			mav.setViewName(redirectSignUp);
			mav.addObject("errorMsg", errorMsg);
		}else{
			mav.setViewName("redirect:../login.jsp");
			attr.addAttribute("signUpStatus", 200);
		}
		return mav;
	}
	
	/**校验用户名是否存在
	 * @param id 判断是否存在时排除id值
	 * */
	@RequestMapping(value="nameIsExist")
	@ResponseBody
	public ValidVo nameIsExist(String userName, @RequestParam(required=false)Integer id){
		return new ValidVo( ! basicUserBiz.nameIsExist(userName,id) );
	}
	
	/**校验手机号是否存在*/
	@RequestMapping(value="teleIsExist")
	@ResponseBody
	public ValidVo teleIsExist(Long telephone, @RequestParam(required=false)Integer id){
		return new ValidVo( ! basicUserBiz.teleIsExist(telephone,id) );
	}
	
	/**校验企业名称是否存在*/
	@RequestMapping(value="enterpriseIsExist")
	@ResponseBody
	public ValidVo enterpriseIsExist(String enterpriseName, @RequestParam(required=false)Integer id){
		return new ValidVo( ! enterpriseBiz.isExsit(enterpriseName,id));
	}
	
	//普通用户登录
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ModelAndView login(String userName, String password, HttpSession session){
		BasicUser basicUser = basicUserBiz.login(userName, password);
		ModelAndView mav = new ModelAndView();
		Integer one = new Integer(1);
		if(basicUser == null){
			mav.setViewName("../login");
			mav.addObject("errorMsg", "用户名或密码错误");
		}else if(one.equals(basicUser.getState())){
			mav.setViewName("../login");
			mav.addObject("errorMsg", "您的帐号已冻结请联系客服");
		}else{
			session.setAttribute(loginBasicUser, basicUser);
//			mav.setViewName("../home");//此种方式会导致显示home.jsp页面，刷新时登录表单会重复提交
			mav.setViewName("redirect:../home");
		}
		return mav;
	}
	
	/**普通用户异步登录,弹出式登录表单用
	 * */
	@RequestMapping(value="asyLogin", method=RequestMethod.POST)
	@ResponseBody
	public boolean asyLogin(String userName, String password, HttpSession session){
		BasicUser basicUser = basicUserBiz.login(userName, password);
		if(basicUser == null || basicUser.getState() == 1){
			return false;
		}else{
			session.setAttribute(loginBasicUser, basicUser);
			return true;
		}
	}
	
	
	/**管理员登录检测
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
	
	//后台管理退出
	@RequestMapping("mngLogout")
	public ModelAndView mngLogout(HttpSession session){
		session.removeAttribute(loginMngUser);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:../backstage");
		return mav;
	}
	
	//普通用户退出
	@RequestMapping("logout")
	public ModelAndView logout(HttpSession session){
		session.removeAttribute(loginBasicUser);
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:../login.jsp");
		return mav;
	}
}
