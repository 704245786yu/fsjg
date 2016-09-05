package com.sys.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.BaseCtrl;
import com.sys.biz.AuthBiz;
import com.sys.po.Auth;

@Controller
@RequestMapping("auth")
public class AuthCtrl extends BaseCtrl<AuthBiz, Integer, Auth> {

	public AuthCtrl(){
		defaultPage = "sys/auth";
	}
}
