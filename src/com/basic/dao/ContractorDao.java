package com.basic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.basic.po.Contractor;
import com.common.BaseDao;

@Repository
public class ContractorDao extends BaseDao<Integer, Contractor>{

	/**分页查询
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页需要显示的记录数
	 * @return 返回contractor的部分属性，以及Person的realName属性
	 * */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findByPageAndParams(int offset, int limit){
		StringBuffer hql = new StringBuffer("select new map(c.id as id, ")
				.append("c.processType as processType, ")
				.append("c.processYear as processYear, ")
				.append("c.workerAmount as workerAmount, ")
				.append("c.quote as quote, ")
				.append("c.equipment as equipment, ")
				.append("c.processDesc as processDesc, ")
				.append("c.createTime as createTime, ")
				.append("p.realName as realName) ")
				.append("from Contractor c, Person p where c.id = p.id");
		List<Map<String,Object>> list = (List<Map<String,Object>>)super.findByPage(hql.toString(),
				offset, limit, new String[]{}, new Object[]{});
		return list;
	}
}
