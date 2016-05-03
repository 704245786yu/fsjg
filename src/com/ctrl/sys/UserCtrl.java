package com.ctrl.sys;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.sys.UserBiz;
import com.common.BaseCtrl;
import com.dto.BootTablePageDto;
import com.po.sys.User;

@Controller
@RequestMapping("user")
public class UserCtrl extends BaseCtrl<UserBiz, Integer, User> {

	public UserCtrl() {
		defaultPage = "sys/user";
	}

	/**
	 * 检验用户输入的“原密码”与真实密码是否一致
	 * 
	 * @param oldPassWord
	 *            用户输入的“原密码”
	 * @param userName
	 * @return
	 */
	/*@RequestMapping("checkOldPwd")
	@ResponseBody
	public Boolean checkOldPwd(String oldPassWord, String userName) {

		BootTablePageDto<User> bt = new BootTablePageDto<User>();
		bt = UserBiz.findByPageAndParams(0, 5, userName);
		String pwd = bt.getRows().get(0).getPassWord().toString();
		//System.out.println(pwd);
		if (pwd.equals(oldPassWord)) {
			return true;
		} else {
			return false;

		}

	}*/
	@RequestMapping("save")
	@ResponseBody
	public User save(User User) {

		User.setUpdateBy(888);// 用户ID，暂时写死，后期获取登陆用户来获得此数据
		Date date = new Date();
		User.setUpdateTime(date);
		return super.save(User);

	}
	@RequestMapping("update")
	@ResponseBody
	public User update(User User) {
		User.setUpdateBy(888);// 用户ID，暂时写死，后期获取登陆用户来获得此数据
		Date date = new Date();
		User.setUpdateTime(date);
		return super.update(User);
	}
}
