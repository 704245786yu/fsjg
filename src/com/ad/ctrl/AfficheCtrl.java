package com.ad.ctrl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ad.biz.AfficheBiz;
import com.ad.po.Affiche;
import com.ad.vo.AfficheVo;
import com.common.BaseCtrl;
import com.sys.ctrl.UserCtrl;
import com.sys.po.User;

@Controller
@RequestMapping("affiche")
public class AfficheCtrl extends BaseCtrl<AfficheBiz, Integer, Affiche> {

	/**显示后台管理页面*/
	@RequestMapping("showManage")
	public ModelAndView showManage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backstage/ad/affiche");
		return mav;
	}

	@RequestMapping("saveAffiche")
	@ResponseBody
	public AfficheVo saveAffiche(Affiche po, HttpSession session) {
		User user = UserCtrl.getLoginUser(session);
		po.setUpdateBy(user.getId());
		biz.save(po);
		
		AfficheVo vo = new AfficheVo();
		vo.setId(po.getId());
		vo.setRealName(user.getRealName());
		vo.setTitle(po.getTitle());
		vo.setUpdateTime(new Date());
		return vo;
	}

	@RequestMapping("updateAffiche")
	@ResponseBody
	public AfficheVo updateAffiche(Affiche po, HttpSession session) {
		User user = UserCtrl.getLoginUser(session);
		po.setUpdateBy(user.getId());
		biz.update(po);
		
		AfficheVo vo = new AfficheVo();
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
		ModelAndView mav = new ModelAndView("main/afficheDetail");
		Affiche affiche = biz.getById(id);
		mav.addObject("affiche", affiche);
		return mav;
	}
	
	/**获取所有公告标题
	 * */
	@RequestMapping("getAllTitle")
	@ResponseBody
	public List<Object[]> getAllTitle(){
		return biz.getAllTitle();
	}
	
	@RequestMapping("findByParam")
	@ResponseBody
	public List<AfficheVo> findByParam(String title, String beginDate, String endDate){
		return biz.findByParam(title, beginDate, endDate);
	}
}