package com.basic.ctrl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basic.biz.CostumeCategoryBiz;
import com.basic.po.CostumeCategory;
import com.common.NestTreeCtrl;
import com.sys.po.User;

@Controller
@RequestMapping("costumeCategory")
public class CostumeCategoryCtrl extends NestTreeCtrl<CostumeCategoryBiz, Integer, CostumeCategory>{

	public CostumeCategoryCtrl(){
		defaultPage = "backstage/costumeCategory";
	}
	
	@Override
	public CostumeCategory save(CostumeCategory po, HttpSession httpSession) {
		User user = (User)httpSession.getAttribute("loginUser");
//		po.setUpdateBy(user.getId());
		return super.save(po, httpSession);
	}

	@Override
	public CostumeCategory update(CostumeCategory po, HttpSession httpSession) {
		User user = (User)httpSession.getAttribute("loginUser");
//		po.setUpdateBy(user.getId());
		return super.update(po, httpSession);
	}

}