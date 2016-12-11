package com.basic.ctrl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.biz.BasicUserBiz;
import com.basic.biz.ContractorBiz;
import com.basic.biz.CostumeCategoryBiz;
import com.basic.biz.DistrictBiz;
import com.basic.biz.EnterpriseBiz;
import com.basic.biz.PersonBiz;
import com.basic.po.BasicUser;
import com.basic.po.Contractor;
import com.basic.po.Enterprise;
import com.basic.po.Person;
import com.basic.po.UserAbstract;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.common.vo.ValidVo;
import com.sys.biz.ConstantDictBiz;
import com.sys.ctrl.LoginCtrl;
import com.sys.ctrl.UserCtrl;
import com.sys.po.User;

@Controller
@RequestMapping("basicUser")
public class BasicUserCtrl extends BaseCtrl<BasicUserBiz, Integer, BasicUser> {
	
	@Autowired
	private PersonBiz personBiz;
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private ContractorBiz contractorBiz;
	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	@Autowired
	private ConstantDictBiz constantDictBiz;
	@Autowired
	private DistrictBiz districtBiz;
	
	/**获取当前登录用户*/
	public static BasicUser getLoginUser(HttpSession session){
		return (BasicUser)session.getAttribute(LoginCtrl.loginBasicUser);
	}
	
	/**密码验证。只能验证当前用户的密码是否正确
	 * */
	@RequestMapping("checkMyPwd")
	@ResponseBody
	public HashMap<String,Boolean> checkMyPwd(String oldPassword,HttpSession session){
		int userId = getLoginUser(session).getId();
		HashMap<String,Boolean> map =new HashMap<String,Boolean>();
		map.put("valid", biz.checkPwd(userId, oldPassword));
		return map;
	}
	
	/**修改密码
	 * @return success：修改成功
	 * */
	@RequestMapping("modifyPwd")
	@ResponseBody
	public String modifyPwd(String password, HttpSession session){
		int userId = BasicUserCtrl.getLoginUser(session).getId();
		biz.modifyPwd(userId, password);
		return "success";
	}
	
	/**校验用户名是否存在,判断是否存在时排除id值
	 * */
	@RequestMapping(value="isNameExist")
	@ResponseBody
	public ValidVo isNameExist(String userName, HttpSession session){
		BasicUser b = getLoginUser(session);
		return new ValidVo( ! biz.nameIsExist(userName,b.getId()) );
	}
	
	/**校验手机号是否存在*/
	@RequestMapping(value="isTeleExist")
	@ResponseBody
	public ValidVo isTeleExist(Long telephone, HttpSession session){
		BasicUser b = getLoginUser(session);
		return new ValidVo( ! biz.teleIsExist(telephone,b.getId()) );
	}
	
	@RequestMapping(value="isNameExist2")
	@ResponseBody
	public ValidVo isNameExist2(Integer id,String userName){
		return new ValidVo( ! biz.nameIsExist(userName,id) );
	}
	
	@RequestMapping(value="isTeleExist2")
	@ResponseBody
	public ValidVo isTeleExist2(Integer id,Long telephone){
		return new ValidVo( ! biz.teleIsExist(telephone,id) );
	}
	
	/**显示个人中心
	 * @param index 显示个人中心第几个模块，eg:index=3,用在发布订单后，去个人中心查看当前发布的订单
	 * */
	@RequestMapping("showMyCenter")
	public ModelAndView showMyCenter(Integer index, HttpSession session){
		ModelAndView mav = null;
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		UserAbstract userAbstract = null;
		mav = new ModelAndView("main/myCenter/myCenter");
		int roleId = basicUser.getRoleId();
		if(roleId == 1){
			userAbstract = personBiz.getByBasicUserId(basicUser.getId());
			//获取快产专家
			Contractor contractor = contractorBiz.getById(userAbstract.getId());
			mav.addObject("contractor", contractor);
		}else if(roleId == 2){
			userAbstract = enterpriseBiz.getByBasicUserId(basicUser.getId());
		}
		mav.addObject("index",index);//显示个人中心第几个模块
		mav.addObject("userInfo", userAbstract);
		return mav;
	}
	
	/**用户状态更改：冻结 解冻*/
	@RequestMapping("modifyState")
	@ResponseBody
	public ReturnValueVo modifyState(int id,byte state,HttpSession session){
		User user = UserCtrl.getLoginUser(session);
		biz.modifyState(id, state, user.getId());
		return new ReturnValueVo(ReturnValueVo.SUCCESS,null);
	}
	
	/**后台订单信息详细查看：获取用户基本信息
	 * @param userType 1:普通用户 2:工厂用户
	 * @return 用户名称，用户所在地区
	 * */
	@RequestMapping("getUserInfo")
	@ResponseBody
	public HashMap<String,String> getUserInfo(int userId,int userType){
		HashMap<String,String> map = new HashMap<String,String>();
		UserAbstract userAbstract = null;
		if(userType==1){
			Person person = personBiz.getByBasicUserId(userId);
			userAbstract = person;
			map.put("userName", person.getRealName());
		}else if(userType==2){
			Enterprise e = enterpriseBiz.getByBasicUserId(userId);
			userAbstract = e;
			map.put("userName", e.getEnterpriseName());
		}
		List<String> names = districtBiz.getNameByUser(userAbstract);
		StringBuffer str = new StringBuffer();
		for(int i=0; i<names.size(); i++)
			str.append(names.get(i));
		str.append(userAbstract.getDetailAddr());
		map.put("district", str.toString());
		return map;
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
