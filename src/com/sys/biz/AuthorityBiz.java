package com.sys.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.sys.dao.AuthorityDao;
import com.sys.po.Authority;

@Service
public class AuthorityBiz extends BaseBiz<AuthorityDao, Integer, Authority>{
	
	/**根据角色获取关联的权限
	 * */
	public List<Authority> getAuthByRoleId(int roleId){
		return dao.getAuthByRoleId(roleId);
	}
}
