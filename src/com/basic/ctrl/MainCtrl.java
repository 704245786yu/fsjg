package com.basic.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ad.biz.AdPositionBiz;
import com.ad.biz.BlogrollBiz;
import com.ad.biz.TradeNewsBiz;
import com.ad.po.AdPosition;
import com.ad.po.Blogroll;
import com.ad.po.TradeNews;
import com.basic.biz.EnterpriseBiz;
import com.basic.vo.AuthEnterpriseVo;
import com.basic.vo.StrengthEnterpriseVo;
import com.util.JacksonJson;

@Controller
public class MainCtrl {

	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private BlogrollBiz blogrollBiz;
	@Autowired
	private AdPositionBiz adPositionBiz;
	@Autowired
	private TradeNewsBiz tradeNewsBiz;
	
	@RequestMapping({"/","home"})
	public String home(Model model){
		//实力工厂
		List<StrengthEnterpriseVo> strengths = enterpriseBiz.getStrength(6);
		model.addAttribute("strengths", strengths);
		//认证工厂
		List<AuthEnterpriseVo> auths = enterpriseBiz.getNewAuth(6,false);
		model.addAttribute("auths", auths);
		//友情链接
		List<Blogroll> blogrolls = blogrollBiz.getAll();
		model.addAttribute("blogrolls", blogrolls);
		//广告位
		List<AdPosition> adPositions = adPositionBiz.getByCode("home");
		model.addAttribute("adPositions", JacksonJson.beanToJson(adPositions));
		//行业资讯
		List<TradeNews> tradeNews = tradeNewsBiz.getTen();
		model.addAttribute("tradeNews", tradeNews);
		return "../home";
	}
	
	@RequestMapping("forgetPwd")
	public String forgetPwd(){
		return "main/forgetPwd";
	}
}
