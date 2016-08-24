package basic;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basic.biz.DistrictBiz;
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
	
	@Test
	public void test2(){
		ArrayList<Long> districtCodes = new ArrayList<Long>();
		districtCodes.add(350000l);
		districtCodes.add(310101015000l);
		districtCodes.add(310104003000l);
		districtCodes.add(null);
		JacksonJson.printBeanToJson(districtBiz.getNameByCode(districtCodes));
	}
}
