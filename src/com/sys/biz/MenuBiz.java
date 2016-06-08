package com.sys.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.NestTreeBiz;
import com.sys.dao.MenuDao;
import com.sys.dao.RoleMenuDao;
import com.sys.po.Menu;

@Service
public class MenuBiz extends NestTreeBiz<MenuDao, Integer, Menu>{
	
	@Autowired
	private RoleMenuDao roleMenuDao;
	
	/**根据角色获取关联的菜单,以邻接列表模型返回
	 * */
	public List<Menu> getMenuByRoleId(int roleId){
		List<?> menuIdList = roleMenuDao.getMenuIdByRoleId(roleId);
		Integer[] menuIds = new Integer[menuIdList.size()];
		for(int i=0; i<menuIdList.size(); i++){
			menuIds[i] = (Integer)menuIdList.get(i);
		}
		return this.transformAdjTree(menuIds);
	}
}