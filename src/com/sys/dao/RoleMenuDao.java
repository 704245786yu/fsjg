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
	
	/**根据当前角色ID获取该ID对应的角色信息和低等级角色
	 * @param roleId
	 * @return List roleIds
	 * */
	@SuppressWarnings("unchecked")
	public List<Integer> getLowGradeRoleId(int roleId){
		StringBuffer buffer = new StringBuffer("select t1.role_id from ")
			//统计每个角色所拥有的菜单在roleId所拥有的菜单范围内的菜单个数
			.append("(SELECT r1.role_id,count(menu_id) c FROM sys_role_menu r1 where menu_id in (select menu_id from sys_role_menu where role_id =:roleId) group by role_id) t1")
			.append(" INNER JOIN ")
			//统计每个角色所拥有的菜单个数
			.append("(select r2.role_id,count(menu_id) c from sys_role_menu r2 group by role_id) t2")
			//返回两个菜单统计数相同的记录
			.append(" on t1.role_id = t2.role_id and t1.c=t2.c");
		List<Integer> list = (List<Integer>)super.findByNativeSql(buffer.toString(), new String[]{"roleId"}, new Integer[]{roleId});
		return list;
	}
}
