package com.biz.basic;

import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.dao.basic.PersonDao;
import com.po.basic.Person;

@Service
public class PersonBiz extends BaseBiz<PersonDao, Integer, Person> {

	/**分页获取所有数据
	 * */
//	public List<Person> getAllByPage(int pageNo, int pageSize){
//		return dao.findByPage(pageNo, pageSize);
//	}
}
