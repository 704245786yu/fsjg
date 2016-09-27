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
	
	@Column(name="id_num")
	private String idNum;	//身份证号
	
	@Column(name="id_front_photo")
	private String idFrontPhoto;	//身份证照片(正)

	@Column(name="id_back_photo")
	private String idBackPhoto;	//身份证照片(反)
	
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

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getIdFrontPhoto() {
		return idFrontPhoto;
	}

	public void setIdFrontPhoto(String idFrontPhoto) {
		this.idFrontPhoto = idFrontPhoto;
	}

	public String getIdBackPhoto() {
		return idBackPhoto;
	}

	public void setIdBackPhoto(String idBackPhoto) {
		this.idBackPhoto = idBackPhoto;
	}

}
