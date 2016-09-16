package com.basic.ctrl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.biz.CostumeCategoryBiz;
import com.basic.po.CostumeCategory;
import com.common.NestTreeCtrl;
import com.sys.ctrl.UserCtrl;
import com.sys.po.User;

@Controller
@RequestMapping("costumeCategory")
public class CostumeCategoryCtrl extends NestTreeCtrl<CostumeCategoryBiz, Integer, CostumeCategory>{

	public CostumeCategoryCtrl(){
		defaultPage = "backstage/costumeCategory";
	}
	
	@Override
	public CostumeCategory save(CostumeCategory po, HttpSession session) {
		User user = UserCtrl.getLoginUser(session);
		po.setUpdateBy(user.getId());
		return super.save(po, session);
	}

	@Override
	public CostumeCategory update(CostumeCategory po, HttpSession session) {
		User user = UserCtrl.getLoginUser(session);
		po.setUpdateBy(user.getId());
		return super.update(po, session);
	}

	/**获取行业分类*/
	@RequestMapping("getTrade")
	@ResponseBody
	public HashMap<String,Integer> getTrade(){
		return biz.getChildCostumeMap(null);
	}
	
	/**获取所有节点包含层级信息*/
	@RequestMapping("getAllHierarchy")
	@ResponseBody
	public List<CostumeCategory> getAllHierarchy(){
		return biz.getNodeWithDescendant(1);
	}
}