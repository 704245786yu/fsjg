package com.ctrl.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biz.sys.ConstantTypeBiz;
import com.common.BaseCtrl;
import com.po.sys.ConstantType;

@Controller
@RequestMapping("constantType")
public class ConstantTypeCtrl extends BaseCtrl<ConstantTypeBiz, Integer, ConstantType>{

	public ConstantTypeCtrl(){
		defaultPage = "sys/constantType";
	}
}
