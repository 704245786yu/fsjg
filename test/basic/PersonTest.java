package basic;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basic.dao.PersonDao;
import com.basic.po.Person;
import com.util.DateTransform;
import com.util.JacksonJson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PersonTest {

	@Autowired
	private PersonDao personDao;
	
	@Test
	public void testSave(){
		Person person = new Person();
//		person.setUserName("test1");
//		person.setPassword("123123");
		personDao.save(person);
	}
	
	@Test
	public void test1(){
		JacksonJson.printBeanToJson(personDao.findByUserId(102));
	}
	
	@Test
	public void test2(){
		Date beginTime = null;
		Date endTime = null;
		beginTime = DateTransform.String2Date("2016-08-01", "yyyy-MM-dd");
		endTime = DateTransform.String2Date("2016-10-10"+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		JacksonJson.printBeanToJson(personDao.findByPage("", null, null, beginTime, endTime, 0, 10, null));
	}
	
	@Test
	public void test3(){
		personDao.deleteById(7);
	}
}
