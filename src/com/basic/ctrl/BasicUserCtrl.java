package com.basic.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.biz.BasicUserBiz;
import com.basic.biz.CostumeCategoryBiz;
import com.basic.biz.DistrictBiz;
import com.basic.biz.EnterpriseBiz;
import com.basic.biz.PersonBiz;
import com.basic.po.BasicUser;
import com.basic.po.Enterprise;
import com.basic.po.Person;
import com.basic.po.UserAbstract;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.sys.biz.ConstantDictBiz;
import com.sys.ctrl.LoginCtrl;
import com.sys.ctrl.UserCtrl;
import com.sys.po.ConstantDict;
import com.sys.po.User;

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
	public String modifyPwd(Integer id, String oldpassword,String newpassword,String passwordconfirm, HttpSession session){
		try{
			if(oldpassword==null||"".equals(oldpassword)||newpassword==null||"".equals(newpassword)||passwordconfirm==null||"".equals(passwordconfirm)){
				return "input_empty";
			}
			if(!newpassword.equals(passwordconfirm)){
				return "password_not_equal";
			}
			if(biz.checkPwd(id, oldpassword)){
				BasicUser loginUser = BasicUserCtrl.getLoginUser(session);
				int updateBy = loginUser.getId();
				biz.modifyPwd(id, newpassword, updateBy);
				return "success";
			}else{
				return "oldpassword_false";
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return "error";
		}
	}
	
	/**显示个人中心*/
	@RequestMapping("showMineInfo")
	public ModelAndView showMineInfo(HttpSession session){
		ModelAndView mav = null;
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		UserAbstract userAbstract = null;
		int roleId = basicUser.getRoleId();
		if(roleId == 1){
			mav = new ModelAndView("main/mineInfoPerson");
			userAbstract = personBiz.getByBasicUserId(basicUser.getId());
			
		}
		else if(roleId == 2){
			mav = new ModelAndView("main/mineInfo");
			Enterprise e = enterpriseBiz.getByBasicUserId(basicUser.getId());
			userAbstract = e;
			//行业分类
			String trade = e.getTrade();
			String[] trades = trade.split(",");
			List<Integer> codes = new ArrayList<Integer>();
			for(int i=0; i<trades.length; i++)
				codes.add(Integer.valueOf(trades[i]));
			List<String> tradeNames = costumeCategoryBiz.getNameByCode(codes);
			mav.addObject("tradeNames", tradeNames);
			//加工类型
			List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
			mav.addObject("processTypes", processTypes);
			//主营产品
			List<Integer> costumeCodes = e.getCostumeCode();
			List<String> costumeNames = costumeCategoryBiz.getNameByCode(costumeCodes);
			mav.addObject("costumeNames", costumeNames);
		}
		List<Long> districtCodes = new ArrayList<Long>();
		districtCodes.add(userAbstract.getProvince());
		districtCodes.add(userAbstract.getCity());
		districtCodes.add(userAbstract.getCounty());
		Long town = userAbstract.getTown();
		if(town != null)
			districtCodes.add(town);
		List<String> districtNames = districtBiz.getNameByCode(districtCodes);
		mav.addObject("districtNames", districtNames);
		mav.addObject("userInfo", userAbstract);
		return mav;
	}
	/**
	 * 企业个人中心信息修改
	 * @param userAbstract
	 * @return
	 */
	@RequestMapping(value="editEnterpriseInfo", method=RequestMethod.POST)
	public ModelAndView editEnterpriseInfo(String id,String employeeCount,String detailAddr,String qq,String wechat,String fix_phone,String orgCode,String enterpriseAge){
		BasicUser basicUser=new BasicUser();
		basicUser.setId(Integer.parseInt(id));
		Enterprise enterprise=new Enterprise();
		enterprise.setStaffNumber(Integer.parseInt(employeeCount));
		enterprise.setDetailAddr(detailAddr);
		enterprise.setQq(Long.parseLong(qq));
		enterprise.setWechat(wechat);
		enterprise.setFixPhone(fix_phone);
		enterprise.setOrgCode(orgCode);
		enterprise.setEnterpriseAge(Short.parseShort(enterpriseAge));
		enterprise.setBasicUser(basicUser);
		enterpriseBiz.update(enterprise);
		ModelAndView mav = new ModelAndView("main/mineInfo");
		return mav;
	}
	/**
	 * 个人用户个人中心信息修改
	 * @return
	 */
	@RequestMapping(value="editPersonInfo", method=RequestMethod.POST)
	public ModelAndView editPersonInfo(String id,String qq,String wechat,String postalCode,String detailAddr,String fixPhone){
		Person person=new Person();
		person.setId(Integer.parseInt(id));
		person.setFixPhone(fixPhone);
		person.setPostalCode(postalCode);
		person.setQq(Long.parseLong(qq));
		person.setWechat(wechat);
		person.setDetailAddr(detailAddr);
		personBiz.update(person);
		ModelAndView mav = new ModelAndView("main/mineInfoPerson");
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
