package com.basic.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class IndentDistVo {
	private Long indentNum;//订单编号
	private String indentName;//订单名称
	private String costumeCode;//服饰编号,
	private Byte processType;//加工类型
	private Integer quantity;//订单数量
	private Boolean isUrgency;//是否急单
	private Long province;
	private Long city;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;//报价日期
	
	public Long getIndentNum() {
		return indentNum;
	}
	public void setIndentNum(Long indentNum) {
		this.indentNum = indentNum;
	}
	public String getIndentName() {
		return indentName;
	}
	public void setIndentName(String indentName) {
		this.indentName = indentName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getCostumeCode() {
		return costumeCode;
	}
	public void setCostumeCode(String costumeCode) {
		this.costumeCode = costumeCode;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Boolean getIsUrgency() {
		return isUrgency;
	}
	public void setIsUrgency(Boolean isUrgency) {
		this.isUrgency = isUrgency;
	}
	public Byte getProcessType() {
		return processType;
	}
	public void setProcessType(Byte processType) {
		this.processType = processType;
	}
	
}
