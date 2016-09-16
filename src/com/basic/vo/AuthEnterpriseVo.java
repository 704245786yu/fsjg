package com.basic.vo;

import java.util.List;

/**最新认证工厂*/
public class AuthEnterpriseVo {

	private Integer id;
	private String name;
	private String logo;
	private List<Integer> costumeCode;	//主营产品
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public List<Integer> getCostumeCode() {
		return costumeCode;
	}
	public void setCostumeCode(List<Integer> costumeCode) {
		this.costumeCode = costumeCode;
	}
	
}
