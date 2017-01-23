package com.ad.biz;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ad.dao.ActivityDao;
import com.ad.po.Activity;
import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;
import com.util.DateTransform;

@Service
public class ActivityBiz extends BaseBiz<ActivityDao,Integer,Activity>{

	public Activity getById(int id){
		return dao.findById(id);
	}
	
	public List<Activity> findByParam(String title, String beginDate, String endDate){
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		return dao.findByParam(title, beginTime, endTime);
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getAllTitle(){
		String hql = "select id,title from TradeNews";
		return (List<Object[]>)dao.find(hql,new String[]{},new Object[]{});
	}*/
	
	/**获取5个轮播图活动*/
	@SuppressWarnings("unchecked")
	public List<Activity> getFive(){
		String hql = "from Activity";
		return (List<Activity>)dao.findByPage(hql, 0, 5, new String[]{}, new Object[]{});
	}
	
	/**获取前26个活动标题*/
	@SuppressWarnings("unchecked")
	public List<Activity> getHomeList(){
		String hql = "select new Activity(id,title) from Activity";
		return (List<Activity>)dao.findByPage(hql, 0, 26, new String[]{}, new Object[]{});
	}
	
	@SuppressWarnings("unchecked")
	public BootTablePageDto<Activity> getTitleByPage(int offset, int limit, Long total){
		String hql = "select new Activity(id, title, updateTime) from TradeNews order by updateTime desc";
		if(total==null)
			total = dao.getCount();
		List<Activity> list = (List<Activity>)dao.findByPage(hql, offset, limit, new String[]{}, new Object[]{});
		return new BootTablePageDto<Activity>(total,list);
	}
}