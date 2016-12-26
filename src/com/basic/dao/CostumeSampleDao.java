package com.basic.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.basic.po.CostumeSample;
import com.basic.vo.CostumeSampleVo;
import com.common.BaseDao;
import com.common.dto.BootTablePageDto;

@Repository
public class CostumeSampleDao extends BaseDao<Integer, CostumeSample>{
	
	public BootTablePageDto<CostumeSampleVo> findByPage(Long num,String name,String enterpriseName, Date beginDate,Date endDate, int offset, int limit, Long total){
		StringBuffer hql = new StringBuffer("from CostumeSample c, Enterprise e where c.enterpriseNum = e.number");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		if(enterpriseName.length()>0){
			hql.append(" and e.enterpriseName like :enterpriseName");
			params.add("enterpriseName");
			values.add("%"+enterpriseName+"%");
		}
		if(name.length()>0){
			hql.append(" and c.name like :name");
			params.add("name");
			values.add("%"+name+"%");
		}
		if(num!=null){
			hql.append(" and c.num like :num");
			params.add("num");
			values.add(num);
		}
		if(beginDate!=null && endDate!=null){
			hql.append(" and c.updateTime between :beginDate and :endDate");
			params.add("beginDate");
			values.add(beginDate);
			params.add("endDate");
			values.add(endDate);
		}
		if(total==null){
			StringBuffer countSql = new StringBuffer("select count(1) ");
			countSql.append(hql);
			total = super.getCount(countSql.toString(), params.toArray(new String[]{}), values.toArray(new Object[]{}));
			if(total==0)
				return new BootTablePageDto<CostumeSampleVo>(total,new ArrayList<CostumeSampleVo>());
		}
		StringBuffer sql = new StringBuffer("select c.id as id, c.enterpriseNum as enterpriseNum, e.enterpriseName as enterpriseName, c.num as num, c.name as name, c.costumeCode as costumeCode, c.orderAmount as orderAmount, c.price as price, c.saleMarket as saleMarket, c.support as support, c.processDesc as processDesc, c.smImg as smImg, c.detailImg as detailImg, c.updateTime as updateTime ");
		sql.append(hql);
		List<CostumeSampleVo> list = super.findByPage(sql.toString(), offset, limit, params.toArray(new String[]{}), values.toArray(new Object[]{}),CostumeSampleVo.class);
		return new BootTablePageDto<CostumeSampleVo>(total,list);
	}
	
	/**获取样品图片*/
	public String[] getImgs(int id){
		String hql = "select smImg,detailImg from CostumeSample where id =:id";
		Object[] obj = (Object[])super.find(hql, new String[]{"id"}, new Integer[]{id}).get(0);
		String[] imgs = new String[2];
		for(int i=0; i<obj.length; i++){
			if(obj[i] != null)
				imgs[i] = String.valueOf(obj[i]);
			else
				imgs[i] = "";
		}
		return imgs;
	}
}
