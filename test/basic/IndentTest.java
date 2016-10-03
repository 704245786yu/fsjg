package basic;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basic.dao.IndentDao;
import com.util.DateTransform;
import com.util.JacksonJson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class IndentTest {

	@Autowired
	private IndentDao indentDao;
	
	@Test
	public void test(){
		String hql = "from Indent where indentName like :indentName and createTime between :beginDate and :endDate";
		Date beginDate = DateTransform.String2Date("2016-08-19", "yyyy-MM-dd");
		Date endDate = DateTransform.String2Date("2016-08-20", "yyyy-MM-dd");
		JacksonJson.printBeanToJson(indentDao.find(hql, new String[]{"indentName","beginDate","endDate"}, new Object[]{"%为让人%",beginDate,endDate}));
	}
	
	@Test
	public void test1(){
		JacksonJson.printBeanToJson(indentDao.myReceivedQuote(null, "", null, null, 31, null, 0, 10));
	}
	
	@Test
	public void test2(){
		JacksonJson.printBeanToJson(indentDao.getNameAndTele(1474688681110l));
	}
}
