package com.basic.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.BasicUserDao;
import com.basic.dao.EnterpriseDao;
import com.basic.dao.PersonDao;
import com.basic.po.BasicUser;
import com.basic.po.Enterprise;
import com.basic.po.Person;
import com.common.BaseBiz;

@Service
public class BasicUserBiz extends BaseBiz<BasicUserDao,Integer,BasicUser>{
	
	@Autowired
	private EnterpriseDao enterpriseDao;
	@Autowired
	private PersonDao personDao;
	
	/**用户注册
	 * @param enterpriseName可为空
	 * */
	public String signUp(BasicUser basicUser, String enterpriseName){
		boolean isExsit = dao.isExsit(basicUser.getUserName(), basicUser.getTelephone());
		if(isExsit){
			return "用户名或手机号重复";
		}
		//企业用户需判断企业名称是否重复
		if(basicUser.getRoleId() == 2){
			if(enterpriseDao.isExsit(enterpriseName))
				return "企业名称重复";
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterpriseName(enterpriseName);
			enterprise.setBasicUser(basicUser);
			enterpriseDao.persist(enterprise);
		}else if(basicUser.getRoleId() == 1){
			Person person = new Person();
			person.setBasicUser(basicUser);
			personDao.persist(person);
		}
		return null;
	}
	
	/**密码验证
	 * @return 验证通过返回true,失败返回false
	 * */
	public boolean checkPwd(Integer userId, String oldPassword){
		List<?> passwords = dao.find("select password from BasicUser where id =:id",new String[]{"id"},new Integer[]{userId});
		String password = (String)passwords.get(0);
		if(password.equals(oldPassword))
			return true;
		else
			return false;
	}
	/**修改密码
	 * */
	public void modifyPwd(Integer userId, String password, Integer updateBy){
		dao.executeUpdate("update BasicUser set password =:password, updateBy =:updateBy where id =:userId",
				new String[]{"password","updateBy","userId"}, new Object[]{password, updateBy,userId});
	}
	
	public BasicUser login(String nameOrTele, String password){
		return dao.login(nameOrTele, password);
	}
	
	public boolean nameIsExist(String userName){
		return dao.nameIsExsit(userName);
	}
	
	public boolean teleIsExist(Long telephone){
		return dao.teleIsExsit(telephone);
	}
}
