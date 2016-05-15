package com.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.BaseDao;
import com.sys.po.RoleMenu;

@Repository
public class RoleMenuDao extends BaseDao<Integer,RoleMenu>{
	
	/**根据角色ID删除相应数据
	 * */
	public void deleteByRoleId(int id){
		super.executeUpdate("delete from RoleMenu where roleId =:roleId",
				new String[]{"roleId"}, new Integer[]{id});
	}
	
	/**根据角色ID获取关联的菜单ID
	 * */
	public List<?> getMenuIdByRoleId(int roleId){
		ArrayList<Integer> params = new ArrayList<Integer>();
		params.add(roleId);
		return super.find("select menuId from RoleMenu where roleId = ?", params);
	}
}
