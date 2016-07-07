package com.basic.po;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**企业信息
 * */
@Entity
@Table(name="basic_enterprise")
public class Enterprise extends UserAbstract{
	
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
	
	private String trade;	//行业分类，为服饰分类的一级类目
	
	@Column(name = "process_type")
	private String processType;	//加工类型
	
	@Transient
	private List<Integer> costumeCode;	//主营产品
	
	/*@Column(name = "minimum_staff_amount")
	private Short minimumStaffAmount;	//最小员工数量
	
	@Column(name = "maximum_staff_amount")
	private Short maximumStaffAmount;	//最大员工数量
*/	
	
	@Column(name = "staff_number")
	private Integer staffNumber;
	
	@Column(name = "high_speed_staff_number")
	private Integer highSpeedStaffNumber;	//高速车工人数
	
	@Column(name = "other_staff_number")
	private Integer otherStaffNumber;	//其他加工人数
	
	@Column(name = "enterprise_age")
	private Short enterpriseAge;	//经营期限
	
	@Column(name = "equipment")
	private String equipment;	//生产设备描述
	
	private String yield;	//产值产量
	
	private String cooperator;	//合作客户
	
	@Column(name = "website_url")
	private String websiteUrl;	//企业网址
	
	@Column(name = "enterprise_img")
	private String enterpriseImg;	//工厂图片,多图片用逗号隔开
	
	private String description;	//工厂描述


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

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}
	
	public List<Integer> getCostumeCode() {
		return costumeCode;
	}

	public void setCostumeCode(List<Integer> costumeCode) {
		this.costumeCode = costumeCode;
	}

	public Short getEnterpriseAge() {
		return enterpriseAge;
	}

	public void setEnterpriseAge(Short enterpriseAge) {
		this.enterpriseAge = enterpriseAge;
	}

	public Integer getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(Integer staffNumber) {
		this.staffNumber = staffNumber;
	}

	public Integer getHighSpeedStaffNumber() {
		return highSpeedStaffNumber;
	}

	public void setHighSpeedStaffNumber(Integer highSpeedStaffNumber) {
		this.highSpeedStaffNumber = highSpeedStaffNumber;
	}

	public Integer getOtherStaffNumber() {
		return otherStaffNumber;
	}

	public void setOtherStaffNumber(Integer otherStaffNumber) {
		this.otherStaffNumber = otherStaffNumber;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}

	public String getCooperator() {
		return cooperator;
	}

	public void setCooperator(String cooperator) {
		this.cooperator = cooperator;
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
