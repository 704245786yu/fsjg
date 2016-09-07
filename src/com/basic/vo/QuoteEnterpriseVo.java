package com.basic.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**报价企业：用于“我收到的报价”页面展示报价企业的信息
 * */
public class QuoteEnterpriseVo{
	private Integer id;	//企业ID
	private String enterpriseName;
	private String linkman;
	private Long telephone;
	private Double quote;	//报价
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date quoteDate;	//报价日期
	private Integer staffNumber;	//员工人数
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Long getTelephone() {
		return telephone;
	}
	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}
	public Double getQuote() {
		return quote;
	}
	public void setQuote(Double quote) {
		this.quote = quote;
	}
	public Date getQuoteDate() {
		return quoteDate;
	}
	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}
	public Integer getStaffNumber() {
		return staffNumber;
	}
	public void setStaffNumber(Integer staffNumber) {
		this.staffNumber = staffNumber;
	}

}
