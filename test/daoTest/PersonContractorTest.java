package daoTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dao.basic.PersonContractorDao;
import com.util.JacksonJson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PersonContractorTest {

	@Autowired
	private PersonContractorDao personContractorDao;
	
	@Test
	public void testGet(){
	}
}