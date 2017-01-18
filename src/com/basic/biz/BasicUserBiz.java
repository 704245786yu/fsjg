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

@Service
public class BasicUserBiz extends BaseBiz<BasicUserDao,Integer,BasicUser>{
	
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private EnterpriseDao enterpriseDao;
	@Autowired
	private PersonDao personDao;
	
	/**用户注册
	 * @param enterpriseName可为空
	 * */
	public String signUp(BasicUser basicUser, String enterpriseName){
		boolean isExsit = dao.isExist(basicUser.getUserName(), basicUser.getTelephone());
		if(isExsit){
			return "用户名或手机号重复";
		}
		//企业用户需判断企业名称是否重复
		if(basicUser.getRoleId() == 2){
			if(enterpriseDao.isExsit(enterpriseName,null))
				return "企业名称重复";
			Enterprise enterprise = new Enterprise();
			enterprise.setEnterpriseName(enterpriseName);
			enterprise.setNumber(enterpriseBiz.generateNumber(basicUser.getTelephone()).toString());
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
	public boolean checkPwd(Integer id, String oldPassword){
		long count = dao.getCount("select count(1) from BasicUser where id =:id and password =:oldPassword",
				new String[]{"id","oldPassword"},new Object[]{id,oldPassword});
		return count == 1;
	}
	
	/**修改密码
	 * */
	public void modifyPwd(Integer userId, String password){
		dao.executeUpdate("update BasicUser set password =:password where id =:userId",
				new String[]{"password","userId"}, new Object[]{password,userId});
	}
	
	/**忘记密码-修改密码
	 * */
	public int modifyPwd(long telephone, String password){
		return dao.executeUpdate("update BasicUser set password =:password where telephone =:telephone",
				new String[]{"password","telephone"}, new Object[]{password,telephone});
	}
	
	public BasicUser login(String nameOrTele, String password){
		return dao.login(nameOrTele, password);
	}
	
	public boolean nameIsExist(String userName, Integer id){
		return dao.nameIsExist(userName,id);
	}
	
	/**手机号码是否已存在，若id为null判断全表，若不为null，则排除该id值的记录
	 * @param id 要排除在检测范围内的id记录
	 * */
	public boolean teleIsExist(Long telephone, Integer id){
		return dao.teleIsExist(telephone, id);
	}
	
	/**验证我的用户名、手机号是否已经存在*/
	public String isMyNameAndTeleExist(BasicUser b){
		String errorMsg = "";
		Integer basicUserId = b.getId();
		if(dao.nameIsExist(b.getUserName(), basicUserId)){
			errorMsg = "用户名已存在";
		}
		if(this.teleIsExist(b.getTelephone(), basicUserId)){
			errorMsg += " 手机号已存在";
		}
		return errorMsg;
	}
	
	/**用户状态更改：冻结 解冻*/
	public void modifyState(int id,byte state,int updateBy){
		String hql = "update BasicUser set state =:state, updateBy =:updateBy where id =:id";
		dao.executeUpdate(hql, new String[]{"state","updateBy","id"}, new Object[]{state,updateBy,id});
	}
}
