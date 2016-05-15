package com.sys.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_constant_type")
public class ConstantType{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="constant_type_code")
	private String constantTypeCode;
	
	@Column(name="constant_type_name")
	private String constantTypeName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConstantTypeCode() {
		return constantTypeCode;
	}

	public void setConstantTypeCode(String constantTypeCode) {
		this.constantTypeCode = constantTypeCode;
	}

	public String getConstantTypeName() {
		return constantTypeName;
	}

	public void setConstantTypeName(String constantTypeName) {
		this.constantTypeName = constantTypeName;
	}

	@Override
	public String toString() {
		return getId()+"-"+getConstantTypeCode()+"-"+getConstantTypeName();
	}
	
}
