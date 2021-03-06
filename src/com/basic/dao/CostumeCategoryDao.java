package com.basic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.basic.po.CostumeCategory;
import com.common.NestTreeDao;

/**@author zhiyu
 * */
@Repository
public class CostumeCategoryDao extends NestTreeDao<Integer, CostumeCategory> {

	/**获取本节点及后代节点的名称、ID 键值对数据
	public HashMap<String,Integer> getDescendantOrgMap1(int id){
		List<Integer> ids = super.getDescendantId(id);
		ids.add(0, id);
		List<CostumeCategory> list = super.findByIds(ids);
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0; i<list.size(); i++){
			map.put(list.get(i).getCategoryName(), list.get(i).getId());
		}
		return map;
	}*/
	
	/**根据服饰类型编码获取名称*/
	@SuppressWarnings("unchecked")
	public List<String> getNameByCode(List<Integer> codes){
		String hql = "select categoryName from CostumeCategory where categoryCode in (:codes)";
		return (List<String>)super.find(hql, new String[]{"codes"}, new Object[]{codes});
	}
}
