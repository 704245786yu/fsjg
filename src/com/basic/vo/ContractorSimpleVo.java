package com.basic.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ContractorSimpleVo {

	private Integer id;
	private String realName;
	private Byte age;
	private Short processYear;
	private Short workerAmount;
	private Long province;
	private Long city;
	private Long county;
	private Long town;
	private String district;
	private String costumeCode;
	private String skill;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Byte getAge() {
		return age;
	}
	public void setAge(Byte age) {
		this.age = age;
	}
	public Short getProcessYear() {
		return processYear;
	}
	public void setProcessYear(Short processYear) {
		this.processYear = processYear;
	}
	public Short getWorkerAmount() {
		return workerAmount;
	}
	public void setWorkerAmount(Short workerAmount) {
		this.workerAmount = workerAmount;
	}
	public Long getProvince() {
		return province;
	}
	public void setProvince(Long province) {
		this.province = province;
	}
	public Long getCity() {
		return city;
	}
	public void setCity(Long city) {
		this.city = city;
	}
	public Long getCounty() {
		return county;
	}
	public void setCounty(Long county) {
		this.county = county;
	}
	public Long getTown() {
		return town;
	}
	public void setTown(Long town) {
		this.town = town;
	}
	public String getCostumeCode() {
		return costumeCode;
	}
	public void setCostumeCode(String costumeCode) {
		this.costumeCode = costumeCode;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
}
