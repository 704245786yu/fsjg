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
	
	@Column(name="user_name")
	private String userName;	//用户名，登录平台用，可为空
	
	private String password;
	
	@Column(name="trade_id")
	private Integer tradeId;	//行业分类，为服饰分类的一级类目
	
	@Column(name="enterprise_number")
	private String enterpriseNumber;	//企业编号
	
	@Column(name="enterprise_name")
	private String enterpriseName;
	
	private String linkman;
	
	private Long province;	//省
	
	private Long city;	//市
	
	private Long county;	//区县
	
	private Long town;	//镇/乡/街道
	
	@Column(name="detail_addr")
	private String detailAddr;	//详细地址
	
	private String telephone;	//手机号,手机号需唯一,用户可通过手机号登录系统
	
	private String fixPhone;
	
	private Long qq;
	
	@Column(name="create_by")
	private Integer createBy;	//创建人。0：表示用户自行注册，非0表示由平台管理人员录入
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "create_time")
	private Date createTime;	//创建时间(注册时间)
	
	@Column(name = "audit_state")
	private Byte auditState;	//审核状态，审核企业的资质。待审核:Wait 未通过:N 已通过:Y
	
	@Column(name = "audit_by")
	private Integer auditBy;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "audit_time")
	private Date auditTime;
	
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
	
	private String wechat;	//微信号
	
	private String email;	//电子邮箱
	
	@Column(name = "enterprise_img")
	private String enterpriseImg;	//工厂图片,多图片用逗号隔开
	
	private String description;	//工厂描述

	/**getter setter
	 * */
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
