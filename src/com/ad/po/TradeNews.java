package com.ad.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**公告
 * */
@Entity
@Table(name="ad_trade_news")
public class TradeNews {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String title;//公告标题
	
	private String source;//来源
	
	private String content;//公告内容
	
	@Column(name="update_by")
	private Integer updateBy; //操作用户Id
	
	@Column(name="update_time")
	private Date updateTime; //更改时间
	
	public TradeNews(){}
	
	public TradeNews(Integer id, String title, Date updateTime) {
		this.id = id;
		this.title = title;
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
