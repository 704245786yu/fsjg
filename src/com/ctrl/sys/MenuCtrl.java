package com.ctrl.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biz.sys.MenuBiz;
import com.common.NestTreeCtrl;
import com.po.sys.Menu;

@Controller
@RequestMapping("menu")
public class MenuCtrl extends NestTreeCtrl<MenuBiz, Integer, Menu>{
	
}