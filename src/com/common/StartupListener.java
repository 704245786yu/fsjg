package com.common;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.basic.biz.CostumeCategoryBiz;
import com.basic.ctrl.CostumeCategoryCtrl;
import com.basic.po.CostumeCategory;
import com.sys.biz.ConstantDictBiz;
import com.sys.po.ConstantDict;
import com.util.JacksonJson;

@WebListener
public class StartupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		
		CostumeCategoryBiz costumeCategoryBiz = wac.getBean(CostumeCategoryBiz.class);
		HashMap<Integer,String> codeNameMap = costumeCategoryBiz.getAllCodeNameMap();
		sc.setAttribute("costumeCategoryMap", JacksonJson.beanToJson(codeNameMap));
		//服饰类型层次树
		CostumeCategoryCtrl costumeCategoryCtrl = wac.getBean(CostumeCategoryCtrl.class);
		List<CostumeCategory> costumeCategoryList = costumeCategoryCtrl.getAllHierarchy();
		sc.setAttribute("costumeCategoryList",JacksonJson.beanToJson(costumeCategoryList));
		
		//行业分类
		sc.setAttribute("trade", costumeCategoryBiz.getTrade());
		
		//加工类型
		ConstantDictBiz constantDictBiz = wac.getBean(ConstantDictBiz.class);
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		sc.setAttribute("processTypes", processTypes);
		HashMap<String,String> processTypeMap = constantDictBiz.getValueNameMap("process_type");
		sc.setAttribute("processTypeMap", JacksonJson.beanToJson(processTypeMap));
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}


}
