package com.basic.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**个人承包商，快产专家
 * */
@Entity
@Table(name="basic_person_contractor")
public class PersonContractor {

	@Id
	private Integer id;	//该主键从Person表获得
	
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

	@Column(name="create_by")
	private Integer createBy; //创建人。0：表示用户自行注册，非0表示由平台管理人员录入
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name="create_time")
	private Date createTime; //创建时间(注册时间)
	
	@Column(name="update_by")
	private Integer updateBy; //更改人。0：表示用户自行更改，非0表示由平台管理人员更改
	
	@Column(name="update_time")
	private Date updateTime; //更改时间
	
	
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

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
