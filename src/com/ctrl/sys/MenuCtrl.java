package com.ctrl.sys;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biz.sys.MenuBiz;
import com.common.NestTreeCtrl;
import com.po.sys.Menu;

@Controller
@RequestMapping("menu")
public class MenuCtrl extends NestTreeCtrl<MenuBiz, Integer, Menu>{

	/**根据用户所属角色获取相应菜单
	 * */
	public List<Menu> getMenuByRole(HttpSession session){
		return null;
	}
}