package basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.basic.biz.EnterpriseBiz;
import com.basic.dao.EnterpriseDao;
import com.basic.po.BasicUser;
import com.basic.po.Enterprise;
import com.util.JacksonJson;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("/applicationContext.xml")
public class EnterpriseTest {
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	
	@Autowired
	private EnterpriseDao enterpriseDao;
	
	@Test
	public void save(){
		BasicUser basicUser = new BasicUser();
		basicUser.setTelephone(18721414056L);
		basicUser.setPassword("123");
		basicUser.setRoleId(1);
		
		Enterprise enterprise = new Enterprise();
		enterprise.setEnterpriseName("哈哈哈");
		enterprise.setBasicUser(basicUser);
		enterpriseDao.save(enterprise);
	}
	
	@Test
	public void update(){
		BasicUser basicUser = new BasicUser();
		basicUser.setId(30);
		basicUser.setTelephone(11111L);
		basicUser.setPassword("112");
		basicUser.setRoleId(2);
		Enterprise enterprise = new Enterprise();
		enterprise.setId(7);
		enterprise.setEnterpriseName("泰州");
		enterprise.setBasicUser(basicUser);
		enterpriseDao.update(enterprise);
	}
	
	@Test
	public void get(){
		enterpriseDao.findById(1);
	}
	
	@Test
	public void getBasicUser(){
		JacksonJson.printBeanToJson(enterpriseDao.getUserId(5));
	}
	
	@Test
	public void isExsit(){
		JacksonJson.printBeanToJson(enterpriseDao.isExsit("服饰厂1"));
	}
}
