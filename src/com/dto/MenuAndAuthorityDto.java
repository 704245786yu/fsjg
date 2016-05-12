package com.dto;

import java.util.List;

import com.po.sys.Authority;
import com.po.sys.Menu;

public class MenuAndAuthorityDto {

	private List<Menu> menuList;
	
	private List<Authority> authorityList;

	
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}
	
}
