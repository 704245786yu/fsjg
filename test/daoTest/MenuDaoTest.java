package daoTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sys.dao.MenuDao;
import com.util.JacksonJson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MenuDaoTest {

	@Autowired
	private MenuDao menuDao;
	
	@Test
	public void test1(){
//		JacksonJson.printBeanToJson(menuDao.getNodeWithDescendant(11));
		JacksonJson.printBeanToJson(menuDao.transformAdjTree(new Integer[]{1,3,4,6,9}));
	}
}
