package com.basic.ctrl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.biz.BasicUserBiz;
import com.basic.po.BasicUser;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.sys.ctrl.LoginCtrl;

@Controller
@RequestMapping("basicUser")
public class BasicUserCtrl extends BaseCtrl<BasicUserBiz, Integer, BasicUser> {
	
	/**获取当前登录用户*/
	public static BasicUser getLoginUser(HttpSession session){
		return (BasicUser)session.getAttribute(LoginCtrl.loginUserKey);
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
	@RequestMapping("modifyPwd")
	@ResponseBody
	/*public String modifyPwd(Integer id, String password, HttpSession session){
		User loginUser = BasicUserCtrl.getLoginUser(session);
		int updateBy = loginUser.getId();
		biz.modifyPwd(id, password, updateBy);
		return "success";
	}*/
	
	@Override
	public List<BasicUser> getAll(){
		return null;
	}

	@Override
	public BootTablePageDto<BasicUser> getAllByPage(Long total, int offset, int pageSize){
		return null;
	}
}
