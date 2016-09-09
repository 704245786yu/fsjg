package com.basic.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**我确认的订单*/
public class ConfirmIndentVo {
	private Long indentNum;//订单编号
	private String indentName;//订单名称
	private Double expectPrice;//预期价
	private Double price;//成交价
	private String enterpriseName;//工厂名称
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date updateTime;//接单日期
	
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
	public Double getExpectPrice() {
		return expectPrice;
	}
	public void setExpectPrice(Double expectPrice) {
		this.expectPrice = expectPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
