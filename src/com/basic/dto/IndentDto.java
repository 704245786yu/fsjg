package com.basic.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**加工订单DTO*/
public class IndentDto {

	private Integer id;
	private Long indentNum;//订单编号
	private Byte indentType;//订单类型
	private String indentName;//订单名称
	private Integer quantity;//订单数量
	private Byte saleMarket;//销售市场
	private Byte processType;//加工类型
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date preDeliveryDate;//预计交货日期',
	private Boolean isUrgency;//是否为紧急订单',
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date effectiveDate;//有效日期。前端选择天数，后端保存日期',
	private Long province;//发单用户省
	private Long city;//发单用户市
	private String district;
	private Long condProvince;//工厂报名条件限制：所在省',
	private Long condCity;//工厂报名条件限制：所在市',
	private String condDistrict;
	private String condDemand;//工厂接单要求',
	private Byte userType;//发单用户类型 0：个人 1：企业',
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;//创建时间'
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getIndentNum() {
		return indentNum;
	}
	public void setIndentNum(Long indentNum) {
		this.indentNum = indentNum;
	}
	public Byte getIndentType() {
		return indentType;
	}
	public void setIndentType(Byte indentType) {
		this.indentType = indentType;
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
	public Byte getSaleMarket() {
		return saleMarket;
	}
	public void setSaleMarket(Byte saleMarket) {
		this.saleMarket = saleMarket;
	}
	public Byte getProcessType() {
		return processType;
	}
	public void setProcessType(Byte processType) {
		this.processType = processType;
	}
	public Date getPreDeliveryDate() {
		return preDeliveryDate;
	}
	public void setPreDeliveryDate(Date preDeliveryDate) {
		this.preDeliveryDate = preDeliveryDate;
	}
	public Boolean getIsUrgency() {
		return isUrgency;
	}
	public void setIsUrgency(Boolean isUrgency) {
		this.isUrgency = isUrgency;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
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
	public Long getCondProvince() {
		return condProvince;
	}
	public void setCondProvince(Long condProvince) {
		this.condProvince = condProvince;
	}
	public Long getCondCity() {
		return condCity;
	}
	public void setCondCity(Long condCity) {
		this.condCity = condCity;
	}
	public String getCondDemand() {
		return condDemand;
	}
	public void setCondDemand(String condDemand) {
		this.condDemand = condDemand;
	}
	public Byte getUserType() {
		return userType;
	}
	public void setUserType(Byte userType) {
		this.userType = userType;
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
	public String getCondDistrict() {
		return condDistrict;
	}
	public void setCondDistrict(String condDistrict) {
		this.condDistrict = condDistrict;
	}
	
}
