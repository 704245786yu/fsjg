package com.basic.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.basic.dao.EnterpriseCostumeRelaDao;
import com.basic.po.EnterpriseCostumeRela;
import com.common.BaseBiz;

@Service
public class EnterpriseCostumeRelaBiz extends BaseBiz<EnterpriseCostumeRelaDao, Integer, EnterpriseCostumeRela>{

	/**获取加工企业的主营产品类型*/
	public List<Integer> getCostumeCode(int enterpriseId){
		return dao.getCostumeCode(enterpriseId);
	}
	
	/**根据企业ID获取服饰类型名称*/
	@SuppressWarnings("unchecked")
	public List<String> getCostumeNameByEnterpriseId(int enterpriseId){
		String hql = "select categoryName from CostumeCategory where categoryCode in (select costumeCode from EnterpriseCostumeRela where enterpriseId =:enterpriseId)";
		return (List<String>)dao.find(hql, new String[]{"enterpriseId"}, new Integer[]{enterpriseId});
	}
}
