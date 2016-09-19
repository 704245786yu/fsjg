package com.basic.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.basic.po.EnterpriseCostumeRela;
import com.common.BaseDao;

@Repository
public class EnterpriseCostumeRelaDao extends BaseDao<Integer, EnterpriseCostumeRela>{
	
	public void save(int enterpriseId,List<Integer> costumeCode){
		List<EnterpriseCostumeRela> list = new ArrayList<EnterpriseCostumeRela>(costumeCode.size());
		for(int i=0; i<costumeCode.size(); i++){
			list.add(new EnterpriseCostumeRela(enterpriseId,costumeCode.get(i)));
		}
		super.saveBatch(list);
	}
	
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
