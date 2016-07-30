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
	
	/**模糊匹配查询服饰类型编码*/
	@SuppressWarnings("unchecked")
	public List<Integer> getCodeByCategoryName(String categoryName){
		String hql = "select categoryCode from CostumeCategory where categoryName like :categoryName and categoryCode is not null";
		return (List<Integer>)dao.find(hql, new String[]{"categoryName"}, new String[]{"%"+categoryName+"%"});
	}
	
	/**获取服饰类型 名称、编码 键值对*/
	public HashMap<String,Integer> getAllNameCodeMap(){
		List<CostumeCategory> list = super.getAll();
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0; i<list.size(); i++){
			map.put(list.get(i).getCategoryName(), list.get(i).getCategoryCode());
		}
		return map;
	}
	
	/**获取服饰类型编码和类型名称*/
	public HashMap<Integer,String> getAllCodeNameMap(){
		List<CostumeCategory> list = super.getAll();
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		for(int i=1; i<list.size(); i++){
			map.put(list.get(i).getCategoryCode(), list.get(i).getCategoryName());
		}
		return map;
	}
	
	/**根据服饰类型编码获取名称*/
	public List<String> getNameByCode(Integer[] codes){
		return dao.getNameByCode(codes);
	}
}