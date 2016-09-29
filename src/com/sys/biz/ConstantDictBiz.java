package com.sys.biz;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;
import com.sys.dao.ConstantDictDao;
import com.sys.po.ConstantDict;

@Service
public class ConstantDictBiz extends BaseBiz<ConstantDictDao, Integer, ConstantDict>{
	
	/**根据constantTypeCode常量类型编码查询字典常量
	 * */
	public List<ConstantDict> findByConstantTypeCode(String constantTypeCode){
		ConstantDict constantDict = new ConstantDict();
		constantDict.setConstantTypeCode(constantTypeCode);
		return dao.findByExample(constantDict);
	}

	/**根据constantTypeCode常量类型编码获取字典常量键值对*/
	public HashMap<String,String> getNameValueMap(String constantTypeCode){
		ConstantDict constantDict = new ConstantDict();
		constantDict.setConstantTypeCode(constantTypeCode);
		List<ConstantDict> list = dao.findByExample(constantDict);
		HashMap<String,String> map = new HashMap<String,String>();
		for(int i=0; i<list.size(); i++){
			map.put(list.get(i).getConstantName(), list.get(i).getConstantValue());
		}
		return map;
	}
	
	/**根据constantTypeCode常量类型编码获取字典常量键值对*/
	public HashMap<String,String> getValueNameMap(String constantTypeCode){
		ConstantDict constantDict = new ConstantDict();
		constantDict.setConstantTypeCode(constantTypeCode);
		List<ConstantDict> list = dao.findByExample(constantDict);
		HashMap<String,String> map = new HashMap<String,String>();
		for(int i=0; i<list.size(); i++){
			map.put(list.get(i).getConstantValue(), list.get(i).getConstantName());
		}
		return map;
	}
	
	/**@param constantTypeCode 常量编码，精确匹配
	 * @param constantName 模糊匹配
	 * */
	@SuppressWarnings("unchecked")
	public List<ConstantDict> getByCodeAndConstantName(String constantTypeCode, String constantName){
		String hql = "from ConstantDict where constantTypeCode =:constantTypeCode and constantName like :constantName";
		return (List<ConstantDict>)dao.find(hql, new String[]{"constantTypeCode","constantName"}, new String[]{constantTypeCode, "%"+constantName+"%"});
	}
	
	/**根据搜索条件分页查询数据。searchText用于模糊匹配查询常量名称和常量类型名称。
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * @param constantName 要模糊查询的常量名称
	 * */
	@SuppressWarnings("unchecked")
	public BootTablePageDto<ConstantDict> findByPageAndParams(int offset, int limit, String constantName){
		BootTablePageDto<ConstantDict> bt = new BootTablePageDto<ConstantDict>();
		String hql =" from ConstantDict where constantName like :constantName";
		Long total = dao.getCount("select count(1)"+hql, new String[]{"constantName"}, new String[]{"%"+constantName+"%"});
		bt.setTotal(total);
		bt.setRows((List<ConstantDict>)dao.findByPage(hql,offset,limit, new String[]{"constantName"}, new String[]{"%"+constantName+"%"}));
		return bt;
	}
}
