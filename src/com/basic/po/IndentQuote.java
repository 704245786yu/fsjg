package com.basic.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="process_indent_quote")
public class IndentQuote {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="indent_num")
	private Long indentNum;	//订单编号
	
	@Column(name="enterprise_id")
	private Integer enterpriseId;	//企业Id
	
	private Double quote;	//报价
	
	@Column(name = "create_time")
	private Date createTime;	//创建时间

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

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Double getQuote() {
		return quote;
	}

	public void setQuote(Double quote) {
		this.quote = quote;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
