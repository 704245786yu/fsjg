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

	/**显示默认的页面*/
	@Override
	public ModelAndView showDefaultPage() {
		ModelAndView mav = new ModelAndView();
		List<Role> roles = roleBiz.getAll();
		mav.addObject("roles", roles);
		mav.setViewName(defaultPage);
		return mav;
	}
	
	@Override
	public User save(User user, HttpSession session) {
		User loginUser = (User)session.getAttribute(LoginCtrl.loginUserKey);
		user.setUpdateBy(loginUser.getId());
		biz.save(user);
		return user;
	}
	

	@Override
	public User update(User user, HttpSession session) {
		User loginUser = (User)session.getAttribute(LoginCtrl.loginUserKey);
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
			User loginUser = (User)session.getAttribute(LoginCtrl.loginUserKey);
			int updateBy = loginUser.getId();
//			int loginUserId = null;
			return biz.modifyPwd(userId, oldPwd, password, updateBy);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**查询用户信息
	 * 限制：只能查看本组织和子组织的用户。
	 * */
	@RequestMapping("findByPage")
	@ResponseBody
	public BootTablePageDto<User> findByPage(int offset, int pageSize, HttpSession session){
		User loginUser = (User)session.getAttribute("loginUser");
//		return biz.findByOrgWithPage(offset, pageSize, organizationId);
		return null;
	}
	
	@Override
	public List<User> getAll(){
		return null;
	}

	@Override
	public BootTablePageDto<User> getAllByPage(Long total, int offset, int pageSize){
		return null;
	}
}