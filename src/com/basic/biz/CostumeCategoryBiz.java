package com.basic.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.basic.dao.CostumeCategoryDao;
import com.basic.po.CostumeCategory;
import com.common.NestTreeBiz;

@Service
public class CostumeCategoryBiz extends NestTreeBiz<CostumeCategoryDao, Integer, CostumeCategory>{
	
	/**此方法问题在于webApplicationContext为null，原因可能bean未都创建成功*/
//	@PostConstruct
//	public void init(){  
//		HashMap<Integer,String> map = this.getAllCodeNameMap();
//		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
//		ServletContext servletContext = webApplicationContext.getServletContext();
//		servletContext.setAttribute("costumeCategoryMap", map);
//	}
	
	/**获取行业分类*/
	public HashMap<String,Integer> getTrade(){
		return this.getChildCostumeMap(null);
	}
	
	/**获取行业分类及其服饰类型
	 * @return List<HashMap<Object,Object>> {tradeCode:tradeName, "children":costumeMap<code,name>}
	 * */
	public List<HashMap<Object,Object>> getTradeAndCostume(){
		List<HashMap<Object,Object>> result = new ArrayList<HashMap<Object,Object>>();
		List<CostumeCategory> trades = this.getChild(null);
		for(int i=0; i<trades.size(); i++){
			CostumeCategory trade = trades.get(i);
			List<CostumeCategory> costumeList = dao.getDescendantNode(trade.getId());
			HashMap<Integer,String> costumeMap = new HashMap<Integer,String>();
			for(int j=0; j<costumeList.size(); j++){
				costumeMap.put(costumeList.get(j).getCategoryCode(), costumeList.get(j).getCategoryName());
			}
			HashMap<Object,Object> map = new HashMap<Object,Object>();
			map.put(trade.getCategoryCode(), trade.getCategoryName());
			map.put("children", costumeMap);
			result.add(map);
		}
		return result;
	}
	
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
	
	/**获取服饰类型编码和类型名称，并按左值排序*/
	public HashMap<Integer,String> getAllCodeNameMap(){
//		String hql = "from CostumeCategory order by lft";
//		List<CostumeCategory> list = dao.find(hql);
		List<CostumeCategory> list = super.getAll();
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		for(int i=1; i<list.size(); i++){
			map.put(list.get(i).getCategoryCode(), list.get(i).getCategoryName());
		}
		return map;
	}
	
	/**根据服饰类型编码获取名称*/
	public List<String> getNameByCode(List<Integer> codes){
		return dao.getNameByCode(codes);
	}

	/**获取所有后代编码*/
	@SuppressWarnings("unchecked")
	public List<Integer> getSubCode(int code){
		CostumeCategory c = new CostumeCategory();
		c.setCategoryCode(code);
		CostumeCategory category = dao.findByExample(c).get(0);
		String hql = "select categoryCode from CostumeCategory where lft between :lft and :rgt";
		return (List<Integer>)dao.find(hql, new String[]{"lft","rgt"}, new Integer[]{category.getLft(),category.getRgt()});
	}
}