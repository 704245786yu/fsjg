package com.basic.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.common.BaseDao;
import com.po.basic.Enterprise;

@Repository
public class EnterpriseDao extends BaseDao<Integer, Enterprise>{

	/**最新入住的企业*/
	@SuppressWarnings("unchecked")
	public List<Enterprise> getNewest(){
		String hql = "from Enterprise order by id desc";
		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(0).setMaxResults(5);
		return query.list();
	}
}
