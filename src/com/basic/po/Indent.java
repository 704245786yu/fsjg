package com.basic.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**加工订单*/
@Entity
@Table(name="process_indent")
public class Indent {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="indent_num")
	private Integer indentNum;//订单编号
	
	@Column(name="indent_type")
	private Byte indentType;//订单类型
	
	@Column(name="indent_name")
	private String indentName;//订单名称
	
	@Column(name="costume_code")
	private String costumeCode;//服饰类型编码，多个用,隔开
	
	private Integer quantity;//订单数量
	
	private String description;//订单说明
	
	@Column(name="process_type")
	private Byte processType;//加工类型
	
	@Column(name="expect_price")
	private Double expectPrice;//预期价，若为空，则表示面谈,
	
	@Column(name="pre_delivery_date")
	private Date preDeliveryDate;//预计交货日期',
	
	@Column(name="is_urgency")
	private Boolean isUrgency;//是否为紧急订单',
	
	@Column(name="effective_date")
	private Date effectiveDate;//有效日期。前端选择天数，后端保存日期',
	
	@Column(name="detail")
	private String detail;//详细说明',
	
	@Column(name="linkman")
	private String linkman;//联系人',
	
	@Column(name="telephone")
	private String telephone;//联系电话',
	
	@Column(name="cond_province")
	private Long condProvince;//工厂报名条件限制：所在省',
	
	@Column(name="cond_city")
	private Long condCity;//工厂报名条件限制：所在市',
	
	@Column(name="cond_county")
	private Long condCounty;//工厂报名条件限制：所在区县',
	
	@Column(name="cond_town")
	private Long condTown;//工厂报名条件限制：所在镇/乡/街道',
	
	@Column(name="cond_staff_num")
	private Integer condStaffNum;//工厂报名条件限制：工人数量',
	
	@Column(name="cond_demand")
	private String condDemand;//工厂接单要求',
	
	@Column(name="photo")
	private String photo;//样品图，多个图用逗号分隔',
	
	@Column(name="document")
	private String document;//订单相关文档',
	
	@Column(name="state")
	private Byte state;//订单状态 0：未收到报价 1：已收到报价 2：已接单 3：已失效',
	
	@Column(name="received_enterprise_id")
	private Integer receivedEnterpriseId;//接单企业ID',
	
	@Column(name="create_by")
	private Integer createBy;//创建用户（个人或企业）ID',
	
	@Column(name="create_user_type")
	private Byte createUserType;//发单用户类型 0：个人 1：企业',
	
	@Column(name="create_time")
	private Date createTime;//创建时间'

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIndentNum() {
		return indentNum;
	}

	public void setIndentNum(Integer indentNum) {
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

	public String getCostumeCode() {
		return costumeCode;
	}

	public void setCostumeCode(String costumeCode) {
		this.costumeCode = costumeCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getProcessType() {
		return processType;
	}

	public void setProcessType(Byte processType) {
		this.processType = processType;
	}

	public Double getExpectPrice() {
		return expectPrice;
	}

	public void setExpectPrice(Double expectPrice) {
		this.expectPrice = expectPrice;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public Long getCondCounty() {
		return condCounty;
	}

	public void setCondCounty(Long condCounty) {
		this.condCounty = condCounty;
	}

	public Long getCondTown() {
		return condTown;
	}

	public void setCondTown(Long condTown) {
		this.condTown = condTown;
	}

	public Integer getCondStaffNum() {
		return condStaffNum;
	}

	public void setCondStaffNum(Integer condStaffNum) {
		this.condStaffNum = condStaffNum;
	}

	public String getCondDemand() {
		return condDemand;
	}

	public void setCondDemand(String condDemand) {
		this.condDemand = condDemand;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public Integer getReceivedEnterpriseId() {
		return receivedEnterpriseId;
	}

	public void setReceivedEnterpriseId(Integer receivedEnterpriseId) {
		this.receivedEnterpriseId = receivedEnterpriseId;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Byte getCreateUserType() {
		return createUserType;
	}

	public void setCreateUserType(Byte createUserType) {
		this.createUserType = createUserType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
