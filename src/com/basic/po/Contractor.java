package com.basic.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**个人承包商，快产专家
 * */
@Entity
@Table(name="basic_contractor")
public class Contractor {

	@Id
	@Column(name="person_id")
	private Integer personId;	//该主键从Person表获得
	
	@Column(name="process_type")
	private String processType;	//加工类型
	
	@Column(name="process_year")
	private Short processYear;	//加工年限
	
	@Column(name="worker_amount")
	private Short workerAmount;	//工人数量
	
	@Column(name="work_space")
	private Byte workSpace;//工作场地
	
	private String skill;	//专业技能
	
	private String quote;	//报价，填写接单价格规则
	
	private String equipment;	//生产设备
	
	@Column(name="process_desc")
	private String processDesc;	//加工说明

	@Column(name="costume_code")
	private String costumeCode;//服饰类型编码,多个编码之间用,分隔
	
	
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
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

	public String getCostumeCode() {
		return costumeCode;
	}

	public void setCostumeCode(String costumeCode) {
		this.costumeCode = costumeCode;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Byte getWorkSpace() {
		return workSpace;
	}

	public void setWorkSpace(Byte workSpace) {
		this.workSpace = workSpace;
	}
	
}
