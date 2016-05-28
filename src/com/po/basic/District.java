package com.po.basic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 省市街道信息
 * */
@Entity
@Table(name = "basic_district")
public class District {
	
	@Id
	@Column(name = "district_code")
	private Long districtCode;
	@Column(name = "district_name")
	private String districtName;
	@Column(name = "p_code")
	private Long pCode;
	@Column(name = "update_by")
	private Integer updateBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "update_time")
	private Date updateTime;

	public District(){}
	
	public District(Long districtCode, String districtName, Long pCode, Integer updateBy) {
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.pCode = pCode;
		this.updateBy = updateBy;
	}
	
	public Long getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Long getpCode() {
		return pCode;
	}

	public void setpCode(Long pCode) {
		this.pCode = pCode;
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
