package com.basic.ctrl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.biz.EnterpriseBiz;
import com.basic.biz.IndentQuoteBiz;
import com.basic.po.BasicUser;
import com.basic.po.IndentQuote;
import com.basic.vo.QuoteEnterpriseVo;
import com.common.BaseCtrl;
import com.common.vo.ReturnValueVo;

/**订单报价
 * */
@Controller
@RequestMapping("indentQuote")
public class IndentQuoteCtrl extends BaseCtrl<IndentQuoteBiz,Integer,IndentQuote>{
	
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	
	/**订单报价，报价后要发送短信通知发单方
	 * */
	@RequestMapping("quote/{indentNum}/{quote}")
	@ResponseBody
	public ReturnValueVo quote(@PathVariable("indentNum") long indentNum,@PathVariable("quote") double quote, HttpSession session){
		//检查是否登录
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser == null){
			return  new ReturnValueVo(ReturnValueVo.ERROR, "nologin");
		}
		//检查是否是企业用户，非企业用户不许报价
		int roleId = basicUser.getRoleId();
		if(roleId == 1){
			return  new ReturnValueVo(ReturnValueVo.ERROR, "noEnterprise");
		}
		//获取企业用户id
		int enterpriseId = enterpriseBiz.getIdByUserId(basicUser.getId());
		return biz.quote(indentNum, quote, enterpriseId);
	}
	
	/**获取报价工厂信息
	 * @retrun 工厂Id、工厂名称、联系人、手机号码、报价金额、报价日期、员工人数
	 * */
	@RequestMapping("getQuoteEnterprise/{indentNum}")
	@ResponseBody
	public List<QuoteEnterpriseVo> getQuoteEnterprise(@PathVariable("indentNum") long indentNum){
		return biz.getQuoteEnterprise(indentNum);
	}
}
