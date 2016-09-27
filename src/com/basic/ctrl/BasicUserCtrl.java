package com.basic.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.biz.BasicUserBiz;
import com.basic.biz.CostumeCategoryBiz;
import com.basic.biz.DistrictBiz;
import com.basic.biz.EnterpriseBiz;
import com.basic.biz.PersonBiz;
import com.basic.po.BasicUser;
import com.basic.po.Enterprise;
import com.basic.po.UserAbstract;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.common.vo.ValidVo;
import com.sys.biz.ConstantDictBiz;
import com.sys.ctrl.LoginCtrl;
import com.sys.po.ConstantDict;

@Controller
@RequestMapping("basicUser")
public class BasicUserCtrl extends BaseCtrl<BasicUserBiz, Integer, BasicUser> {
	
	@Autowired
	private PersonBiz personBiz;
	@Autowired
	private EnterpriseBiz enterpriseBiz;
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
	
	/**显示个人中心*/
	@RequestMapping("showMyCenter")
	public ModelAndView showMineInfo(HttpSession session){
		ModelAndView mav = null;
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		UserAbstract userAbstract = null;
		mav = new ModelAndView("main/myCenter/myCenter");
		int roleId = basicUser.getRoleId();
		if(roleId == 1){
			userAbstract = personBiz.getByBasicUserId(basicUser.getId());
		}else if(roleId == 2){
			Enterprise e = enterpriseBiz.getByBasicUserId(basicUser.getId());
			userAbstract = e;
			//行业分类，工厂刚注册时有可能还未选择所属行业
			String trade = e.getTrade();
			if(trade != null){
				String[] trades = trade.split(",");
				List<Integer> codes = new ArrayList<Integer>();
				for(int i=0; i<trades.length; i++)
					codes.add(Integer.valueOf(trades[i]));
				List<String> tradeNames = costumeCategoryBiz.getNameByCode(codes);
				mav.addObject("tradeNames", tradeNames);
			}
			//加工类型
			List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
			mav.addObject("processTypes", processTypes);
			//主营产品，工厂刚注册时有可能还未选择主营产品
			List<Integer> costumeCodes = e.getCostumeCode();
			if(costumeCodes.size()>0){
				List<String> costumeNames = costumeCategoryBiz.getNameByCode(costumeCodes);
				mav.addObject("costumeNames", costumeNames);
			}
		}
		List<Long> districtCodes = new ArrayList<Long>();
		districtCodes.add(userAbstract.getProvince());
		districtCodes.add(userAbstract.getCity());
		districtCodes.add(userAbstract.getCounty());
		districtCodes.add(userAbstract.getTown());
//		List<String> districtNames = districtBiz.getNameByCode(districtCodes);
//		mav.addObject("districtNames", districtNames);
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
