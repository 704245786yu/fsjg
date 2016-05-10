package com.ctrl.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.biz.sys.ConstantDictBiz;
import com.biz.sys.ConstantTypeBiz;
import com.common.BaseCtrl;
import com.dto.BootTablePageDto;
import com.po.sys.ConstantDict;
import com.po.sys.ConstantType;

@Controller
@RequestMapping("constantDict")
public class ConstantDictCtrl extends BaseCtrl<ConstantDictBiz, Integer, ConstantDict>{

	@Autowired
	private ConstantTypeBiz constantTypeBiz;
	
	public ConstantDictCtrl(){
		defaultPage = "sys/constantDict";
	}

	@Override
	@RequestMapping
	public ModelAndView showDefaultPage() {
		List<ConstantType> constantTypes = constantTypeBiz.getAll();
		ModelAndView mav = new ModelAndView();
		mav.setViewName(defaultPage);
		mav.addObject("constantTypes", constantTypes);
		return mav;
	}

	/**根据搜索条件分页查询数据。searchText用于模糊匹配查询常量名称和常量类型名称。
	 * @param offset 偏移量，即记录索引位置
	 * @param pageSize 每页记录数
	 * @param constantName 要模糊查询的常量名称
	 * */
	@RequestMapping("findByPageAndParams")
	@ResponseBody
	public BootTablePageDto<ConstantDict> findByPageAndParams(int offset, int pageSize, String constantName){
		return biz.findByPageAndParams(offset,pageSize,constantName);
	}
}
