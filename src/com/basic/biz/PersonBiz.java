package com.basic.biz;

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
		BasicUser tempBasicUser = p.getBasicUser();
		
		Person old = dao.findByUserId(tempBasicUser.getId());
		BasicUser basicUser = old.getBasicUser();
		basicUser.setUserName(tempBasicUser.getUserName());
		basicUser.setTelephone(tempBasicUser.getTelephone());
		basicUser.setUpdateBy(tempBasicUser.getUpdateBy());
		basicUserDao.update(basicUser);
		
		p.setId(old.getId());
		p.setAuditState(old.getAuditState());
		p.setAuditBy(old.getAuditBy());
		p.setAuditTime(old.getAuditTime());
		
		dao.merge(p);
	}
	
}
