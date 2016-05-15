package com.sys.biz;

import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.sys.dao.UserDao;
import com.sys.po.User;

@Service
public class UserBiz extends BaseBiz<UserDao, Integer, User>{

}
