package com.sys.biz;

import java.util.ArrayList;
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
	
	/**根据搜索条件分页查询数据。searchText用于模糊匹配查询常量名称和常量类型名称。
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * @param constantName 要模糊查询的常量名称
	 * */
	public BootTablePageDto<ConstantDict> findByPageAndParams(int offset, int limit, String constantName){
		BootTablePageDto<ConstantDict> bt = new BootTablePageDto<ConstantDict>();
		//若前端没有传递数据记录总数，则查询数据总记录数
		List<Object[]> params = new ArrayList<Object[]>();
		params.add(new Object[]{"like","constantName",'%'+constantName+'%'});
		Long total = dao.getCountByCriteria(params);
		bt.setTotal(total);
		bt.setRows(dao.findByPageAndParams(params,offset,limit));
		return bt;
	}
}
