package basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basic.po.BasicUser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class EnterpriseTest {
	private com.basic.dao.EnterpriseDao dao;
	@Test
	public void save(){
		BasicUser user = new BasicUser();
	}
}
