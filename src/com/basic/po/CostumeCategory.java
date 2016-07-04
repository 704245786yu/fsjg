package com.basic.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.po.NestTreePO;

/**服饰分类
 * @author zhiyu
 * */
@Entity
@Table(name="basic_costume_category")
public class CostumeCategory extends NestTreePO {

	@Column(name="category_name")
	private String categoryName;	//服饰类型名称
	
	@Column(name="category_code")
	private Integer categoryCode;	//服饰类型编码
	
	@Column(name="update_by")
	private Integer updateBy;
	
	@Column(name="update_time")
	private Date updateTime;

	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Integer categoryCode) {
		this.categoryCode = categoryCode;
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
