package com.basic.po;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

public abstract class CustomerAbstract {

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
	
	private String email;	//电子邮箱

	@Column(name="audit_state")
	private Byte auditState;	//审核状态。待审核:Wait 未通过:N 已通过:Y
	
	@Column(name="audit_by")
	private Byte auditBy;	//审核人
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name="audit_time")
	private Date auditTime;	//审核时间
	

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

}
