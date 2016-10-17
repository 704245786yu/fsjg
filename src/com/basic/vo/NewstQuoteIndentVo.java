package com.basic.vo;

public class NewstQuoteIndentVo {
	private Long indentNum;//订单编号
	private String indentName;//订单名称
	private Integer quantity;//订单数量
	private Long countNum;//报价人数
	private Byte processType;//加工类型
	
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
	public Long getCountNum() {
		return countNum;
	}
	public void setCountNum(Long countNum) {
		this.countNum = countNum;
	}
	public Byte getProcessType() {
		return processType;
	}
	public void setProcessType(Byte processType) {
		this.processType = processType;
	}
	
}