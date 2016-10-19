package com.basic.po;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

/**个人用户与企业用户的共用字段*/
@MappedSuperclass
public abstract class UserAbstract {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@OneToOne(cascade=CascadeType.PERSIST,targetEntity=BasicUser.class)
	@JoinColumn(name="user_id")
	private BasicUser basicUser;
	
	private Long province;	//省
	
	private Long city;	//市
	
	private Long county;	//区县
	
	private Long town;	//镇/乡/街道
	
	@Column(name="detail_addr")
	private String detailAddr;	//详细地址
	
	private Long qq;
	
	@Column(name="fix_phone")
	private String fixPhone;
	
	private String wechat;	//微信号
	
	@Column(name="postal_code")
	private String postalCode;	//邮政编码
	
	private String email;	//电子邮箱

	@Column(name="audit_state")
	private Byte auditState;	//审核状态。0:待审核 1:未通过 2:已通过
	
	@Column(name="audit_by")
	private Integer auditBy;	//审核人
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name="audit_time")
	private Date auditTime;	//审核时间
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BasicUser getBasicUser() {
		return basicUser;
	}

	public void setBasicUser(BasicUser basicUser) {
		this.basicUser = basicUser;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Byte getAuditState() {
		return auditState;
	}

	public void setAuditState(Byte auditState) {
		this.auditState = auditState;
	}

	public Integer getAuditBy() {
		return auditBy;
	}

	public void setAuditBy(Integer auditBy) {
		this.auditBy = auditBy;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
