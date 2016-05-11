package com.ctrl.sys;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.biz.sys.RoleBiz;
import com.biz.sys.UserBiz;
import com.common.BaseCtrl;
import com.po.sys.Role;
import com.po.sys.User;

@Controller
@RequestMapping("user")
public class UserCtrl extends BaseCtrl<UserBiz, Integer, User> {

	@Autowired
	private RoleBiz roleBiz;
	
	public UserCtrl() {
		defaultPage = "sys/user";
	}

	/**显示默认的页面*/
	@Override
	public ModelAndView showDefaultPage() {
		ModelAndView mav = new ModelAndView();
		List<Role> roles = roleBiz.getAll();
		mav.addObject("roles", roles);
		mav.setViewName(defaultPage);
		return mav;
	}
	
	@RequestMapping("saveUser")
	@ResponseBody
	public User saveUser(User user, HttpSession session) {
		Date date = new Date();
		user.setUpdateTime(date);
		return super.save(user);

	}
	

	@RequestMapping("updateUser")
	@ResponseBody
	public User update(User user, HttpSession session) {
		Date date = new Date();
		user.setUpdateTime(date);
		biz.update(user);
		return user;
	}
}
