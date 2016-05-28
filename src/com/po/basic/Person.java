package com.po.basic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**平台个人用户信息
 * */
@Entity
@Table(name="basic_person")
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="real_name")
	private String realName;
	
	private String password;
	
	private String gender;
	
	private Byte age;
	
	private Long province;
	
	private Long city;
	
	private Long county;
	
	private Long town;
	
	@Column(name="detail_addr")
	private String detailAddr;
	
	private String telephone;
	
	@Column(name="create_by")
	private Integer createBy;	//创建人。0：表示用户自行注册，非0表示由平台管理人员录入
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name="create_time")
	private Date createTime;	//注册时间
	
	@Column(name="audit_state")
	private Byte auditState;	//审核状态，0：待审核 1：未通过 2：已通过
	
	@Column(name="audit_by")
	private Byte auditBy;	//审核人
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name="audit_time")
	private Date auditTime;	//审核时间

	@Column(name="person_state")
	private Byte personState;	//用户状态 0：正常 1：冻结
	
	private String email;
	
	private Long qq;
	
	@Column(name="fix_phone")
	private String fixPhone;
	
	private String wechat;
	
	@Column(name="postal_code")
	private String postalCode;	//邮政编码
	
	private String idCard;	//身份证号
	
	@Column(name="idCard_photo_url")
	private String idCardPhotoUrl;	//身份证照片url

	
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

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Byte getAuditState() {
		return auditState;
	}

	public void setAuditState(Byte auditState) {
		this.auditState = auditState;
	}

	public Byte getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(Byte auditBy) {
		this.auditBy = auditBy;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Byte getPersonState() {
		return personState;
	}

	public void setPersonState(Byte personState) {
		this.personState = personState;
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

	public String getIdCardPhotoUrl() {
		return idCardPhotoUrl;
	}

	public void setIdCardPhotoUrl(String idCardPhotoUrl) {
		this.idCardPhotoUrl = idCardPhotoUrl;
	}
	
}
