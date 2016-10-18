package com.ad.biz;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ad.dao.AfficheDao;
import com.ad.po.Affiche;
import com.ad.vo.AfficheVo;
import com.common.BaseBiz;
import com.util.DateTransform;

@Service
public class AfficheBiz extends BaseBiz<AfficheDao,Integer,Affiche>{

	public Affiche getById(int id){
		return dao.findById(id);
	}
	
	public List<AfficheVo> findByParam(String title, String beginDate, String endDate){
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		return dao.findByParam(title, beginTime, endTime);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllTitle(){
		String hql = "select id,title from Affiche";
		return (List<Object[]>)dao.find(hql,new String[]{},new Object[]{});
	}
}