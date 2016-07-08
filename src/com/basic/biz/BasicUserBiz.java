package com.basic.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.BasicUserDao;
import com.basic.dao.EnterpriseDao;
import com.basic.dao.PersonDao;
import com.basic.po.BasicUser;
import com.basic.po.Enterprise;
import com.basic.po.Person;
import com.common.BaseBiz;
import com.common.vo.ReturnValueVo;

@Service
public class BasicUserBiz extends BaseBiz<BasicUserDao,Integer,BasicUser>{
	
	@Autowired
	private EnterpriseDao enterpriseDao;
	@Autowired
	private PersonDao personDao;
	
	/**用户注册
	 * @param enterpriseName可为空
	 * */
	public ReturnValueVo signUp(BasicUser basicUser, String enterpriseName){
		boolean isExsit = dao.isExsit(basicUser.getUserName(), basicUser.getTelephone());
		if(isExsit){
			return new ReturnValueVo(ReturnValueVo.ERROR,"用户名或手机号重复");
		}
		//企业用户需判断企业名称是否重复
		if(basicUser.getRoleId() == 2){
			if(enterpriseDao.isExsit(enterpriseName))
				return new ReturnValueVo(ReturnValueVo.ERROR,"企业名称重复");
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterpriseName(enterpriseName);
			enterprise.setBasicUser(basicUser);
			enterpriseDao.save(enterprise);
		}else if(basicUser.getRoleId() == 1){
			Person person = new Person();
			person.setBasicUser(basicUser);
			personDao.save(person);
		}
		return new ReturnValueVo(ReturnValueVo.SUCCESS,null);
	}
	
	public boolean nameIsExist(String userName){
		return dao.nameIsExsit(userName);
	}
	
	public boolean teleIsExist(Long telephone){
		return dao.teleIsExsit(telephone);
	}
}
