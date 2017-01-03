package com.ad.biz;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ad.dao.TradeNewsDao;
import com.ad.po.TradeNews;
import com.ad.vo.TradeNewsVo;
import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;
import com.util.DateTransform;

@Service
public class TradeNewsBiz extends BaseBiz<TradeNewsDao,Integer,TradeNews>{

	public TradeNews getById(int id){
		return dao.findById(id);
	}
	
	public List<TradeNewsVo> findByParam(String title, String beginDate, String endDate){
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
		String hql = "select id,title from TradeNews";
		return (List<Object[]>)dao.find(hql,new String[]{},new Object[]{});
	}
	
	/**获取最新的10个咨询*/
	@SuppressWarnings("unchecked")
	public List<TradeNews> getTen(){
		String hql = "from TradeNews order by updateTime desc";
		return (List<TradeNews>)dao.findByPage(hql, 0, 10, new String[]{}, new Object[]{});
	}
	
	@SuppressWarnings("unchecked")
	public BootTablePageDto<TradeNews> getTitleByPage(int offset, int limit, Long total){
		String hql = "select new TradeNews(id, title, updateTime) from TradeNews order by updateTime desc";
		if(total==null)
			total = dao.getCount();
		List<TradeNews> list = (List<TradeNews>)dao.findByPage(hql, offset, limit, new String[]{}, new Object[]{});
		return new BootTablePageDto<TradeNews>(total,list);
	}
}