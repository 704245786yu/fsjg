package com.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biz.UserBiz;

@Controller
@RequestMapping("user")
public class UserCtrl {

	@Autowired
	private UserBiz userBiz;
}