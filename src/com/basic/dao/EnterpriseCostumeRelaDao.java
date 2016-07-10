package com.basic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.basic.po.EnterpriseCostumeRela;
import com.common.BaseDao;

@Repository
public class EnterpriseCostumeRelaDao extends BaseDao<Integer, EnterpriseCostumeRela>{
	
	/**获取加工企业的主营产品类型*/
	@SuppressWarnings("unchecked")
	public List<Integer> getCostumeCode(int enterpriseId){
		String hql = "select costumeCode from EnterpriseCostumeRela where enterpriseId =:enterpriseId";
		return (List<Integer>)super.find(hql, new String[]{"enterpriseId"}, new Integer[]{enterpriseId});
	}

	public void delByEnterpriseId(int enterpriseId){
		String hql = "delete EnterpriseCostumeRela where enterpriseId =:enterpriseId";
		super.executeUpdate(hql, new String[]{"enterpriseId"}, new Integer[]{enterpriseId});
	}
}
