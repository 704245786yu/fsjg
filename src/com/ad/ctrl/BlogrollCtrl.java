package com.ad.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ad.biz.BlogrollBiz;
import com.ad.po.Blogroll;
import com.common.BaseCtrl;

@Controller
@RequestMapping("blogroll")
public class BlogrollCtrl extends BaseCtrl<BlogrollBiz, Integer, Blogroll> {

	/**显示后台管理页面*/
	@RequestMapping("showManage")
	public ModelAndView showManage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backstage/ad/blogroll");
		return mav;
	}
	
}