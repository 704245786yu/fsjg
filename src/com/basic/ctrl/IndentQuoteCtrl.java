package com.basic.ctrl;

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
import com.common.BaseCtrl;
import com.common.vo.ReturnValueVo;

@Controller
@RequestMapping("indentQuote")
public class IndentQuoteCtrl extends BaseCtrl<IndentQuoteBiz,Integer,IndentQuote>{
	
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	
	/**订单报价
	 * */
	@RequestMapping("quote/{indentId}/{quote}")
	@ResponseBody
	public ReturnValueVo quote(@PathVariable("indentId") int indentId,@PathVariable("quote") double quote, HttpSession session){
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
		return biz.quote(indentId, quote, enterpriseId);
	}
}
