package com.basic.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.basic.po.Enterprise;
import com.basic.po.EnterpriseCostumeRela;
import com.common.BaseDao;

@Repository
public class EnterpriseDao extends BaseDao<Integer, Enterprise>{
	
	@Autowired
	private EnterpriseCostumeRelaDao relaDao;
	
	@Transactional
	public void saveBatchEntity(List<Enterprise> enterpriseList){
		for(int i=0; i<enterpriseList.size(); i++){
			Enterprise enterprise = enterpriseList.get(i);
			super.save(enterprise);
			int id = enterprise.getId();
			List<Integer> costumeCode = enterprise.getCostumeCode();
			for(int j=0; j<costumeCode.size(); j++){
				EnterpriseCostumeRela rela = new EnterpriseCostumeRela(id, costumeCode.get(j));
				relaDao.save(rela);
			}
		}
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
}
