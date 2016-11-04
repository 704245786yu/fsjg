package com.ad.ctrl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ad.biz.TradeNewsBiz;
import com.ad.po.TradeNews;
import com.ad.vo.TradeNewsVo;
import com.common.BaseCtrl;
import com.sys.ctrl.UserCtrl;
import com.sys.po.User;

@Controller
@RequestMapping("tradeNews")
public class TradeNewsCtrl extends BaseCtrl<TradeNewsBiz, Integer, TradeNews> {

	/**显示后台管理页面*/
	@RequestMapping("showManage")
	public ModelAndView showManage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backstage/ad/tradeNews");
		return mav;
	}

	@RequestMapping("saveTradeNews")
	@ResponseBody
	public TradeNewsVo saveAffiche(TradeNews po, HttpSession session) {
		User user = UserCtrl.getLoginUser(session);
		po.setUpdateBy(user.getId());
		biz.save(po);
		
		TradeNewsVo vo = new TradeNewsVo();
		vo.setId(po.getId());
		vo.setRealName(user.getRealName());
		vo.setTitle(po.getTitle());
		vo.setUpdateTime(new Date());
		return vo;
	}

	@RequestMapping("updateTradeNews")
	@ResponseBody
	public TradeNewsVo updateAffiche(TradeNews po, HttpSession session) {
		User user = UserCtrl.getLoginUser(session);
		po.setUpdateBy(user.getId());
		biz.update(po);
		
		TradeNewsVo vo = new TradeNewsVo();
		vo.setId(po.getId());
		vo.setRealName(user.getRealName());
		vo.setTitle(po.getTitle());
		vo.setUpdateTime(new Date());
		return vo;
	}

	@RequestMapping(value="getContent",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getContent(int id){
		return biz.getById(id).getContent();
	}
	
	@RequestMapping("showDetail/{id}")
	public ModelAndView showDetail(@PathVariable int id){
		ModelAndView mav = new ModelAndView("main/tradeNewsDetail");
		TradeNews tradeNews = biz.getById(id);
		mav.addObject("tradeNews", tradeNews);
		return mav;
	}
	
	/**获取所有咨询标题
	 * */
	@RequestMapping("getAllTitle")
	@ResponseBody
	public List<Object[]> getAllTitle(){
		return biz.getAllTitle();
	}
	
	@RequestMapping("findByParam")
	@ResponseBody
	public List<TradeNewsVo> findByParam(String title, String beginDate, String endDate){
		return biz.findByParam(title, beginDate, endDate);
	}
}