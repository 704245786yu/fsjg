package sys;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sys.biz.MenuBiz;
import com.sys.ctrl.MenuCtrl;
import com.sys.dao.MenuDao;
import com.sys.po.Menu;
import com.util.JacksonJson;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("/applicationContext.xml")
public class MenuTest {
	
	@Autowired
	private MenuCtrl menuCtrl;
	
	@Autowired
	private MenuBiz menuBiz;
	
	@Autowired
	private MenuDao menuDao;
	
	@Test
	public void testGetAdjTree(){
		JacksonJson.printBeanToJson(menuDao.transformAdjTree(new Integer[]{2,3}));
	}

	@Test
	public void getNodeWithDescendant(){
		System.out.println(JacksonJson.beanToJson(menuDao.getNodeWithDescendant(1)));
	}
	
	@Test
	public void getDescendantId(){
		List<Integer> list = menuDao.getDescendantId(1);
		System.out.println(JacksonJson.beanToJson(list));
	}

	@Test
	public void getMenuByRoleId(){
		JacksonJson.printBeanToJson(menuBiz.getMenuByRoleId(2));
	}
	
	@Test
	public void initMenus(){
		Menu root = saveRootNode();
		
		Menu home = getMenu("首页","home",null,root.getId());
		menuBiz.save(home);
		Menu person = getMenu("个人用户管理","person/backstage",null,root.getId());
		menuBiz.save(person);
		Menu enterprise = getMenu("工厂用户管理","enterprise/backstage",null,root.getId());
		menuBiz.save(enterprise);
		Menu personContractor = getMenu("快产专家管理","personContractor",null,root.getId());
		menuBiz.save(personContractor);
		
		//系统管理
		Menu sys = getMenu("系统管理",null,null,root.getId());
		menuBiz.save(sys);
		Menu user = getMenu("系统后台用户管理","user",null,sys.getId());
		menuBiz.save(user);
		Menu role = getMenu("角色管理","role",null,sys.getId());
		menuBiz.save(role);
		Menu district = getMenu("地区管理","district",null,sys.getId());
		menuBiz.save(district);
		Menu costumeCategory = getMenu("服饰类型管理","costumeCategory",null,sys.getId());
		menuBiz.save(costumeCategory);
		
		//核心功能管理
		Menu core = getMenu("核心功能管理",null,null,root.getId());
		menuBiz.save(core);
		Menu menu = getMenu("菜单管理","menu",null,core.getId());
		menuBiz.save(menu);
		Menu constantType = getMenu("常量类型管理","constantType",null,core.getId());
		menuBiz.save(constantType);
		Menu dict = getMenu("字典常量管理","constantDict",null,core.getId());
		menuBiz.save(dict);
		
		//功能测试
		Menu testFun = getMenu("功能测试",null,null,root.getId());
		menuBiz.save(testFun);
		Menu dateRangePickderDemo = getMenu("dateRangePickderDemo","person/dateRangePickerDemo",null,testFun.getId());
		menuBiz.save(dateRangePickderDemo);
	}
	
	protected Menu getMenu(String menuName, 
			String path, String imgPath, Integer pId){
		Menu menu = new Menu();
		menu.setMenuName(menuName);
		menu.setPath(path);
		menu.setImgPath(imgPath);
		menu.setpId(pId);
		return menu;
	}
	
	/**保存根节点*/
	@SuppressWarnings("deprecation")
	protected Menu saveRootNode(){
		Menu menu = getMenu("根菜单",null,null,null);
		menuDao.addRootNode(menu);
		return menu;
	}
	
}
