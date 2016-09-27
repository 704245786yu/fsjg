package com.basic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.basic.po.Person;
import com.common.BaseDao;

@Repository
public class PersonDao extends BaseDao<Integer, Person>{

	@SuppressWarnings("unchecked")
	public Person findByUserId(int userId){
		String hql = "from Person p where p.basicUser.id =:userId";
		List<Person> list = (List<Person>)super.find(hql, new String[]{"userId"}, new Integer[]{userId});
		if(list.size()==1)
			return list.get(0);
		else
			return null;
	}
}
