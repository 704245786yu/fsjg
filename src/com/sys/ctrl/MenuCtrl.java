package com.sys.ctrl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.NestTreeCtrl;
import com.sys.biz.MenuBiz;
import com.sys.po.Menu;

@Controller
@RequestMapping("menu")
public class MenuCtrl extends NestTreeCtrl<MenuBiz, Integer, Menu>{

	public MenuCtrl(){
		defaultPage = "sys/menu";
	}
	
	/**根据用户所属角色获取相应菜单
	 * */
	public List<Menu> getMenuByUser(HttpSession session){
		return null;
	}
	
	/**根据角色获取关联的菜单,以邻接列表模型返回
	 * */
	@RequestMapping("getMenuByRoleId/{roleId}")
	@ResponseBody
	public List<Menu> getMenuByRoleId(@PathVariable int roleId){
		return biz.getMenuByRoleId(roleId);
	}
}