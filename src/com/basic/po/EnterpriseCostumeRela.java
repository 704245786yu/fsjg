package com.basic.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="basic_enterprise_costume_rela")
public class EnterpriseCostumeRela {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="enterprise_id")
	private Integer enterpriseId;
	
	@Column(name="costume_code")
	private Integer costumeCode;

	public EnterpriseCostumeRela() {}
	
	public EnterpriseCostumeRela(Integer enterpriseId, Integer costumeCode) {
		this.enterpriseId = enterpriseId;
		this.costumeCode = costumeCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getCostumeCode() {
		return costumeCode;
	}

	public void setCostumeCode(Integer costumeCode) {
		this.costumeCode = costumeCode;
	}
	
}
