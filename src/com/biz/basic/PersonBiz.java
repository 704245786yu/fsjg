package com.biz.basic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.dao.basic.PersonDao;
import com.po.basic.Person;
import com.util.JacksonJson;

@Service
public class PersonBiz extends BaseBiz<PersonDao, Integer, Person> {

	/**批量保存个人用户信息
	 * */
	public Integer batchSavePerson(List<String[]> data){
		JacksonJson.printBeanToJson(data);
		//TODO something
		return 1;
	}
	
	/**分页获取所有数据
	 * */
//	public List<Person> getAllByPage(int pageNo, int pageSize){
//		return dao.findByPage(pageNo, pageSize);
//	}
}
