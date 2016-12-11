package com.basic.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.basic.po.Person;
import com.common.BaseDao;
import com.common.dto.BootTablePageDto;

@Repository
public class PersonDao extends BaseDao<Integer, Person>{

	@Autowired
	private BasicUserDao basicUserDao;
	
	@SuppressWarnings("unchecked")
	public Person findByUserId(int userId){
		String hql = "from Person p where p.basicUser.id =:userId";
		List<Person> list = (List<Person>)super.find(hql, new String[]{"userId"}, new Integer[]{userId});
		if(list.size()==1)
			return list.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public BootTablePageDto<Person> findByPage(String userName,Long telephone,Byte auditState,Date beginDate,Date endDate,int offset, int limit, Long total){
		StringBuffer hql = new StringBuffer("from Person where 1=1");
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

	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(Integer id) {
		String hql = "select basicUser.id from Person where id =:id";
		List<Integer> list = (List<Integer>)super.find(hql, new String[]{"id"}, new Integer[]{id});
		Integer basicUserId  = list.get(0);
		super.deleteById(id);
		basicUserDao.deleteById(basicUserId);
	}
	
}
