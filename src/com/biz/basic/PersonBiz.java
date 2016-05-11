package com.biz.basic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.dao.basic.PersonDao;
import com.po.basic.Person;

@Service
public class PersonBiz extends BaseBiz<PersonDao, Integer, Person> {

	private static final String defaultPassword = "123456";
	/**批量保存个人用户信息
	 * */
	public Integer batchSavePerson(List<String[]> data){
		List<Person> list = new ArrayList<Person>();
		for(int i=0; i<data.size(); i++){
			String[] temp = data.get(i);
			Person person = new Person();
			person.setUserName(temp[9]);	//用户名默认为手机号码
			person.setRealName(temp[1]);
			person.setPassword(defaultPassword);	//密码为默认密码
			person.setGender(temp[2]);
		}
		return 1;
	}
	
	/**分页获取所有数据
	 * */
//	public List<Person> getAllByPage(int pageNo, int pageSize){
//		return dao.findByPage(pageNo, pageSize);
//	}
}
