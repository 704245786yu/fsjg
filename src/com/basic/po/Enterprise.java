package com.basic.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**企业信息
 * */
@Entity
@Table(name="basic_enterprise")
public class Enterprise extends UserAbstract{
	
	@Column(name="trade_id")
	private Integer tradeId;	//行业分类，为服饰分类的一级类目
	
	@Column(name="enterprise_number")
	private String enterpriseNumber;	//企业编号
	
	@Column(name="enterprise_name")
	private String enterpriseName;
	
	private String linkman;
	
	@Column(name = "sale_market")
	private Byte saleMarket;	//销售市场
	
	@Column(name = "business_license_Img")
	private String businessLicenseImg;	//营业执照
	
	@Column(name = "organization_code")
	private String organizationCode;	//组织机构代码
	
	@Column(name = "process_type")
	private Byte processType;	//加工类型
	
	@Column(name = "minimum_staff_amount")
	private Short minimumStaffAmount;	//最小员工数量
	
	@Column(name = "maximum_staff_amount")
	private Short maximumStaffAmount;	//最大员工数量
	
	@Column(name = "high_speed_staff_amount")
	private Short highSpeedStaffAmount;	//高速车工人数
	
	@Column(name = "other_staff_amount")
	private Short otherStaffAmount;	//其他加工人数
	
	@Column(name = "enterprise_age")
	private Short enterpriseAge;	//经营期限
	
	@Column(name = "equipment_desc")
	private String equipmentDesc;	//生产设备描述
	
	private Integer yield;	//产值产量
	
	@Column(name = "cooperation_customer")
	private String cooperationCustomer;	//合作客户
	
	@Column(name = "website_url")
	private String websiteUrl;	//企业网址
	
	@Column(name = "enterprise_img")
	private String enterpriseImg;	//工厂图片,多图片用逗号隔开
	
	private String description;	//工厂描述


	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public String getEnterpriseNumber() {
		return enterpriseNumber;
	}

	public void setEnterpriseNumber(String enterpriseNumber) {
		this.enterpriseNumber = enterpriseNumber;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public Byte getSaleMarket() {
		return saleMarket;
	}

	public void setSaleMarket(Byte saleMarket) {
		this.saleMarket = saleMarket;
	}

	public String getBusinessLicenseImg() {
		return businessLicenseImg;
	}

	public void setBusinessLicenseImg(String businessLicenseImg) {
		this.businessLicenseImg = businessLicenseImg;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public Byte getProcessType() {
		return processType;
	}

	public void setProcessType(Byte processType) {
		this.processType = processType;
	}

	public Short getMinimumStaffAmount() {
		return minimumStaffAmount;
	}

	public void setMinimumStaffAmount(Short minimumStaffAmount) {
		this.minimumStaffAmount = minimumStaffAmount;
	}

	public Short getMaximumStaffAmount() {
		return maximumStaffAmount;
	}

	public void setMaximumStaffAmount(Short maximumStaffAmount) {
		this.maximumStaffAmount = maximumStaffAmount;
	}

	public Short getHighSpeedStaffAmount() {
		return highSpeedStaffAmount;
	}

	public void setHighSpeedStaffAmount(Short highSpeedStaffAmount) {
		this.highSpeedStaffAmount = highSpeedStaffAmount;
	}

	public Short getOtherStaffAmount() {
		return otherStaffAmount;
	}

	public void setOtherStaffAmount(Short otherStaffAmount) {
		this.otherStaffAmount = otherStaffAmount;
	}

	public Short getEnterpriseAge() {
		return enterpriseAge;
	}

	public void setEnterpriseAge(Short enterpriseAge) {
		this.enterpriseAge = enterpriseAge;
	}

	public String getEquipmentDesc() {
		return equipmentDesc;
	}

	public void setEquipmentDesc(String equipmentDesc) {
		this.equipmentDesc = equipmentDesc;
	}

	public Integer getYield() {
		return yield;
	}

	public void setYield(Integer yield) {
		this.yield = yield;
	}

	public String getCooperationCustomer() {
		return cooperationCustomer;
	}

	public void setCooperationCustomer(String cooperationCustomer) {
		this.cooperationCustomer = cooperationCustomer;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getEnterpriseImg() {
		return enterpriseImg;
	}

	public void setEnterpriseImg(String enterpriseImg) {
		this.enterpriseImg = enterpriseImg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
