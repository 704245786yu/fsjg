package com.sys.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseBiz;
import com.sys.dao.RoleAuthorityDao;
import com.sys.dao.RoleDao;
import com.sys.dao.RoleMenuDao;
import com.sys.po.Role;
import com.sys.po.RoleAuthority;
import com.sys.po.RoleMenu;

@Service
public class RoleBiz extends BaseBiz<RoleDao, Integer, Role>{

	@Autowired
	private RoleMenuDao roleMenuDao;
	@Autowired
	private RoleAuthorityDao roleAuthorityDao;
	
	/**保存角色,并同时保存角色与菜单关系，角色与权限关系
	 * @param authorityIds 可为null
	 * */
	@Transactional
	public Role save(Role role, Integer[] menuIds, Integer[] authorityIds){
		Integer roleId = (Integer)this.dao.save(role);
		ArrayList<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
		for(int menuId : menuIds){
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRoleId(roleId);
			roleMenu.setMenuId(menuId);
			roleMenuList.add(roleMenu);
		}
		roleMenuDao.saveBatch(roleMenuList);
		
		if(authorityIds != null){
			ArrayList<RoleAuthority> roleAuthorityList = new ArrayList<RoleAuthority>();
			for(int authorityId : authorityIds){
				RoleAuthority roleAuthority = new RoleAuthority();
				roleAuthority.setRoleId(roleId);
				roleAuthority.setAuthorityId(authorityId);
				roleAuthorityList.add(roleAuthority);
			}
			roleAuthorityDao.saveBatch(roleAuthorityList);
		}
		return role;
	}

	/**更新角色
	 * 1、删除之前角色菜单关系、角色权限关系
	 * 2、添加新的角色菜单关系、角色权限关系
	 * */
	@Transactional
	public void update(Role role, Integer[] menuIds, Integer[] authorityIds){
		this.dao.update(role);
		int roleId = role.getId();
		roleMenuDao.deleteByRoleId(roleId);
		roleAuthorityDao.deleteByRoleId(roleId);
		
		ArrayList<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
		for(int menuId : menuIds){
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRoleId(roleId);
			roleMenu.setMenuId(menuId);
			roleMenuList.add(roleMenu);
		}
		roleMenuDao.saveBatch(roleMenuList);

		if(authorityIds != null){
			ArrayList<RoleAuthority> roleAuthorityList = new ArrayList<RoleAuthority>();
			for(int authorityId : authorityIds){
				RoleAuthority roleAuthority = new RoleAuthority();
				roleAuthority.setRoleId(roleId);
				roleAuthority.setAuthorityId(authorityId);
				roleAuthorityList.add(roleAuthority);
			}
			roleAuthorityDao.saveBatch(roleAuthorityList);
		}
	}
	
	@Transactional
	@Override
	public void deleteById(Integer id) {
		roleMenuDao.deleteByRoleId(id);
		roleAuthorityDao.deleteByRoleId(id);
		super.deleteById(id);
	}
	
	/**根据当前角色ID获取该ID对应的角色信息和低等级角色
	 * @param roleId 角色ID
	 * @return 返回角色列表，其中包括roleId对应的角色，和等级（权限）低于该角色的所有角色。
	 * */
	public List<Role> getLowGradeRole(int roleId){
		
		return null;
	}
}
