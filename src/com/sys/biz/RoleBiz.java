package com.sys.biz;

import java.util.ArrayList;

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
		ArrayList<RoleAuthority> roleAuthorityList = new ArrayList<RoleAuthority>();
		for(int authorityId : authorityIds){
			RoleAuthority roleAuthority = new RoleAuthority();
			roleAuthority.setRoleId(roleId);
			roleAuthority.setAuthorityId(authorityId);
			roleAuthorityList.add(roleAuthority);
		}
		roleMenuDao.saveBatch(roleMenuList);
		roleAuthorityDao.saveBatch(roleAuthorityList);
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
		ArrayList<RoleAuthority> roleAuthorityList = new ArrayList<RoleAuthority>();
		for(int authorityId : authorityIds){
			RoleAuthority roleAuthority = new RoleAuthority();
			roleAuthority.setRoleId(roleId);
			roleAuthority.setAuthorityId(authorityId);
			roleAuthorityList.add(roleAuthority);
		}
		roleMenuDao.saveBatch(roleMenuList);
		roleAuthorityDao.saveBatch(roleAuthorityList);
	}
	
	@Transactional
	@Override
	public void deleteById(Integer id) {
		roleMenuDao.deleteByRoleId(id);
		roleAuthorityDao.deleteByRoleId(id);
		super.deleteById(id);
	}
	
}
