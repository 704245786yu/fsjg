package com.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings({"rawtypes","unchecked"})
public class BaseCtrl<BIZ extends BaseBiz, PO> {

	@Autowired
	private BIZ biz;

	protected String defaultPage; //设置Ctrl默认显示的页面，该属性由子类初始化时设置
	
	/**显示默认的页面*/
	@RequestMapping
	public String showDefaultPage(){
		return defaultPage;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public PO save(PO po){
		System.out.println("save:"+po);
		return po;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public PO update(PO po){
		System.out.println("update:"+po);
		return po;
	}
	
	@RequestMapping("getAll")
	@ResponseBody
	public List<PO> getAll(){
		return biz.getAll();
	}
}
