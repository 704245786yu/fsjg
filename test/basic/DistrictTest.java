package basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.biz.basic.DistrictBiz;
import com.util.JacksonJson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DistrictTest {

	@Autowired
	private DistrictBiz districtBiz;
	
	@Test
	public void test1(){
		JacksonJson.printBeanToJson(districtBiz.getNameAndCodeByPcode(null));
	}
}
