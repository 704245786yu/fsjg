package com.basic.biz;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basic.dao.EnterpriseDao;
import com.basic.dao.IndentDao;
import com.basic.dao.IndentQuoteDao;
import com.basic.po.Indent;
import com.basic.po.IndentQuote;
import com.basic.vo.QuoteEnterpriseVo;
import com.common.BaseBiz;
import com.common.vo.ReturnValueVo;
import com.util.SMS;

@Service
public class IndentQuoteBiz extends BaseBiz<IndentQuoteDao, Integer, IndentQuote> {

	@Autowired
	private IndentDao indentDao;
	@Autowired
	private EnterpriseDao enterpriseDao;
	
	/**订单报价
	 * 需检测企业是否已经报价，企业不能重复报价，更新订单状态为已收到报价。
	 * 报价后要发送短信通知发单方
	 * */
	@SuppressWarnings("unchecked")
	@Transactional
	public ReturnValueVo quote(long indentNum,double quote,int enterpriseId){
		IndentQuote indentQuote = new IndentQuote();
		indentQuote.setIndentNum(indentNum);
		indentQuote.setEnterpriseId(enterpriseId);
		List<IndentQuote> list = dao.findByExample(indentQuote);
		//已报价
		if(list.size() != 0){
			return new ReturnValueVo(ReturnValueVo.ERROR, "quoted");
		}
		indentQuote.setQuote(quote);
		dao.save(indentQuote);
		indentDao.updateState(indentNum, (byte)1);
		//发送通知短信
		Indent indent = indentDao.getNameAndTele(indentNum);
		String enterpriseName = enterpriseDao.getName(enterpriseId);
		HashMap<String,LinkedHashMap<String,Object>> map = SMS.sendQuoteNotice(indent.getIndentName(), enterpriseName, indent.getTelephone());
		LinkedHashMap<String,Object> map2 = (LinkedHashMap<String,Object>)map.get("alibaba_aliqin_fc_sms_num_send_response").get("result");
		if(!(boolean)map2.get("success")){
			System.out.println("订单报价-发送验证码错误："+map);
		}
		return new ReturnValueVo(ReturnValueVo.SUCCESS, null);
	}
	
	/**获取当前的报价数
	 * */
	public long getQuoteNum(long indentNum){
		String hql = "select count(1) from IndentQuote where indentNum =:indentNum";
		return dao.getCount(hql, new String[]{"indentNum"}, new Long[]{indentNum});
	}
	
	/**获取报价工厂信息
	 * @retrun 工厂Id、工厂名称、联系人、手机号码、报价金额、报价日期、员工人数
	 * */
	public List<QuoteEnterpriseVo> getQuoteEnterprise(long indentNum){
		return dao.getQuoteEnterprise(indentNum);
	}
}
