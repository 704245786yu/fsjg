package com.sys.ctrl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.BaseCtrl;
import com.dto.BootTablePageDto;
import com.sys.biz.RoleBiz;
import com.sys.biz.UserBiz;
import com.sys.po.Role;
import com.sys.po.User;

@Controller
@RequestMapping("user")
public class UserCtrl extends BaseCtrl<UserBiz, Integer, User> {

	@Autowired
	private RoleBiz roleBiz;

	public UserCtrl() {
		defaultPage = "sys/user";
	}

	/**获取当前登录用户*/
	public static User getLoginUser(HttpSession session){
		return (User)session.getAttribute(LoginCtrl.loginUserKey);
	}
	
	/**显示默认的页面*/
	@Override
	@RequestMapping
	public ModelAndView showDefaultPage(HttpSession session) {
		User loginUser = this.getLoginUser(session);
		//获取所有权限低于当前用户的角色....暂未实现
		List<Role> roles = roleBiz.getAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("roles", roles);
		mav.setViewName(defaultPage);
		return mav;
	}
	
	@Override
	public User save(User user, HttpSession session) {
		User loginUser = this.getLoginUser(session);
		user.setUpdateBy(loginUser.getId());
		biz.save(user);
		return user;
	}
	

	@Override
	public User update(User user, HttpSession session) {
		User loginUser = this.getLoginUser(session);
		user.setUpdateBy(loginUser.getId());
		biz.update(user);
		return user;
	}
	
	/**修改密码
	 * @return 0：异常 1：正常 2：原密码输入错误
	 * */
	@RequestMapping("modifyPwd")
	@ResponseBody
	public Integer modifyPwd(Integer userId, String oldPwd, String password, HttpSession session){
		try{
			User loginUser = this.getLoginUser(session);
			int updateBy = loginUser.getId();
//			int loginUserId = null;
			return biz.modifyPwd(userId, oldPwd, password, updateBy);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public List<User> getAll(){
		return null;
	}

}
