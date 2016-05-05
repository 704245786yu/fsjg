package basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dao.basic.PersonDao;
import com.po.basic.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PersonTest {

	@Autowired
	private PersonDao personDao;
	
	@Test
	public void testSave(){
		Person person = new Person();
		person.setUserName("test1");
		person.setPassword("123123");
		personDao.save(person);
	}
}
