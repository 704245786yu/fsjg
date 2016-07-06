package com.basic.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.basic.dao.PersonDao;
import com.basic.po.Person;
import com.common.BaseBiz;
import com.util.NumberTransform;

@Service
public class PersonBiz extends BaseBiz<PersonDao, Integer, Person> {

	private static final String defaultPassword = "123456";
	
	/**批量保存个人用户信息
	 * */
	public void batchSavePerson(List<String[]> data,Integer userId){
		List<Person> list = new ArrayList<Person>();
		for(int i=0; i<data.size(); i++){
			String[] temp = data.get(i);
			Person person = new Person();
//			person.setUserName(temp[9]);	//用户名默认为手机号码
			person.setRealName(temp[1]);
//			person.setPassword(defaultPassword);	//密码为默认密码
			person.setGender(temp[2]);
			person.setAge(NumberTransform.getByte(temp[3]));
//			person.setProvince(temp[5]);
//			person.setCity(temp[6]);
//			person.setCounty(temp[7]);
//			person.setTown(temp[8]);
//			person.setTelephone(temp[9]);
//			person.setCreateBy(userId);
//			person.setCreateTime(new Date());
			person.setAuditState((byte)0);//默认0：待审核
			person.setPersonState((byte)0);//默认0：正常
			person.setEmail(temp[14]);
			person.setQq(NumberTransform.getLong(temp[15]));
			person.setFixPhone(temp[16]);
			person.setWechat(temp[17]);
			person.setPostalCode(temp[18]);
			person.setIdCard(temp[19]);
			list.add(person);
		}
		dao.saveBatch(list);
	}
	
	/**分页获取所有数据
	 * */
//	public List<Person> getAllByPage(int pageNo, int pageSize){
//		return dao.findByPage(pageNo, pageSize);
//	}
}
