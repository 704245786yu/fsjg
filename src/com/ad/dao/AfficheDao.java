package com.ad.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ad.po.Affiche;
import com.ad.vo.AfficheVo;
import com.common.BaseDao;

@Repository
public class AfficheDao extends BaseDao<Integer, Affiche>{

	public List<AfficheVo> findByParam(String title, Date beginDate, Date endDate){
		StringBuffer hql = new StringBuffer("select a.id as id, a.title as title, u.realName as realName, a.updateTime as updateTime from Affiche a, User u where a.updateBy = u.id ");
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
		return super.find(hql.toString(), params.toArray(new String[]{}), values.toArray(new Object[]{}),AfficheVo.class);
	}
}
