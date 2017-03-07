package com.ad.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

/**活动推广
 * */
@Entity
@Table(name="ad_activity")
public class Activity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String title;//标题
	
	private String source;//来源
	
	private String content;//内容
	
	private Long province;	//省
	
	private Long city;	//市
	
	private Long county;	//区县
	
	private Long town;	//镇/乡/街道
	
	@Transient
	private String detailAddr;//详细地址，列表页用
	
	@Column(name="update_by")
	private Integer updateBy; //操作用户Id
	
	@Transient
	private String realName; //管理员名称
	
	@Transient
	private String imgUrl;//第一张图的地址，首页用
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name="update_time")
	private Date updateTime; //更改时间
	
	public Activity(){}
	
	public Activity(Integer id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public Activity(Integer id, Long province, Long city,
			Long county, String title, Date updateTime) {
		super();
		this.id = id;
		this.title = title;
		this.province = province;
		this.city = city;
		this.county = county;
		this.updateTime = updateTime;
	}

	public Activity(Integer id, String title, String source, Long province,
			Long city, Long county, Long town, String realName, Date updateTime) {
		this.id = id;
		this.title = title;
		this.source = source;
		this.province = province;
		this.city = city;
		this.county = county;
		this.town = town;
		this.realName = realName;
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getCounty() {
		return county;
	}

	public void setCounty(Long county) {
		this.county = county;
	}

	public Long getTown() {
		return town;
	}

	public void setTown(Long town) {
		this.town = town;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}
	
}
