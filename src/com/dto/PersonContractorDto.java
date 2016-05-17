package com.dto;

public class PersonContractorDto {
	//Person表信息
	private String realName;
	
	//PersonContractor表信息
	private Integer id;	//该主键从Person表获得
	
	private Byte processType;	//加工类型 0：清加工 1：经销 2：自营出口 3：其它
	
	private Short processYear;	//加工年限
	
	private Short workerAmount;	//工人数量
	
	private String quote;	//报价，填写接单价格规则
	
	private String equipment;	//生产设备
	
	private String processDesc;	//加工说明

	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Byte getProcessType() {
		return processType;
	}

	public void setProcessType(Byte processType) {
		this.processType = processType;
	}

	public Short getProcessYear() {
		return processYear;
	}

	public void setProcessYear(Short processYear) {
		this.processYear = processYear;
	}

	public Short getWorkerAmount() {
		return workerAmount;
	}

	public void setWorkerAmount(Short workerAmount) {
		this.workerAmount = workerAmount;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getProcessDesc() {
		return processDesc;
	}

	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}
	
}
