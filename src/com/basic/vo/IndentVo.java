package com.basic.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class IndentVo {
	private Integer id;
	private Long indentNum;//订单编号
	private String indentName;//订单名称
	private Integer quantity;//订单数量
	private Double expectPrice;//预期价
	private Long countNum;//报价人数
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date latestTime;//报价日期
	
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
	public Double getExpectPrice() {
		return expectPrice;
	}
	public void setExpectPrice(Double expectPrice) {
		this.expectPrice = expectPrice;
	}
	public Long getCountNum() {
		return countNum;
	}
	public void setCountNum(Long countNum) {
		this.countNum = countNum;
	}
	public Date getLatestTime() {
		return latestTime;
	}
	public void setLatestTime(Date latestTime) {
		this.latestTime = latestTime;
	}
	
}
