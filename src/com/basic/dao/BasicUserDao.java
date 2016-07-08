package com.basic.dao;

import org.springframework.stereotype.Repository;

import com.basic.po.BasicUser;
import com.common.BaseDao;

@Repository
public class BasicUserDao extends BaseDao<Integer, BasicUser>{

	/**检查用户名、手机号是否已经存在*/
	public boolean isExsit(String userName,Long telephone){
		String hql = "select count(1) from BasicUser where userName =:userName or telephone =:telephone";
		long amount = super.getCount(hql, new String[]{"userName","telephone"}, new Object[]{userName,telephone});
		if(amount > 0)
			return true;
		else
			return false;
	}
	
	public boolean nameIsExsit(String userName){
		String hql = "select count(1) from BasicUser where userName =:userName";
		long amount = super.getCount(hql, new String[]{"userName"}, new String[]{userName});
		if(amount > 0)
			return true;
		else
			return false;
	}
	
	public boolean teleIsExsit(Long telephone){
		String hql = "select count(1) from BasicUser where telephone =:telephone";
		long amount = super.getCount(hql, new String[]{"telephone"}, new Long[]{telephone});
		if(amount > 0)
			return true;
		else
			return false;
	}
}
