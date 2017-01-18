package com.basic.vo;

/**首页展示快产专家*/
public class ContractorHomeVo {

	private Integer id;
	private String gender;
	private Byte age;
	private Short processYear;
	private Short workerAmount;
	private Long province;
	private Long city;
	private String district;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
}
