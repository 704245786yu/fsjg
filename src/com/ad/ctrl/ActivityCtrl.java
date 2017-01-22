package com.ad.ctrl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ad.biz.ActivityBiz;
import com.ad.po.Activity;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.sys.ctrl.UserCtrl;
import com.sys.po.User;

@Controller
@RequestMapping("activity")
public class ActivityCtrl extends BaseCtrl<ActivityBiz, Integer, Activity> {

	/**显示后台管理页面*/
	@RequestMapping("showManage")
	public ModelAndView showManage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backstage/ad/activity");
		return mav;
	}

	public ModelAndView showDefaultPage(HttpSession session){
		ModelAndView mav = new ModelAndView("main/activity");
		BootTablePageDto<Activity> result = biz.getTitleByPage(0, 20, null);
		mav.addObject("result", result);
		return mav;
	}
	
	@RequestMapping("saveActivity")
	@ResponseBody
	public Activity saveAffiche(Activity po, HttpSession session) {
		User user = UserCtrl.getLoginUser(session);
		po.setUpdateBy(user.getId());
		biz.save(po);
		
		po.setRealName(user.getRealName());
		po.setUpdateTime(new Date());
		return po;
	}

	@RequestMapping("updateActivity")
	@ResponseBody
	public Activity updateAffiche(Activity po, HttpSession session) {
		User user = UserCtrl.getLoginUser(session);
		po.setUpdateBy(user.getId());
		biz.update(po);
		
		po.setRealName(user.getRealName());
		po.setUpdateTime(new Date());
		return po;
	}

	@RequestMapping(value="getContent",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getContent(int id){
		return biz.getById(id).getContent();
	}
	
	@RequestMapping("showDetail/{id}")
	public ModelAndView showDetail(@PathVariable int id){
		ModelAndView mav = new ModelAndView("main/activityDetail");
		Activity activity = biz.getById(id);
		mav.addObject("activity", activity);
		return mav;
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public BootTablePageDto<Activity> getList(int offset, Long total){
		return biz.getTitleByPage(offset, 20, total);
	}
	
	/**获取所有咨询标题
	 * */
	/*@RequestMapping("getAllTitle")
	@ResponseBody
	public List<Object[]> getAllTitle(){
		return biz.getAllTitle();
	}*/
	
	@RequestMapping("findByParam")
	@ResponseBody
	public List<Activity> findByParam(String title, String beginDate, String endDate){
		return biz.findByParam(title, beginDate, endDate);
	}
}