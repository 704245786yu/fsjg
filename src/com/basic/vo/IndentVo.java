package com.basic.vo;

public class IndentVo {
	private Integer id;
	private Long indentNum;//订单编号
	private String indentName;//订单名称
	private Long countNum;//报价人数
	
	public IndentVo(){}
	
	public IndentVo(Integer id, Long indentNum, String indentName,
			Long countNum) {
		super();
		this.id = id;
		this.indentNum = indentNum;
		this.indentName = indentName;
		this.countNum = countNum;
	}
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
	public Long getCountNum() {
		return countNum;
	}
	public void setCountNum(Long countNum) {
		this.countNum = countNum;
	}
	
}
