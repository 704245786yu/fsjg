package com.basic.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.ContractorDao;
import com.basic.dao.PersonDao;
import com.basic.dto.ContractorDto;
import com.basic.po.Person;
import com.basic.po.Contractor;
import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;
import com.util.NumberTransform;

@Service
public class ContractorBiz extends BaseBiz<ContractorDao, Integer, Contractor> {

	@Autowired
	private PersonDao personDao;
	
	private static final String defaultPassword = "123456";
	
	/**批量保存快产专家信息
	 * 快产专家拥有个人Person的所有字段，先保存Person后保存contractor
	 * */
	public void batchSaveContractor(List<String[]> data,Integer userId){
		List<Person> personList = new ArrayList<Person>();	//个人信息
		List<Contractor> contractorList = new ArrayList<Contractor>();	//快产专家（个人承包商）信息
		for(int i=0; i<data.size(); i++){
			String[] temp = data.get(i);
			Person person = new Person();
//			person.setUserName(temp[9]);	//用户名默认为手机号码
			person.setRealName(temp[1]);
//			person.setPassword(defaultPassword);	//密码为默认密码
			person.setGender(temp[2]);
			person.setAge(NumberTransform.getByte(temp[3]));
//			temp[4] 专业技能
//			person.setProvince(temp[5]);
//			person.setCity(temp[6]);
//			person.setCounty(temp[7]);
//			person.setTown(temp[8]);
			person.setDetailAddr(temp[9]);
//			person.setTelephone(temp[10]);
//			person.setCreateBy(userId);
//			person.setCreateTime(new Date());
			person.setAuditState((byte)0);//默认0：待审核
			person.setPersonState((byte)0);//默认0：正常
			person.setEmail(temp[15]);
			person.setQq(NumberTransform.getLong(temp[16]));
			person.setFixPhone(temp[17]);
			person.setWechat(temp[18]);
			person.setPostalCode(temp[19]);
			person.setIdCard(temp[20]);
			personList.add(person);
			
			Contractor contractor = new Contractor();
//			contractor.setProcessType(temp[11]);
//			contractor temp[12] 主营产品
			contractor.setWorkerAmount(NumberTransform.getShort(temp[13]));
			contractor.setQuote(temp[14]);
			contractor.setEquipment(temp[21]);
			contractorList.add(contractor);
		}
		personDao.saveBatch(personList);
		dao.saveBatch(contractorList);
	}
	
	/**根据ID获取快产专家DTO，快产专家信息同时包括Person信息和自身信息*/
	public ContractorDto getById(int id){
		Person person = personDao.findById(id);
//		person.setPassword(null);	//不允许返回password
		Contractor contractor = dao.findById(id);
		ContractorDto dto = new ContractorDto(person, contractor);
		return dto;
	}
	
	/**分页查询
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页需要显示的记录数
	 * @return 返回contractor的部分属性，以及Person的realName属性
	 * */
	public BootTablePageDto<Map<String,Object>> findByPageAndParams(int offset, int limit){
		BootTablePageDto<Map<String,Object>> bt = new BootTablePageDto<Map<String,Object>>();
		long total = dao.getCount();
		bt.setTotal(total);
		bt.setRows(dao.findByPageAndParams(offset,limit));
		return bt;
	}
}
