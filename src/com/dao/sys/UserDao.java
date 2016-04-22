package com.dao.sys;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.po.sys.User;

@Repository
@Transactional
public class UserDao extends BaseDao<Integer,User>{

}
