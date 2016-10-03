package com.ad.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ad.biz.BlogrollBiz;
import com.ad.po.Blogroll;
import com.common.BaseCtrl;

@Controller
@RequestMapping("blogroll")
public class BlogrollCtrl extends BaseCtrl<BlogrollBiz, Integer, Blogroll> {

	public BlogrollCtrl(){
		defaultPage = "backstage/ad/blogroll";
	}

}