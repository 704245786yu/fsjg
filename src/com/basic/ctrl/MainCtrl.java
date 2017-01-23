package com.basic.ctrl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ad.biz.ActivityBiz;
import com.ad.biz.AdPositionBiz;
import com.ad.biz.BlogrollBiz;
import com.ad.biz.TradeNewsBiz;
import com.ad.po.Activity;
import com.ad.po.AdPosition;
import com.ad.po.Blogroll;
import com.ad.po.TradeNews;
import com.basic.biz.ContractorBiz;
import com.basic.biz.EnterpriseBiz;
import com.basic.biz.IndentBiz;
import com.basic.po.Indent;
import com.basic.vo.AuthEnterpriseVo;
import com.basic.vo.ContractorHomeVo;
import com.basic.vo.StrengthEnterpriseVo;
import com.common.vo.ReturnValueVo;
import com.util.JacksonJson;

@Controller
public class MainCtrl {

	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private ContractorBiz contractorBiz;
	@Autowired
	private IndentBiz indentBiz;
	@Autowired
	private BlogrollBiz blogrollBiz;
	@Autowired
	private AdPositionBiz adPositionBiz;
	@Autowired
	private TradeNewsBiz tradeNewsBiz;
	@Autowired
	private ActivityBiz activityBiz;
	
	@SuppressWarnings("unchecked")
	@RequestMapping({"/","home"})
	public String home(Model model,HttpSession session){
		ServletContext servletContext=session.getServletContext();
		HashMap<Long,String> districtCodeNameMap = (HashMap<Long,String>)servletContext.getAttribute("districtCodeNameMap");
		//实力工厂
		List<StrengthEnterpriseVo> strengths = enterpriseBiz.getStrength(6);
		model.addAttribute("strengths", strengths);
		//认证工厂
		List<AuthEnterpriseVo> auths = enterpriseBiz.getNewAuth(6,false);
		model.addAttribute("auths", auths);
		//快产专家
		List<ContractorHomeVo> contractors = contractorBiz.getHomeList();
		model.addAttribute("contractors", contractors);
		//最新订单
		List<Indent> indents = indentBiz.getHomeList(districtCodeNameMap);
		model.addAttribute("indents", indents);
		//友情链接
		List<Blogroll> blogrolls = blogrollBiz.getAll();
		model.addAttribute("blogrolls", blogrolls);
		//广告位
		List<AdPosition> adPositions = adPositionBiz.getByCode("home");
		model.addAttribute("adPositions", JacksonJson.beanToJson(adPositions));
		//行业资讯
		List<TradeNews> tradeNews = tradeNewsBiz.getTen();
		model.addAttribute("tradeNews", tradeNews);
		//活动推广
		List<Activity> activitySlide = activityBiz.getFive();
		model.addAttribute("activitySlide", activitySlide);
		List<Activity> activities = activityBiz.getHomeList();
		model.addAttribute("activities", activities);
		return "../home";
	}
	
	@RequestMapping("forgetPwd")
	public String forgetPwd(){
		return "main/forgetPwd";
	}
	
	/**忘记密码时用*/
	@SuppressWarnings("unchecked")
	@RequestMapping("checkSmsCode")
	@ResponseBody
	public ReturnValueVo checkSmsCode(long telephone, int smsNum, HttpSession session){
		//判断验证码是否正确且有效
		HashMap<String,Long> sms = (HashMap<String,Long>)session.getAttribute("sms");
		ReturnValueVo returnValue = new ReturnValueVo();
		if(sms==null){
			returnValue.setStatus(ReturnValueVo.ERROR);
			return returnValue;
		}
		long now = System.currentTimeMillis();
		long generateTime = sms.get("generateTime");
		long generateSmsNum = sms.get("smsNum");	//session里保存的验证码
		if((now-generateTime)<=60000 && (generateSmsNum == smsNum)){	//60秒有效
			returnValue.setStatus(ReturnValueVo.SUCCESS);
		}else{
			returnValue.setStatus(ReturnValueVo.ERROR);
		}
		returnValue.setStatus(ReturnValueVo.SUCCESS);
		return returnValue;
	}
}
