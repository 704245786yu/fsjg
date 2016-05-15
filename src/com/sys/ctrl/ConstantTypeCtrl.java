package com.sys.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.BaseCtrl;
import com.sys.biz.ConstantTypeBiz;
import com.sys.po.ConstantType;

@Controller
@RequestMapping("constantType")
public class ConstantTypeCtrl extends BaseCtrl<ConstantTypeBiz, Integer, ConstantType>{

	public ConstantTypeCtrl(){
		defaultPage = "sys/constantType";
	}
}
