package com.ad.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**广告位
 * */
@Entity
@Table(name="ad_position")
public class AdPosition {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;//广告位名称
	
	private String code;//广告位编码
	
	private String size;//尺寸
	
	private Byte sort;//广告位排序
	
	private String title;//广告标题
	
	private String img;//广告图片URL
	
	@Column(name="link_type")
	private Byte linkType;//链接类型 0:外部网址 1:内部工厂详情页
	
	private String link;//链接地址
	
	@Column(name="update_by")
	private Integer updateBy; //操作用户Id
	
	@Column(name="update_time")
	private Date updateTime; //更改时间
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Byte getSort() {
		return sort;
	}

	public void setSort(Byte sort) {
		this.sort = sort;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Byte getLinkType() {
		return linkType;
	}

	public void setLinkType(Byte linkType) {
		this.linkType = linkType;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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
