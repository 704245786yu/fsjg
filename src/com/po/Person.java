package com.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**平台用户信息
 * */
@Entity
@Table(name="basic_person")
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String userName;
	private String realName;
	private String password;
	private Byte gender; //0：男 1：女
	private Byte age;
	private String province;
	private String city;
	private String county;
	private String town;
	private String telephone;
//	加工类型
//	主营产品
	private Short workerAmount;
	private String quote;
	private String email;
	private Long qq;
	private String fixPhone;
	private String wechat;
	private String postalCode;
	private String idCard;
	private String equipment;	//生产设备
	private String processDesc;	//承包加工说明
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Byte getGender() {
		return gender;
	}
	public void setGender(Byte gender) {
		this.gender = gender;
	}
	public Byte getAge() {
		return age;
	}
	public void setAge(Byte age) {
		this.age = age;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Short getWorkerAmount() {
		return workerAmount;
	}
	public void setWorkerAmount(Short workerAmount) {
		this.workerAmount = workerAmount;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getQq() {
		return qq;
	}
	public void setQq(Long qq) {
		this.qq = qq;
	}
	public String getFixPhone() {
		return fixPhone;
	}
	public void setFixPhone(String fixPhone) {
		this.fixPhone = fixPhone;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getProcessDesc() {
		return processDesc;
	}
	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}
	
}
