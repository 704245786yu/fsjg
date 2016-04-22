package com.biz.sys;

import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.dao.sys.UserDao;
import com.po.sys.User;

@Service
public class UserBiz extends BaseBiz<UserDao, Integer, User>{

}
