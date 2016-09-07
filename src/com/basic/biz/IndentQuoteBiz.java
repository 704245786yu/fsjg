package com.basic.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basic.dao.IndentDao;
import com.basic.dao.IndentQuoteDao;
import com.basic.po.IndentQuote;
import com.basic.vo.QuoteEnterpriseVo;
import com.common.BaseBiz;
import com.common.vo.ReturnValueVo;

@Service
public class IndentQuoteBiz extends BaseBiz<IndentQuoteDao, Integer, IndentQuote> {

	@Autowired
	private IndentDao indentDao;
	
	/**订单报价
	 * 需检测企业是否已经报价，企业不能重复报价，更新订单状态为已收到报价。
	 * */
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
		return new ReturnValueVo(ReturnValueVo.SUCCESS, null);
	}
	
	/**获取当前的报价数
	 * */
	public long getQuoteNum(int indentId){
		String hql = "select count(1) from IndentQuote where indentId =:indentId";
		return dao.getCount(hql, new String[]{"indentId"}, new Integer[]{indentId});
	}
	
	/**获取报价工厂信息
	 * @retrun 工厂Id、工厂名称、联系人、手机号码、报价金额、报价日期、员工人数
	 * */
	public List<QuoteEnterpriseVo> getQuoteEnterprise(long indentNum){
		return dao.getQuoteEnterprise(indentNum);
	}
}
