package com.basic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.basic.po.IndentQuote;
import com.basic.vo.QuoteEnterpriseVo;
import com.common.BaseDao;

@Repository
public class IndentQuoteDao extends BaseDao<Integer, IndentQuote>{

	/**获取报价工厂信息
	 * @retrun 工厂Id、工厂名称、联系人、手机号码、报价金额、报价日期、员工人数
	 * */
	public List<QuoteEnterpriseVo> getQuoteEnterprise(long indentNum){
		String hql = "select e.id as id, e.enterpriseName as enterpriseName, e.linkman as linkman, e.basicUser.telephone as telephone, i.quote as quote, i.createTime as quoteDate, e.staffNumber as staffNumber from Enterprise e, IndentQuote i where i.indentNum =:indentNum and e.id = i.enterpriseId)";
		return super.find(hql, new String[]{"indentNum"}, new Long[]{indentNum},QuoteEnterpriseVo.class);
	}
	
	public void delByIndentNum(long indentNum){
		String hql = "delete from IndentQuote where indentNum =:indentNum";
		super.executeUpdate(hql, new String[]{"indentNum"}, new Long[]{indentNum});
	}
}
