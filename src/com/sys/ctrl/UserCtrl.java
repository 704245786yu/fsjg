package com.sys.ctrl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.sys.biz.RoleBiz;
import com.sys.biz.UserBiz;
import com.sys.po.Role;
import com.sys.po.User;

@Controller
@RequestMapping("user")
public class UserCtrl extends BaseCtrl<UserBiz, Integer, User> {
	
	private final String defaultPassword = "123456";
	
	@Autowired
	private RoleBiz roleBiz;
	
	public UserCtrl() {
		defaultPage = "sys/user";
	}

	/**获取当前登录用户*/
	public static User getLoginUser(HttpSession session){
		return (User)session.getAttribute(LoginCtrl.loginMngUser);
	}
	
	/**显示默认的页面*/
	@Override
	@RequestMapping
	public ModelAndView showDefaultPage(HttpSession session) {
		User loginUser = UserCtrl.getLoginUser(session);
		//获取所有权限低于当前用户的角色
		List<Role> roles = roleBiz.getLowGradeRole(loginUser.getRoleId());
		ModelAndView mav = new ModelAndView();
		mav.addObject("roles", roles);
		mav.setViewName(defaultPage);
		return mav;
	}

	/**保存用户
	 * 后台设置默认密码
	 * */
	@Override
	public User save(User user, HttpSession session) {
		User loginUser = UserCtrl.getLoginUser(session);
		user.setPassword(defaultPassword);	//设置默认密码
		user.setUpdateBy(loginUser.getId());
		biz.save(user);
		return user;
	}

	/**更新用户，但不更新用户密码
	 * */
	@Override
	public User update(User user, HttpSession session) {
		User loginUser = UserCtrl.getLoginUser(session);
		user.setUpdateBy(loginUser.getId());
		biz.update(user);
		return user;
	}
	
	/**密码验证
	 * */
	@RequestMapping("checkPwd")
	@ResponseBody
	public String checkPwd(Integer userId, String oldPassword){
		return biz.checkPwd(userId, oldPassword);
	}
	
	/**修改密码
	 * @return success：修改成功
	 * */
	@RequestMapping("modifyPwd")
	@ResponseBody
	public String modifyPwd(Integer id, String password, HttpSession session){
		User loginUser = UserCtrl.getLoginUser(session);
		int updateBy = loginUser.getId();
		biz.modifyPwd(id, password, updateBy);
		return "success";
	}
	
	/**根据搜索条件分页查询数据。searchText用于模糊匹配查询常量名称和常量类型名称。
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * @param userName 要模糊查询的常量名称
	 * */
	@RequestMapping("findByPageAndParams")
	@ResponseBody
	public BootTablePageDto<User> findByPageAndParams(int offset, int limit, String userName) {
		BootTablePageDto<User> bt = new BootTablePageDto<User>();
		bt = biz.findByPageAndParams(offset, limit, userName);
		List<User> users = bt.getRows();
		for (int i = 0; i < users.size(); i++) {
			users.get(i).setPassword(null);	//返回数据到前端之前密码置空
		}
		return bt;
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
