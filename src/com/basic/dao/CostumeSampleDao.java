package com.basic.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.basic.po.CostumeSample;
import com.basic.vo.CostumeSampleVo;
import com.basic.vo.Sample2Vo;
import com.basic.vo.SampleVo;
import com.common.BaseDao;
import com.common.dto.BootTablePageDto;

@Repository
public class CostumeSampleDao extends BaseDao<Integer, CostumeSample>{
	
	public BootTablePageDto<SampleVo> search(Long province,Long city,Long county,Long town, 
			Integer[] costumeCodes,String keyword,int offset,int limit,Long total){
		StringBuilder subSql = new StringBuilder(" from CostumeSample c, Enterprise e where c.enterpriseNum = e.number and name like :keyword ");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		//关键字匹配订单名称、订单说明
		params.add("keyword");
		values.add("%"+keyword+"%");
		
		//接单用户的省市区县乡镇
		if(province != null){
			subSql.append(" and e.province =:province");
			params.add("province");
			values.add(province);
		}
		if(city != null){
			subSql.append(" and e.city =:city");
			params.add("city");
			values.add(city);
		}
		if(county != null){
			subSql.append(" and e.county =:county");
			params.add("county");
			values.add(county);
		}
		if(town != null){
			subSql.append(" and e.town =:town");
			params.add("town");
			values.add(town);
		}
		
		//服饰类型
		if(costumeCodes != null && costumeCodes.length>0){
			StringBuilder subCostumeCode = new StringBuilder(" c.costumeCode like '%"+costumeCodes[0]+"%'");
			for(int i=1; i<costumeCodes.length; i++)
				subCostumeCode.append(" or c.costumeCode like '%"+costumeCodes[i]+"%'");
			subSql.append(" and ("+subCostumeCode.toString()+")");
		}
		
		String[] paramAry = params.toArray(new String[]{});
		String[] valueAry = values.toArray(new String[]{});
		//若有total表示翻页操作，无须再次查询total
		if(total == null){
			StringBuilder countSql = new StringBuilder("select count(1)");
			countSql.append(subSql);
			total = super.getCount(countSql.toString(), paramAry, valueAry);
			if(total==0)
				return new BootTablePageDto<SampleVo>(total,new ArrayList<SampleVo>());
		}
		
		StringBuilder sql = new StringBuilder("select e.enterpriseName as enterpriseName, e.auditState as auditState, c.num as num, c.name as name, c.smImg as img ");
		sql.append(subSql).append(" order by c.updateTime desc");
		List<SampleVo> samples = super.findByPage(sql.toString(), offset, limit, paramAry, valueAry, SampleVo.class);
		return new BootTablePageDto<SampleVo>(total, samples);
	}
	
	public BootTablePageDto<CostumeSampleVo> findByPage(Long num,String name,String enterpriseName, Date beginDate,Date endDate, int offset, int limit, Long total){
		StringBuilder hql = new StringBuilder("from CostumeSample c, Enterprise e where c.enterpriseNum = e.number");
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
			StringBuilder countSql = new StringBuilder("select count(1) ");
			countSql.append(hql);
			total = super.getCount(countSql.toString(), params.toArray(new String[]{}), values.toArray(new Object[]{}));
			if(total==0)
				return new BootTablePageDto<CostumeSampleVo>(total,new ArrayList<CostumeSampleVo>());
		}
		StringBuilder sql = new StringBuilder("select c.id as id, c.enterpriseNum as enterpriseNum, e.enterpriseName as enterpriseName, c.num as num, c.name as name, c.costumeCode as costumeCode, c.orderAmount as orderAmount, c.price as price, c.saleMarket as saleMarket, c.support as support, c.processDesc as processDesc, c.smImg as smImg, c.detailImg as detailImg, c.updateTime as updateTime ");
		sql.append(hql);
		List<CostumeSampleVo> list = super.findByPage(sql.toString(), offset, limit, params.toArray(new String[]{}), values.toArray(new Object[]{}),CostumeSampleVo.class);
		return new BootTablePageDto<CostumeSampleVo>(total,list);
	}
	
	/**获取工厂样品展示列表*/
	public BootTablePageDto<Sample2Vo> getEntSample(long enterpriseNum,Long costumeCode,int offset, int limit, Long total){
		StringBuilder hql = new StringBuilder(" from basic_costume_sample where enterprise_num =:enterpriseNum ");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		params.add("enterpriseNum");
		values.add(enterpriseNum);
		if(costumeCode != null){
			hql.append(" and costume_code like :costumeCode");
			params.add("costumeCode");
			values.add(costumeCode+"%");
		}
		if(total==null){
			StringBuilder countSql = new StringBuilder("select count(1) ");
			countSql.append(hql);
			BigInteger bigInt = (BigInteger)super.findByNativeSql(countSql.toString(), params, values).get(0);
			total = bigInt.longValue();
			if(total==0)
				return new BootTablePageDto<Sample2Vo>(total,new ArrayList<Sample2Vo>());
		}
		StringBuilder sql = new StringBuilder("select num as num, name as name, sm_img as img");
		sql.append(hql);
		List<Object[]> scalars = new ArrayList<Object[]>();
		scalars.add(new Object[]{"num",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"name",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"img",StandardBasicTypes.STRING});
		List<Sample2Vo> list = super.findByNativeSql(sql.toString(), params, values, scalars, offset, limit, Sample2Vo.class);
		return new BootTablePageDto<Sample2Vo>(total,list);
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
