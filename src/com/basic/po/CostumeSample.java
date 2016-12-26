package com.basic.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**服饰样品*/
@Entity
@Table(name="basic_costume_sample")
public class CostumeSample {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="enterprise_num")
	private Integer enterpriseNum;
	
	private Long num;//样品编号
	
	private String name;//样品名称
	
	@Column(name="costume_code")
	private Integer costumeCode;//服饰类型编码
	
	@Column(name="order_amount")
	private Integer orderAmount;//最低下单数量
	
	private Float price;//单价
	
	@Column(name="sale_market")
	private Byte saleMarket;//销售市场
	
	private String support;//0：支持该款 1：贴牌生产 2：支持看样,可多选，用逗号分割
	
	@Column(name="process_desc")
	private String processDesc;//加工说明
	
	@Column(name="sm_img")
	private String smImg;//小图url，多个图片用逗号分隔
	
	@Column(name="detail_img")
	private String detailImg;//详细信息图片url，多个图片用逗号分隔
	
	@Column(name="update_by")
	private Integer updateBy;//操作用户ID,0表示用户自己
	
	private Date updateTime;//更改时间

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEnterpriseNum() {
		return enterpriseNum;
	}

	public void setEnterpriseNum(Integer enterpriseNum) {
		this.enterpriseNum = enterpriseNum;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCostumeCode() {
		return costumeCode;
	}

	public void setCostumeCode(Integer costumeCode) {
		this.costumeCode = costumeCode;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Byte getSaleMarket() {
		return saleMarket;
	}

	public void setSaleMarket(Byte saleMarket) {
		this.saleMarket = saleMarket;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getProcessDesc() {
		return processDesc;
	}

	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}

	public String getSmImg() {
		return smImg;
	}

	public void setSmImg(String smImg) {
		this.smImg = smImg;
	}

	public String getDetailImg() {
		return detailImg;
	}

	public void setDetailImg(String detailImg) {
		this.detailImg = detailImg;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
