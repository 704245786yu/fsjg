package com.auth.model;

/**菜单位置*/
public enum MenuPos {
	NAV_LEFT("左边导航"),NAV_TOP("顶部导航"), MODEL_NAV("非导航菜单"), MODEL_OPER("模块操作");
	
	private String name;
	
	private MenuPos(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
