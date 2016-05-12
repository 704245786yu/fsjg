package com.biz.sys;

import java.util.List;

import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.dao.sys.AuthorityDao;
import com.po.sys.Authority;

@Service
public class AuthorityBiz extends BaseBiz<AuthorityDao, Integer, Authority>{
	
	/**根据角色获取关联的菜单,以邻接列表模型返回
	 * */
	public List<Authority> getAuthByRoleId(int roleId){
		return dao.getAuthByRoleId(roleId);
	}
}
