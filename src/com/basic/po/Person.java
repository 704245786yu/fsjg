package com.basic.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**平台个人用户信息
 * */
@Entity
@Table(name="basic_person")
public class Person extends UserAbstract{

	@Column(name="real_name")
	private String realName;
	
	private String gender;
	
	private Byte age;
	
	@Column(name="person_state")
	private Byte personState;	//用户状态 0：正常 1：冻结
	
	@Column(name="postal_code")
	private String postalCode;	//邮政编码
	
	private String idCard;	//身份证号
	
	@Column(name="idCard_photo_url")
	private String idCardPhotoUrl;	//身份证照片url

	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public Byte getPersonState() {
		return personState;
	}

	public void setPersonState(Byte personState) {
		this.personState = personState;
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

	public String getIdCardPhotoUrl() {
		return idCardPhotoUrl;
	}

	public void setIdCardPhotoUrl(String idCardPhotoUrl) {
		this.idCardPhotoUrl = idCardPhotoUrl;
	}
	
}
