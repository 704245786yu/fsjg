package com.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.BaseDao;
import com.sys.po.Authority;

/**@author zhiyu
 * */
@Repository
public class AuthorityDao extends BaseDao<Integer,Authority>{

	@SuppressWarnings("unchecked")
	public List<Authority> getAuthByRoleId(int roleId){
		String hql = "select a from Authority a, RoleAuthority ra where ra.roleId = ? and a.id = ra.authorityId";
		ArrayList<Integer> params = new ArrayList<Integer>();
		params.add(roleId);
		return (List<Authority>)super.find(hql, params);
	}
}