package com.biz.basic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.dao.basic.PersonContractorDao;
import com.dao.basic.PersonDao;
import com.dto.BootTablePageDto;
import com.po.basic.Person;
import com.po.basic.PersonContractor;
import com.util.NumberTransform;

@Service
public class PersonContractorBiz extends BaseBiz<PersonContractorDao, Integer, PersonContractor> {

	@Autowired
	private PersonDao personDao;
	
	private static final String defaultPassword = "123456";
	
	/**批量保存快产专家信息
	 * 快产专家拥有个人Person的所有字段，先保存Person后保存PersonContractor
	 * */
	public void batchSavePersonContractor(List<String[]> data,Integer userId){
		List<Person> list = new ArrayList<Person>();
		for(int i=0; i<data.size(); i++){
			String[] temp = data.get(i);
			Person person = new Person();
			person.setUserName(temp[9]);	//用户名默认为手机号码
			person.setRealName(temp[1]);
			person.setPassword(defaultPassword);	//密码为默认密码
			person.setGender(temp[2]);
			person.setAge(NumberTransform.getByte(temp[3]));
//			person.setProvince(temp[5]);
//			person.setCity(temp[6]);
//			person.setCounty(temp[7]);
//			person.setTown(temp[8]);
			person.setTelephone(temp[9]);
			person.setCreateBy(userId);
			person.setCreateTime(new Date());
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
		personDao.saveBatch(list);
	}
	
	/**分页查询
	 * @param offset 偏移量，即记录索引位置
	 * @param pageSize 每页需要显示的记录数
	 * @return 返回PersonContractor的部分属性，以及Person的realName属性
	 * */
	public BootTablePageDto<Map<String,Object>> findByPageAndParams(int offset, int pageSize){
		BootTablePageDto<Map<String,Object>> bt = new BootTablePageDto<Map<String,Object>>();
		long total = dao.getCount();
		bt.setTotal(total);
		bt.setRows(dao.findByPageAndParams(offset,pageSize));
		return bt;
	}
}
