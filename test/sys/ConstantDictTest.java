package sys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dao.sys.ConstantDictDao;
import com.dao.sys.ConstantTypeDao;
import com.po.sys.ConstantDict;
import com.po.sys.ConstantType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ConstantDictTest {

	@Autowired
	private ConstantTypeDao constantTypeDao;
	@Autowired
	private ConstantDictDao constantDictDao;
	
	/**添加测试数据
	 * */
	@Test
	public void addDemoData(){
		//加工类型
		ConstantType ct = new ConstantType();
		ct.setConstantTypeCode("process_type");
		ct.setConstantTypeName("加工类型");
		
		ConstantDict cd = new ConstantDict();
		cd.setConstantTypeCode("process_type");
		cd.setConstantName("清加工");
		cd.setConstantValue(0);
		
	}
}
