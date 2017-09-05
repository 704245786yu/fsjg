package com.ad.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ad.po.Activity;
import com.common.BaseDao;

@Repository
public class ActivityDao extends BaseDao<Integer, Activity>{

	/*public List<TradeNewsVo> findByParam(String title, Date beginDate, Date endDate){
		StringBuffer hql = new StringBuffer("select a.id as id, a.title as title, a.source as source, u.realName as realName, a.updateTime as updateTime from Activity a, User u where a.updateBy = u.id ");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		if(title.length() > 0){
			hql.append(" and title like :title");
			params.add("title");
			values.add("%"+title+"%");
		}
		if(beginDate != null){
			hql.append(" and updateTime between :beginDate and :endDate");
			params.add("beginDate");
			values.add(beginDate);
			params.add("endDate");
			values.add(endDate);
		}
		return super.find(hql.toString(), params.toArray(new String[]{}), values.toArray(new Object[]{}),TradeNewsVo.class);
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Activity> findByParam(String title, Date beginDate, Date endDate){
		StringBuffer hql = new StringBuffer("select new Activity(a.id as id, a.title as title, a.duration as duration, a.source as source, a.type as type, a.province as province, a.city as city, a.county as county, a.town as town, u.realName as realName, a.updateTime as updateTime) from Activity a, User u where a.updateBy = u.id ");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		if(title.length() > 0){
			hql.append(" and title like :title");
			params.add("title");
			values.add("%"+title+"%");
		}
		if(beginDate != null){
			hql.append(" and updateTime between :beginDate and :endDate");
			params.add("beginDate");
			values.add(beginDate);
			params.add("endDate");
			values.add(endDate);
		}
		return (List<Activity>)super.find(hql.toString(), params.toArray(new String[]{}), values.toArray(new Object[]{}));
	}
}
