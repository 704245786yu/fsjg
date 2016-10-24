package com.ad.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ad.dao.AdPositionDao;
import com.ad.po.AdPosition;
import com.common.BaseBiz;

@Service
public class AdPositionBiz extends BaseBiz<AdPositionDao,Integer,AdPosition>{

	/**根据广告位编码模糊匹配查询*/
	@SuppressWarnings("unchecked")
	public List<AdPosition> getByCode(String code){
		String hql = "from AdPosition where code like :code";
		return (List<AdPosition>)dao.find(hql, new String[]{"code"}, new String[]{"%"+code+"%"});
	}
}