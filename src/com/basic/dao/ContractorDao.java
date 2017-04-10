package com.basic.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.basic.po.Contractor;
import com.basic.po.Person;
import com.basic.vo.ContractorHomeVo;
import com.basic.vo.ContractorSimpleVo;
import com.basic.vo.ContractorVo;
import com.common.BaseDao;
import com.common.dto.BootTablePageDto;

@Repository
public class ContractorDao extends BaseDao<Integer, Contractor>{

	@Autowired
	private PersonDao personDao;
	
	@Transactional
	public void saveBatchEntity(List<Person> personList, List<Contractor> contractorList){
		for(int i=0; i<personList.size(); i++){
			Person person = personList.get(i);
			Contractor c = contractorList.get(i);
			personDao.persist(person);
			c.setPersonId(person.getId());
			super.save(c);
		}
	}
	
	public BootTablePageDto<ContractorSimpleVo> search(Long province,Long city,Long county,Long town, Integer[] costumeCodes,
			Byte processYear,int offset,int limit,Long total){
		StringBuffer countSql = new StringBuffer("select count(1)");
		StringBuffer subSql = new StringBuffer(" from Person p,Contractor c where p.id = c.personId ");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		//省市区县乡镇
		if(province != null){
			subSql.append(" and p.province =:province");
			params.add("province");
			values.add(province);
		}
		if(city != null){
			subSql.append(" and p.city =:city");
			params.add("city");
			values.add(city);
		}
		if(county != null){
			subSql.append(" and p.county =:county");
			params.add("county");
			values.add(county);
		}
		if(town != null){
			subSql.append(" and p.town =:town");
			params.add("town");
			values.add(town);
		}
		//服饰类型
		if(costumeCodes != null && costumeCodes.length>0){
			StringBuffer subCostumeCode = new StringBuffer(" c.costumeCode like '%"+costumeCodes[0]+"%'");
			for(int i=1; i<costumeCodes.length; i++)
				subCostumeCode.append(" or c.costumeCode like '%"+costumeCodes[i]+"%'");
			subSql.append(" and ("+subCostumeCode.toString()+")");
		}
		//加工年限
		if(processYear != null){
			switch(processYear){
			case 1:
				subSql.append(" and c.processYear < 5");
				break;
			case 2:
				subSql.append(" and c.processYear between 5 and 10");
				break;
			case 3:
				subSql.append(" and c.processYear between 11 and 15");
				break;
			case 4:
				subSql.append(" and c.processYear between 16 and 20");
				break;
			case 5:
				subSql.append(" and c.processYear > 20");
				break;
			}
		}
		String[] paramsAry = new String[params.size()];
		params.toArray(paramsAry);
		Object[] valuesAry = values.toArray();
		//若有total表示翻页操作，无须再次查询total
		if(total == null){
			countSql.append(subSql);
			total = super.getCount(countSql.toString(), paramsAry, valuesAry);
			if(total==0)
				return new BootTablePageDto<ContractorSimpleVo>(0L,new ArrayList<ContractorSimpleVo>());
		}
		
		StringBuffer sql = new StringBuffer("select p.id as id, p.gender as gender, p.age as age, c.processYear as processYear, c.workerAmount as workerAmount, p.province as province, p.city as city, p.county as county, p.town as town, c.costumeCode as costumeCode, c.workSpace as workSpace ");
		sql.append(subSql).append(" order by p.basicUser.createTime desc");
		List<ContractorSimpleVo> contractors = super.findByPage(sql.toString(), offset, limit, paramsAry, valuesAry, ContractorSimpleVo.class);
		return new BootTablePageDto<ContractorSimpleVo>(total, contractors);
	}
	
	public BootTablePageDto<ContractorVo> findByPage(String userName,Long telephone,Byte auditState,Date beginDate,Date endDate,int offset, int limit, Long total){
		StringBuffer hql = new StringBuffer("from Contractor c, Person p where c.personId = p.id");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		if(userName.length()>0){
			hql.append(" and p.basicUser.userName like :userName");
			params.add("userName");
			values.add("%"+userName+"%");
		}
		if(telephone!=null){
			hql.append(" and p.basicUser.telephone =:telephone");
			params.add("telephone");
			values.add(telephone);
		}
		if(auditState!=null){
			hql.append(" and p.auditState =:auditState");
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
			if(total == 0)
				return new BootTablePageDto<ContractorVo>(total, new ArrayList<ContractorVo>());
		}
		List<ContractorVo> list = super.findByPage(
				"select c.personId as id,c.processType as processType, p.basicUser.userName as userName, p.basicUser.telephone as telephone,"
				+ "p.realName as realName,p.basicUser.state as state, p.auditState as auditState, p.basicUser.createTime as createTime "+hql.toString(),
				offset, limit, params.toArray(new String[]{}), values.toArray(new Object[]{}),ContractorVo.class);
		return new BootTablePageDto<ContractorVo>(total,list);
	}
	
	public List<ContractorHomeVo> getHomeList(){
		String hql = "select c.personId as id, p.gender as gender, p.age as age, c.processYear as processYear, c.workerAmount as workerAmount, p.province as province, p.city as city from Contractor c, Person p where c.personId = p.id order by c.personId desc";
		List<ContractorHomeVo> list = super.findByPage(hql, 0, 8, new String[]{}, null, ContractorHomeVo.class);
		return list;
	}
	
	public List<ContractorHomeVo> getRecommend(){
		String hql = "select c.personId as id, p.gender as gender, p.age as age, c.processYear as processYear, c.workerAmount as workerAmount from Contractor c, Person p where c.personId = p.id order by c.workerAmount desc";
		List<ContractorHomeVo> list = super.findByPage(hql, 0, 15, new String[]{}, null, ContractorHomeVo.class);
		return list;
	}
}
