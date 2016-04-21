package com.biz.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.dao.sys.ConstantDictDao;
import com.dto.BootTablePageDto;
import com.po.sys.ConstantDict;

@Service
public class ConstantDictBiz extends BaseBiz<ConstantDictDao, Integer, ConstantDict>{

	/**根据搜索条件分页查询数据。searchText用于模糊匹配查询常量名称和常量类型名称。
	 * @param offset 偏移量，即记录索引位置
	 * @param pageSize 每页记录数
	 * @param constantName 要模糊查询的常量名称
	 * */
	public BootTablePageDto<ConstantDict> findByPageAndParams(int offset, int pageSize, String constantName){
		BootTablePageDto<ConstantDict> bt = new BootTablePageDto<ConstantDict>();
		//若前端没有传递数据记录总数，则查询数据总记录数
		List<Object[]> params = new ArrayList<Object[]>();
		params.add(new Object[]{"like","constantName",'%'+constantName+'%'});
		Long total = dao.getCountByCriteria(params);
		bt.setTotal(total);
		bt.setRows(dao.findByPageAndParams(params,offset,pageSize));
		return bt;
	}
}
