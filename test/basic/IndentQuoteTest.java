package basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basic.dao.IndentQuoteDao;
import com.util.JacksonJson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class IndentQuoteTest {

	@Autowired
	private IndentQuoteDao indentQuoteDao;
	
	@Test
	public void test(){
		JacksonJson.printBeanToJson(indentQuoteDao.getQuoteEnterprise(1471786627628l));
	}
	
}
