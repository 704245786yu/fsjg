package com.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.sys.po.User;

@Repository
@Transactional
public class UserDao extends BaseDao<Integer,User>{

	/**获取用户信息，但不包括密码
	 * */
	@Override
	public List<User> getAll() {
		return super.find("select new User(id, userName, roleId, updateBy, updateTime) from User");
	}

}
