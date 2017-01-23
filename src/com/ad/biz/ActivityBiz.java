package com.ad.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		List<Activity> list = (List<Activity>)dao.findByPage(hql, 0, 5, new String[]{}, new Object[]{});
		//获取每个活动的第一个图片 http://blog.csdn.net/g631521612/article/details/11928301
		for(int i=0; i<list.size(); i++){
			Activity activity = list.get(i);
			String regex = "src=\"(.*?)\"";
			Pattern pa = Pattern.compile(regex, Pattern.DOTALL);    
			Matcher ma = pa.matcher(activity.getContent());    
			if(ma.find())    
			{  
				String srcStr = ma.group();
				int begin = srcStr.indexOf("\"")+1;
                int end = srcStr.lastIndexOf("\"");
                String imgUrl = srcStr.substring(begin, end);
                activity.setImgUrl(imgUrl);
			}
		}
		return list;
	}
	
	/**获取前26个活动标题*/
	@SuppressWarnings("unchecked")
	public List<Activity> getHomeList(){
		String hql = "select new Activity(id,title) from Activity";
		return (List<Activity>)dao.findByPage(hql, 0, 26, new String[]{}, new Object[]{});
	}
	
	@SuppressWarnings("unchecked")
	public BootTablePageDto<Activity> getTitleByPage(Long province, Long city, Long county, Long town, int offset, int limit, Long total){
		StringBuilder hql = new  StringBuilder(" from Activity where 1=1 ");
		List<String> params = new ArrayList<String>();
		List<Long> values = new ArrayList<Long>();
		if(province!=null){
			hql.append(" and province =:province");
			params.add("province");
			values.add(province);
		}
		if(city!=null){
			hql.append(" and city =:city");
			params.add("city");
			values.add(city);
		}
		if(county!=null){
			hql.append(" and county =:county");
			params.add("county");
			values.add(county);
		}
		if(town!=null){
			hql.append(" and town =:town");
			params.add("town");
			values.add(town);
		}
		
		if(total==null){
			StringBuilder countSql = new StringBuilder("select count(1)");
			countSql.append(hql);
			total = dao.getCount(countSql.toString(), params.toArray(new String[]{}), values.toArray(new Long[]{}));
			if(total == 0)
				return new BootTablePageDto<Activity>(total, new ArrayList<Activity>());
		}
		List<Activity> list = (List<Activity>)dao.findByPage("select new Activity(id, title, updateTime) "+hql.toString()+" order by updateTime desc", offset, limit, params.toArray(new String[]{}), values.toArray(new Long[]{}));
		return new BootTablePageDto<Activity>(total,list);
	}
}