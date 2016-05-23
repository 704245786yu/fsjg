package com.dao.basic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.common.BaseDao;
import com.po.basic.PersonContractor;

@Repository
public class PersonContractorDao extends BaseDao<Integer, PersonContractor>{

	/**分页查询
	 * @param offset 偏移量，即记录索引位置
	 * @param pageSize 每页需要显示的记录数
	 * @return 返回PersonContractor的部分属性，以及Person的realName属性
	 * */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findByPageAndParams(int offset, int pageSize){
		StringBuffer hql = new StringBuffer("select new map(pc.id as id, ")
				.append("pc.processType as processType, ")
				.append("pc.processYear as processYear, ")
				.append("pc.workerAmount as workerAmount, ")
				.append("pc.quote as quote, ")
				.append("pc.equipment as equipment, ")
				.append("pc.processDesc as processDesc, ")
				.append("pc.createTime as createTime, ")
				.append("p.realName as realName) ")
				.append("from PersonContractor pc, Person p where pc.id = p.id");
		List<Map<String,Object>> list = (List<Map<String,Object>>)super.findByPage(hql.toString(),
				offset, pageSize, new String[]{}, new Object[]{});
		return list;
	}
}
