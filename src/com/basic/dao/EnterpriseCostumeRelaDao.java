package com.basic.dao;

import org.springframework.stereotype.Repository;

import com.basic.po.EnterpriseCostumeRela;
import com.common.BaseDao;

@Repository
public class EnterpriseCostumeRelaDao extends BaseDao<Integer, EnterpriseCostumeRela>{

	public void delByEnterpriseId(int enterpriseId){
		String hql = "delete EnterpriseCostumeRela where enterpriseId =:enterpriseId";
		super.executeUpdate(hql, new String[]{"enterpriseId"}, new Integer[]{enterpriseId});
	}
}
