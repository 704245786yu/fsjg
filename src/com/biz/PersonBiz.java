package com.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dao.PersonDao;
import com.po.Person;
import com.util.BaseBiz;

@Service
public class PersonBiz extends BaseBiz<PersonDao, Person> {

	/**分页获取所有数据
	 * */
	public List<Person> getAllByPage(int pageNo, int pageSize){
		return dao.findByPage(pageNo, pageSize);
	}
}
