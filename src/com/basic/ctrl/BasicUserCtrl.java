package com.basic.ctrl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.basic.biz.BasicUserBiz;
import com.basic.biz.EnterpriseBiz;
import com.basic.biz.PersonBiz;
import com.basic.po.BasicUser;
import com.basic.po.UserAbstract;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.sys.ctrl.LoginCtrl;

@Controller
@RequestMapping("basicUser")
public class BasicUserCtrl extends BaseCtrl<BasicUserBiz, Integer, BasicUser> {
	
	@Autowired
	private PersonBiz personBiz;
	
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	
	/**获取当前登录用户*/
	public static BasicUser getLoginUser(HttpSession session){
		return (BasicUser)session.getAttribute(LoginCtrl.loginBasicUser);
	}
	
	/**密码验证
	 * */
	/*@RequestMapping("checkPwd")
	@ResponseBody
	public String checkPwd(Integer userId, String oldPassword){
		return biz.checkPwd(userId, oldPassword);
	}*/
	
	/**修改密码
	 * @return success：修改成功
	 * */
//	@RequestMapping("modifyPwd")
//	@ResponseBody
	/*public String modifyPwd(Integer id, String password, HttpSession session){
		User loginUser = BasicUserCtrl.getLoginUser(session);
		int updateBy = loginUser.getId();
		biz.modifyPwd(id, password, updateBy);
		return "success";
	}*/
	
	/**显示个人中心*/
	@RequestMapping("showMineInfo")
	public ModelAndView showMineInfo(HttpSession session){
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		UserAbstract userAbstract = null;
		int roleId = basicUser.getRoleId();
		if(roleId == 1)
			userAbstract = personBiz.getByBasicUserId(basicUser.getId());
		else if(roleId == 2)
			userAbstract = enterpriseBiz.getByBasicUserId(basicUser.getId());
		ModelAndView mav = new ModelAndView("main/mineInfo");
		mav.addObject("userInfo", userAbstract);
		return mav;
	}
	
	@Override
	public List<BasicUser> getAll(){
		return null;
	}

	@Override
	public BootTablePageDto<BasicUser> getAllByPage(Long total, int offset, int pageSize){
		return null;
	}
}
