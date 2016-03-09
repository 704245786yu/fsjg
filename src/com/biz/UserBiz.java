package com.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;

@Service
public class UserBiz {

	@Autowired
	private UserDao userDao;
}
