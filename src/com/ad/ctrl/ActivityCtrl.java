package com.ad.ctrl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ad.biz.ActivityBiz;
import com.ad.biz.AdPositionBiz;
import com.ad.po.Activity;
import com.ad.po.AdPosition;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.sys.ctrl.UserCtrl;
import com.sys.po.User;
import com.util.JacksonJson;

@Controller
@RequestMapping("activity")
public class ActivityCtrl extends BaseCtrl<ActivityBiz, Integer, Activity> {

	@Autowired
	private AdPositionBiz adPositionBiz;
	
	/**显示后台管理页面*/
	@RequestMapping("showManage")
	public ModelAndView showManage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backstage/ad/activity");
		return mav;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView showDefaultPage(HttpSession session){
		ModelAndView mav = new ModelAndView("main/activity");
		BootTablePageDto<Activity> result = biz.getTitleByPage(null,null,null,null,null, 0, 20, null);
		List<Activity> list = result.getRows();
		ServletContext servletContext=session.getServletContext();
		HashMap<Long,String> districtCodeNameMap = (HashMap<Long,String>)servletContext.getAttribute("districtCodeNameMap");
		for(int i=0; i<list.size(); i++){
			Activity a = list.get(i);
			String provinceStr = districtCodeNameMap.get(a.getProvince());
			provinceStr = provinceStr==null ? "" : provinceStr;
			String cityStr = districtCodeNameMap.get(a.getCity());
			cityStr = cityStr==null ? "" : cityStr;
			String countyStr = districtCodeNameMap.get(a.getCounty());
			countyStr = countyStr==null ? "" : countyStr;
			a.setDetailAddr(provinceStr+" "+cityStr+" "+countyStr);
		}
		//广告位
		List<AdPosition> adPositions = adPositionBiz.getByCode("activity_list");
		mav.addObject("adPositions", JacksonJson.beanToJson(adPositions));
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
	public ModelAndView showDetail(@PathVariable int id,HttpSession session){
		ModelAndView mav = new ModelAndView("main/activityDetail");
		Activity activity = biz.getById(id);
		mav.addObject("activity", activity);
		//广告位
		List<AdPosition> adPositions = adPositionBiz.getByCode("activity_detail");
		mav.addObject("adPositions", JacksonJson.beanToJson(adPositions));
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("getList")
	@ResponseBody
	public BootTablePageDto<Activity> getList(HttpSession session,Byte type, Long province, Long city, Long county, Long town, int offset, Long total){
		BootTablePageDto<Activity> result =  biz.getTitleByPage(type, province, city, county, town, offset, 20, total);
		List<Activity> list = result.getRows();
		ServletContext servletContext=session.getServletContext();
		HashMap<Long,String> districtCodeNameMap = (HashMap<Long,String>)servletContext.getAttribute("districtCodeNameMap");
		for(int i=0; i<list.size(); i++){
			Activity a = list.get(i);
			String provinceStr = districtCodeNameMap.get(a.getProvince());
			provinceStr = provinceStr==null ? "" : provinceStr;
			String cityStr = districtCodeNameMap.get(a.getCity());
			cityStr = cityStr==null ? "" : cityStr;
			String countyStr = districtCodeNameMap.get(a.getCounty());
			countyStr = countyStr==null ? "" : countyStr;
			a.setDetailAddr(provinceStr+" "+cityStr+" "+countyStr);
		}
		return result;
	}
	
	@RequestMapping("findByParam")
	@ResponseBody
	public List<Activity> findByParam(String title, String beginDate, String endDate){
		return biz.findByParam(title, beginDate, endDate);
	}
}