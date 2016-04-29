package com.po.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.po.NestTreePO;

@Entity
@Table(name="sys_menu")
public class Menu extends NestTreePO{

	@Column(name="menu_name")
	private String menuName;
	
	private String path;
	
	@Column(name="img_path")
	private String imgPath;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}
