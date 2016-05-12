package com.biz.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.NestTreeBiz;
import com.dao.sys.MenuDao;
import com.dao.sys.RoleMenuDao;
import com.po.sys.Menu;

@Service
public class MenuBiz extends NestTreeBiz<MenuDao, Integer, Menu>{
	
	@Autowired
	private RoleMenuDao roleMenuDao;
	
	/**根据角色获取关联的菜单,以邻接列表模型返回
	 * */
	public List<Menu> getMenuByRoleId(int roleId){
		List<?> menuIdList = roleMenuDao.getMenuIdByRoleId(roleId);
		Integer[] menuIds = new Integer[menuIdList.size()];
		for(int i=0; i<menuIds.length; i++){
			menuIds[i] = (Integer)menuIdList.get(i);
		}
		return this.transformAdjTree(menuIds);
	}
}