package com.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.BaseDao;
import com.sys.po.RoleAuthority;

@Repository
public class RoleAuthorityDao extends BaseDao<Integer,RoleAuthority>{

	/**根据角色ID删除相应数据
	 * */
	public void deleteByRoleId(int id){
		super.executeUpdate("delete from RoleAuthority where roleId =:roleId",
				new String[]{"roleId"}, new Integer[]{id});
	}
	
	/**根据当前角色ID获取该ID对应的角色信息和低等级角色
	 * @param roleId
	 * @return List roleIds
	 * */
	@SuppressWarnings("unchecked")
	public List<Integer> getLowGradeRoleId(int roleId){
		StringBuffer buffer = new StringBuffer("select t1.role_id from ")
			//统计每个角色所拥有的权限在roleId所拥有的权限范围内的权限个数
			.append("(SELECT r1.role_id,count(authority_id) c FROM sys_role_authority r1 where authority_id in (select authority_id from sys_role_authority where role_id =:roleId) group by role_id) t1")
			.append(" INNER JOIN ")
			//统计每个角色所拥有的权限个数
			.append("(select r2.role_id,count(authority_id) c from sys_role_authority r2 group by role_id) t2")
			//返回两个权限统计数相同的记录
			.append(" on t1.role_id = t2.role_id and t1.c=t2.c");
		List<Integer> list1 = (List<Integer>)super.findByNativeSql(buffer.toString(), new String[]{"roleId"}, new Integer[]{roleId});
		//查找所有没有权限的角色
		List<Integer> list2 = (List<Integer>)super.find("select id from Role where id not in (select DISTINCT roleId from RoleAuthority)",
				new String[]{}, null);
		list1.addAll(list2);
		return list1;
	}
}