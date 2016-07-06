package com.basic.biz;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.basic.dao.CostumeCategoryDao;
import com.basic.po.CostumeCategory;
import com.common.NestTreeBiz;

@Service
public class CostumeCategoryBiz extends NestTreeBiz<CostumeCategoryDao, Integer, CostumeCategory>{

	/**获取直接子节点的服饰类型名，服饰类型编码 键值对
	 * @param id 若为null，表示获取一级节点
	 * */
	public HashMap<String,Integer> getChildCostumeMap(Integer id){
		List<CostumeCategory> list = super.getChild(id);
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0; i<list.size(); i++){
			map.put(list.get(i).getCategoryName(), list.get(i).getCategoryCode());
		}
		return map;
	}
	
	/**获取本节点及后代节点的名称、ID 键值对*/
//	public HashMap<String,Integer> getDescendantOrgMap(int id){
//		return dao.getDescendantOrgMap(id);
//	}
	
	/**获取服饰类型 名称、编码 键值对*/
	public HashMap<String,Integer> getAllNameCodeMap(){
		List<CostumeCategory> list = super.getAll();
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0; i<list.size(); i++){
			map.put(list.get(i).getCategoryName(), list.get(i).getCategoryCode());
		}
		return map;
	}
}