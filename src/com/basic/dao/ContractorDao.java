package com.basic.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.basic.po.Contractor;
import com.basic.po.Person;
import com.common.BaseDao;
import com.common.dto.BootTablePageDto;

@Repository
public class ContractorDao extends BaseDao<Integer, Contractor>{

	/**分页查询
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页需要显示的记录数
	 * @return 返回contractor的部分属性，以及Person的realName属性
	 * */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findByPageAndParams(int offset, int limit){
		StringBuffer hql = new StringBuffer("select new map(c.personId as id, ")
				.append("c.processType as processType, ")
				.append("c.processYear as processYear, ")
				.append("c.workerAmount as workerAmount, ")
				.append("c.quote as quote, ")
				.append("c.equipment as equipment, ")
				.append("c.processDesc as processDesc, ")
				.append("p.realName as realName) ")
				.append("from Contractor c, Person p where c.personId = p.id");
		List<Map<String,Object>> list = (List<Map<String,Object>>)super.findByPage(hql.toString(),
				offset, limit, new String[]{}, new Object[]{});
		return list;
	}
	
	public BootTablePageDto<Person> findByPage(String userName,Long telephone,Byte auditState,Date beginDate,Date endDate,int offset, int limit, Long total){
		StringBuffer hql = new StringBuffer("from Contractor c, Person p where c.personId = p.id");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		if(userName.length()>0){
			hql.append(" and userName like :userName");
			params.add("userName");
			values.add("%"+userName+"%");
		}
		if(telephone!=null){
			hql.append(" and telephone =:telephone");
			params.add("telephone");
			values.add(telephone);
		}
		if(auditState!=null){
			hql.append(" and auditState =:auditState");
			params.add("auditState");
			values.add(auditState);
		}
		if(beginDate!=null && endDate!=null){
			hql.append(" and basicUser.createTime between :beginDate and :endDate");
			params.add("beginDate");
			values.add(beginDate);
			params.add("endDate");
			values.add(endDate);
		}
		if(total==null){
			StringBuffer countSql = new StringBuffer("select count(1) ");
			countSql.append(hql);
			total = super.getCount(countSql.toString(), params.toArray(new String[]{}), values.toArray(new Object[]{}));
		}
		List<Person> list = (List<Person>)super.findByPage(hql.toString(), offset, limit, params.toArray(new String[]{}), values.toArray(new Object[]{}));
		return new BootTablePageDto<Person>(total,list);
	}
}
