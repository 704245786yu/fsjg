package ad;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ad.dao.ActivityDao;
import com.util.JacksonJson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ActivityTest {

	@Autowired
	private ActivityDao activityDao;
	
	@Test
	public void test1(){
		JacksonJson.printBeanToJson(activityDao.findByParam("", null, null));
	}
	
}
