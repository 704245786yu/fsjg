package com.basic.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.basic.po.Enterprise;
import com.basic.po.EnterpriseCostumeRela;
import com.common.BaseDao;
import com.common.dto.BootTablePageDto;

@Repository
public class EnterpriseDao extends BaseDao<Integer, Enterprise>{
	
	@Autowired
	private EnterpriseCostumeRelaDao relaDao;
	
	@Transactional
	public void saveBatchEntity(List<Enterprise> enterpriseList){
		for(int i=0; i<enterpriseList.size(); i++){
			Enterprise enterprise = enterpriseList.get(i);
			super.persist(enterprise);
			int id = enterprise.getId();
			List<Integer> costumeCode = enterprise.getCostumeCode();
			for(int j=0; j<costumeCode.size(); j++){
				EnterpriseCostumeRela rela = new EnterpriseCostumeRela(id, costumeCode.get(j));
				relaDao.save(rela);
			}
		}
	}
	
	/**检查企业是否已经存在*/
	public boolean isExsit(String enterpriseName){
		String hql = "select count(1) from Enterprise where enterpriseName =:enterpriseName";
		long amount = super.getCount(hql, new String[]{"enterpriseName"}, new String[]{enterpriseName});
		if(amount > 0)
			return true;
		else
			return false;
	}
	
	/**获取关联的用户ID*/
	public Integer getUserId(int id){
		String sql = "select user_id from basic_enterprise where id =:id";
		return (Integer)super.findByNativeSql(sql, new String[]{"id"}, new Integer[]{id}).get(0);
	}
	
	/**最新入住的企业*/
	@SuppressWarnings("unchecked")
	public List<Enterprise> getNewest(){
		String hql = "from Enterprise order by id desc";
		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(0).setMaxResults(5);
		return query.list();
	}
	
	/**模糊匹配企业名称、加工类型、主营产品、工厂描述
	 * @return id、企业名称、加工类型、员工人数、工厂介绍、所在地区、主营产品、QQ、企业logo
	 * */
	public BootTablePageDto<Enterprise> search(String keyword, String processType, List<Integer> costumeCategoryCodes){
//		System.out.println(keyword);
//		System.out.println(processType);
//		JacksonJson.printBeanToJson(costumeCategoryCodes);
		StringBuffer countSql = new StringBuffer("select count(1)");
		StringBuffer subSql = new StringBuffer(" from basic_enterprise where enterprise_name like :keyword or description like :keyword");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		params.add("keyword");
		values.add("%"+keyword+"%");
		if(processType != null){
			subSql.append(" or process_type like :processType");
			params.add("processType");
			values.add("%"+processType+"%");
		}
		if(costumeCategoryCodes.size() != 0){
			subSql.append(" or id in (select distinct enterprise_id from basic_enterprise_costume_rela where costume_code in (:costumeCategoryCodes))");
			params.add("costumeCategoryCodes");
			values.add(costumeCategoryCodes);
		}
		countSql.append(subSql);
		BigInteger bigInt = (BigInteger)super.findByNativeSql(countSql.toString(), params, values).get(0);
		long total = bigInt.longValue();
		
		StringBuffer sql = new StringBuffer("select id, enterprise_name as enterpriseName, process_type as processType, staff_number as staffNumber, description, province, city, county");
		sql.append(subSql);
		List<Object[]> scalars = new ArrayList<Object[]>();
		scalars.add(new Object[]{"id",StandardBasicTypes.INTEGER});
		scalars.add(new Object[]{"enterpriseName",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"processType",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"staffNumber",StandardBasicTypes.INTEGER});
		scalars.add(new Object[]{"description",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"province",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"city",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"county",StandardBasicTypes.LONG});
		List<Enterprise> enterprises = super.findByNativeSql(sql.toString(), params, values, scalars, 0, 10);
		return new BootTablePageDto<Enterprise>(total, enterprises);
	}
	
	/**模糊匹配企业名称、加工类型、工厂描述
	 * @return id、企业名称、加工类型、员工人数、工厂介绍、所在地区、主营产品、QQ、企业logo
	 * */
	public BootTablePageDto<Enterprise> search2(Long province,Long city,Long county,Long town, Integer[] costumeCode){
//		System.out.println(keyword);
//		System.out.println(processType);
//		JacksonJson.printBeanToJson(costumeCategoryCodes);
		StringBuffer countSql = new StringBuffer("select count(1)");
		StringBuffer subSql = new StringBuffer(" from basic_enterprise where enterprise_name like :keyword or description like :keyword");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		params.add("keyword");
		/*values.add("%"+keyword+"%");
		if(processType != null){
			subSql.append(" or process_type like :processType");
			params.add("processType");
			values.add("%"+processType+"%");
		}
		if(costumeCategoryCodes.size() != 0){
			subSql.append(" or id in (select distinct enterprise_id from basic_enterprise_costume_rela where costume_code in (:costumeCategoryCodes))");
			params.add("costumeCategoryCodes");
			values.add(costumeCategoryCodes);
		}*/
		countSql.append(subSql);
		BigInteger bigInt = (BigInteger)super.findByNativeSql(countSql.toString(), params, values).get(0);
		long total = bigInt.longValue();
		
		StringBuffer sql = new StringBuffer("select id, enterprise_name as enterpriseName, process_type as processType, staff_number as staffNumber, description, province, city, county");
		sql.append(subSql);
		List<Object[]> scalars = new ArrayList<Object[]>();
		scalars.add(new Object[]{"id",StandardBasicTypes.INTEGER});
		scalars.add(new Object[]{"enterpriseName",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"processType",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"staffNumber",StandardBasicTypes.INTEGER});
		scalars.add(new Object[]{"description",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"province",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"city",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"county",StandardBasicTypes.LONG});
		List<Enterprise> enterprises = super.findByNativeSql(sql.toString(), params, values, scalars, 0, 10);
		return new BootTablePageDto<Enterprise>(total, enterprises);
	}
	
	/**根据服饰类型编码获取关联的企业*/
	@SuppressWarnings("unchecked")
	public BootTablePageDto<Enterprise> getByCostumeCode(int costumeCode){
		String hql = "select count(distinct enterpriseId) from EnterpriseCostumeRela where costumeCode =:costumeCode)";
		Long total = (Long)super.find(hql, new String[]{"costumeCode"}, new Integer[]{costumeCode}).get(0);
		
		hql = "from Enterprise where id in (select distinct enterpriseId from EnterpriseCostumeRela where costumeCode =:costumeCode)";
		List<Enterprise> list = (List<Enterprise>)super.findByPage(hql, 0, 10, new String[]{"costumeCode"}, new Integer[]{costumeCode});
		return new BootTablePageDto<Enterprise>(total, list);
	}
}
