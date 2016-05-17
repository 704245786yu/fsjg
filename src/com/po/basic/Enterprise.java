package com.po.basic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**企业信息
 * */
@Entity
@Table(name="basic_enterprise")
public class Enterprise {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
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

	public String getFixPhone() {
		return fixPhone;
	}

	public void setFixPhone(String fixPhone) {
		this.fixPhone = fixPhone;
	}

	public Long getQq() {
		return qq;
	}

	public void setQq(Long qq) {
		this.qq = qq;
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

	public Byte getSaleMarket() {
		return saleMarket;
	}

	public void setSaleMarket(Byte saleMarket) {
		this.saleMarket = saleMarket;
	}

	public String getBusinessLicenseUrl() {
		return businessLicenseUrl;
	}

	public void setBusinessLicenseUrl(String businessLicenseUrl) {
		this.businessLicenseUrl = businessLicenseUrl;
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

	public Short getStaffAmount() {
		return staffAmount;
	}

	public void setStaffAmount(Short staffAmount) {
		this.staffAmount = staffAmount;
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

	public String getEnterpriseImg() {
		return enterpriseImg;
	}

	public void setEnterpriseImg(String enterpriseImg) {
		this.enterpriseImg = enterpriseImg;
	}

	public Byte getQualificationState() {
		return qualificationState;
	}

	public void setQualificationState(Byte qualificationState) {
		this.qualificationState = qualificationState;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="user_name")
	private String userName;
	
	private String password;
	
	@Column(name="trade_id")
	private Integer tradeId;
	
	@Column(name="enterprise_number")
	private String enterpriseNumber;
	
	@Column(name="enterprise_name")
	private String enterpriseName;
	
	private String linkman;
	
	private String province;
	
	private String city;
	
	private String country;
	
	private String town;
	
	@Column(name="detail_addr")
	private String detailAddr;
	
	private String telephone;
	
	private String fixPhone;
	
	private Long qq;
	
	@Column(name="create_by")
	private Integer createBy;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "audit_state")
	private Byte auditState;
	
	@Column(name = "audit_by")
	private Integer auditBy;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "audit_time")
	private Date auditTime;
	
	@Column(name = "sale_market")
	private Byte saleMarket;
	
	@Column(name = "business_license_url")
	private String businessLicenseUrl;
	
	@Column(name = "organization_code")
	private String organizationCode;
	
	@Column(name = "process_type")
	private Byte processType; 
	
	@Column(name = "staff_amount")
	private Short staffAmount;
	
	@Column(name = "high_speed_staff_amount")
	private Short highSpeedStaffAmount;
	
	@Column(name = "other_staff_amount")
	private Short otherStaffAmount;
	
	@Column(name = "enterprise_age")
	private Short enterpriseAge;
	
	@Column(name = "equipment_desc")
	private String equipmentDesc;
	
	private Integer yield;
	
	@Column(name = "cooperation_customer")
	private String cooperationCustomer;
	
	@Column(name = "website_url")
	private String websiteUrl;
	
	private String wechat;
	
	private String email;
	
	@Column(name = "enterprise_img")
	private String enterpriseImg;
	
	@Column(name = "qualification_state")
	private Byte qualificationState;
	
	private String description;
}
