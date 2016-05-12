package com.dao.sys;

import org.springframework.stereotype.Repository;

import com.common.BaseDao;
import com.po.sys.RoleAuthority;

@Repository
public class RoleAuthorityDao extends BaseDao<Integer,RoleAuthority>{

	/**根据角色ID删除相应数据
	 * */
	public void deleteByRoleId(int id){
		super.executeUpdate("delete from RoleAuthority where roleId =:roleId",
				new String[]{"roleId"}, new Integer[]{id});
	}
}