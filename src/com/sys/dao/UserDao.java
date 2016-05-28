package com.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.sys.po.User;

@Repository
@Transactional
public class UserDao extends BaseDao<Integer,User>{

	public Long getCountByOrg(List<Integer> orgIds){
		String hql = "select count(id) from User where organizationId in (:orgIds)";
		return super.getCount(hql, new String[]{"orgIds"}, new Object[]{orgIds});
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findByOrgWithPage(int offset, int pageSize, List<Integer> orgIds){
		String hql = "from User where organizationId in (:orgIds)";
		return (List<User>)super.findByPage(hql, offset, pageSize, new String[]{"orgIds"}, new Object[]{orgIds});
	}

}
