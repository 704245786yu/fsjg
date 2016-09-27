package basic;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basic.dao.BasicUserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BasicUserTest {

	@Autowired
	private BasicUserDao basicUserDao;
	
	@Test
	public void testSave(){
		HashSet<Long> tele = new HashSet<Long>();
		tele.add(18657639877L);
		tele.add(13962129694L);
		tele.add(1111111111L);
		System.out.println(basicUserDao.teleIsExist(tele));
	}
}
