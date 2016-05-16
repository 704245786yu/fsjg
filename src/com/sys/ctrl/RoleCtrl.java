package com.sys.ctrl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.BaseCtrl;
import com.sys.biz.AuthorityBiz;
import com.sys.biz.MenuBiz;
import com.sys.biz.RoleBiz;
import com.sys.dto.MenuAndAuthorityDto;
import com.sys.po.Authority;
import com.sys.po.Menu;
import com.sys.po.Role;
import com.sys.po.User;
import com.util.JacksonJson;

@Controller
@RequestMapping("role")
public class RoleCtrl extends BaseCtrl<RoleBiz, Integer, Role>{

	@Autowired
	private MenuBiz menuBiz;
	@Autowired
	private AuthorityBiz authorityBiz;
	
	public RoleCtrl(){
		defaultPage = "sys/role";
	}
	
	public ModelAndView showDefaultPage(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("authorities", authorityBiz.getAll());
		mav.setViewName(defaultPage);
		return mav;
	}
	
	@RequestMapping("saveRole")
	@ResponseBody
	public Role saveRole(Role role, @RequestParam("menuIds[]") Integer[] menuIds, @RequestParam("authorityIds[]")Integer[] authorityIds,HttpSession httpSession){
		User user = (User)httpSession.getAttribute("loginUser");
//		JacksonJson.printBeanToJson(role);
//		JacksonJson.printBeanToJson(menuIds);
//		JacksonJson.printBeanToJson(authorityIds);
//		role.setUpdateBy(user.getId());
		role.setUpdateTime(new Date());
		return biz.save(role, menuIds, authorityIds);
	}
	
	@RequestMapping("updateRole")
	@ResponseBody
	public Role updateRole(Role role, @RequestParam("menuIds[]") Integer[] menuIds, @RequestParam("authorityIds[]")Integer[] authorityIds,HttpSession httpSession){
		User user = (User)httpSession.getAttribute("loginUser");
		JacksonJson.printBeanToJson(role);
		JacksonJson.printBeanToJson(menuIds);
		JacksonJson.printBeanToJson(authorityIds);
//		role.setUpdateBy(user.getId());
		role.setUpdateTime(new Date());
		biz.update(role, menuIds, authorityIds);
		return role;
	}
	
	/**根据角色获取关联的菜单、权限信息
	 * */
	@RequestMapping("getMenuAndAuth/{roleId}")
	@ResponseBody
	public MenuAndAuthorityDto getMenuAndAuth(@PathVariable int roleId){
		MenuAndAuthorityDto menuAndAuth = new MenuAndAuthorityDto();
		List<Menu> menuList = menuBiz.getMenuByRoleId(roleId);
		List<Authority> authList = authorityBiz.getAuthByRoleId(roleId);
		menuAndAuth.setMenuList(menuList);
		menuAndAuth.setAuthorityList(authList);
		return menuAndAuth;
	}
}
