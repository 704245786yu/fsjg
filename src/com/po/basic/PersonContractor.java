package com.po.basic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**个人承包商，快产专家
 * */
@Entity
@Table(name="basic_person_contractor")
public class PersonContractor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="person_id")
	private Integer personId;	//basic_person的主键ID
	
	@Column(name="process_type")
	private Byte processType;	//加工类型 0：清加工 1：经销 2：自营出口 3：其它
	
	@Column(name="process_year")
	private Short processYear;	//加工年限
	
	@Column(name="worker_amount")
	private Short workerAmount;	//工人数量
	
	private String quote;	//报价，填写接单价格规则
	
	private String equipment;	//生产设备
	
	@Column(name="process_desc")
	private String processDesc;	//加工说明

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
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
