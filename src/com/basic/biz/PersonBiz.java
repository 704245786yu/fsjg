package com.basic.biz;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basic.dao.BasicUserDao;
import com.basic.dao.PersonDao;
import com.basic.po.BasicUser;
import com.basic.po.Person;
import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;

@Service
public class PersonBiz extends BaseBiz<PersonDao, Integer, Person> {

	@Autowired
	private BasicUserDao basicUserDao;
	
	/**根据BasicUserId获取Person信息*/
	public Person getByBasicUserId(int userId){
		Criterion c = Restrictions.eq("basicUser.id", userId);
		Person p=dao.findByCriteria(c).get(0);
		return p;
	}
	
	/**先更新基本用户信息，后更新个人用户信息*/
	@Override
	@Transactional
	public void update(Person p) {
		BasicUser basicUser = p.getBasicUser();
		
		Person old = dao.findByUserId(basicUser.getId());
		BasicUser oldBasicUser = old.getBasicUser();
		basicUser.setPassword(oldBasicUser.getPassword());
		basicUser.setRoleId(oldBasicUser.getRoleId());
		basicUser.setState(oldBasicUser.getState());
		basicUser.setCreateBy(oldBasicUser.getCreateBy());
		basicUser.setCreateTime(oldBasicUser.getCreateTime());
		
		p.setId(old.getId());
		p.setAuditState(old.getAuditState());
		p.setAuditBy(old.getAuditBy());
		p.setAuditTime(old.getAuditTime());
		
		dao.merge(p);
	}
	
	/**实名审核*/
	public void audit(int id,byte auditState,int auditBy){
		String hql = "update Person set auditState =:auditState, auditBy =:auditBy, auditTime =:auditTime where id =:id";
		dao.executeUpdate(hql, new String[]{"auditState","auditBy","auditTime","id"}, new Object[]{auditState,auditBy,new Date(),id});
	}
	
	public BootTablePageDto<Person> findByPage(String userName,Long telephone,Byte auditState,Integer createType,Date beginDate,Date endDate,int offset, int limit, Long total){
		return dao.findByPage(userName, telephone, auditState, createType, beginDate, endDate, offset, limit, total);
	}
	
	/**获取省市信息*/
	@SuppressWarnings("unchecked")
	public Object[] getDistrict(int userId){
		String hql = "select province,city from Person where basicUser.id =:userId";
		List<Object[]> list = (List<Object[]>)dao.find(hql, new String[]{"userId"}, new Integer[]{userId});
		if(list.size()!=1)
			return new Object[]{};
		else
			return list.get(0);
	}
}
