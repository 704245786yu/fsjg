package com.basic.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.basic.dao.IndentQuoteDao;
import com.basic.po.IndentQuote;
import com.common.BaseBiz;
import com.common.vo.ReturnValueVo;

@Service
public class IndentQuoteBiz extends BaseBiz<IndentQuoteDao, Integer, IndentQuote> {

	/**订单报价
	 * 需检测企业是否已经报价，企业不能重复报价
	 * */
	public ReturnValueVo quote(int indentId,double quote,int enterpriseId){
		IndentQuote indentQuote = new IndentQuote();
		indentQuote.setIndentId(indentId);
		indentQuote.setEnterpriseId(enterpriseId);
		List<IndentQuote> list = dao.findByExample(indentQuote);
		//已报价
		if(list.size() != 0){
			return new ReturnValueVo(ReturnValueVo.ERROR, "quoted");
		}
		indentQuote.setQuote(quote);
		dao.save(indentQuote);
		return new ReturnValueVo(ReturnValueVo.SUCCESS, null);
	}
}
