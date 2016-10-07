package com.basic.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.IndentDao;
import com.basic.dto.IndentDto;
import com.basic.po.Indent;
import com.basic.vo.ConfirmIndentVo;
import com.basic.vo.IndentVo;
import com.basic.vo.MyQuotedVo;
import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;
import com.sys.biz.ConstantDictBiz;
import com.sys.po.ConstantDict;
import com.util.DateTransform;
import com.util.SMS;

@Service
public class IndentBiz extends BaseBiz<IndentDao, Integer, Indent> {

	@Autowired
	private ConstantDictBiz constantDictBiz;
	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	
	/**@param province..town 接单用户的省市区县乡镇编码
	 * @param costumeCode[] 服饰类型编码数组 
	 * @param keyword 模糊匹配 订单名称、订单说明、详细说明、加工类型、服饰类型
	 * @param offset
	 * @param limit
	 * @return id、订单名称、预计订单数量、预计交货日期、销售市场、订单类型、接单省、城市、接单企业省、市、接单要求、发单企业、发布日期、有效日期
	 * */
	public BootTablePageDto<IndentDto> search(Long province,Long city,Long county,Long town, Integer[] costumeCodes, 
			Integer processType, Byte saleMarket,String keyword,int offset,int limit,Long total){
		//为简化查询，不匹配多个加工类型
		String processTypeStr = null;//要查询的加工类型编码
		//判断是否需要根据关键字匹配加工类型
		if(processType == null){
			if(keyword.length() > 0){
				//为简化查询，不匹配多个加工类型
				List<ConstantDict> processTypes = constantDictBiz.getByCodeAndConstantName("process_type", keyword);
				if(processTypes.size() != 0){
					processTypeStr = processTypes.get(0).getConstantValue();
				}
			}
		}else{
			processTypeStr = processType.toString();
		}
		
		//判断是否需要根据关键字匹配主营产品
		if(costumeCodes == null || costumeCodes.length == 0){
			List<Integer> costumeCategoryCodes = new ArrayList<Integer>();
			int endIndex = 0;
			if(keyword.length() > 0){
				costumeCategoryCodes = costumeCategoryBiz.getCodeByCategoryName(keyword);
				//为保证性能，取前3条服饰类型记录
				endIndex = costumeCategoryCodes.size()>3 ? 3 : costumeCategoryCodes.size();
			}
			costumeCodes = costumeCategoryCodes.subList(0, endIndex).toArray(new Integer[]{});
		}
		
		BootTablePageDto<IndentDto> result = dao.search(province,city,county,town,costumeCodes,processTypeStr,saleMarket,keyword,offset,limit,total);
		return result;
	}

	/**根据订单编号查询订单*/
	public Indent getByNum(Long indentNum){
		Criterion c = Restrictions.eq("indentNum", indentNum);
		List<Indent> list = dao.findByCriteria(c);
		if(list.size() == 1)
			return list.get(0);
		else
			return null;
	}
	
	/**个人中心-我发布的订单
	 * @param indentNum 订单编号
	 * @param indentName 订单名称,模糊匹配
	 * @param state 订单状态
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @param createBy 用户Id
	 * @param total 总记录数
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * */
	public BootTablePageDto<Indent> getMyReleased(Long indentNum, String indentName, Byte state, String beginDate, String endDate,
			int createBy, Long total, int offset, int limit){
		Date beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
		Date endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		return dao.getMyReleased(indentNum, indentName, state, beginTime, endTime, createBy, total, offset, limit);
	}
	
	/**个人中心-我的报价
	 * @param indentNum 订单编号
	 * @param indentName 订单名称,模糊匹配
	 * @param state 订单状态
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @param enterpriseId 报价企业Id
	 * @param total 总记录数
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * */
	public BootTablePageDto<MyQuotedVo> getMyQuoted(Long indentNum, String indentName, Byte state, String beginDate, String endDate,
			int enterpriseId, Long total, int offset, int limit){
		Date beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
		Date endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		return dao.getMyQuoted(indentNum, indentName, state, beginTime, endTime, enterpriseId, total, offset, limit);
	}
	
	/**个人中心-我收到报价的订单*/
	public BootTablePageDto<IndentVo> myReceivedQuote(Long indentNum, String indentName, String beginDate, String endDate,
			int createBy, Long total, int offset, int limit){
		Date beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
		Date endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		return dao.myReceivedQuote(indentNum, indentName, beginTime, endTime, createBy, total, offset, limit);
	}
	
	/**个人中心-我收到的订单*/
	public BootTablePageDto<MyQuotedVo> myReceivedIndent(Long indentNum, String indentName, String beginDate, String endDate,
			int enterpriseId, Long total, int offset, int limit){
		Date beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
		Date endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		return dao.myReceivedIndent(indentNum, indentName, beginTime, endTime, enterpriseId, total, offset, limit);
	}
	
	/**确认订单,并发送短信通知接单方*/
	@SuppressWarnings("unchecked")
	public void confirm(long indentNum,String indentName,int enterpriseId,long telephone,double price,int createBy){
		dao.confirm(indentNum, enterpriseId, price, createBy);
		HashMap<String,LinkedHashMap<String,Object>> map = SMS.sendConfirmIndentNotice(indentName, telephone);
		LinkedHashMap<String,Object> map2 = (LinkedHashMap<String,Object>)map.get("alibaba_aliqin_fc_sms_num_send_response").get("result");
		if(!(boolean)map2.get("success")){
			System.out.println("确认订单-发送验证码错误："+map);
		}
	}
	
	/**我确认的订单*/
	public BootTablePageDto<ConfirmIndentVo> myConfirmed(Long indentNum, String indentName, String beginDate, String endDate,
			int createBy, Long total, int offset, int limit){
		Date beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
		Date endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		return dao.myConfirmed(indentNum, indentName, beginTime, endTime, createBy, total, offset, limit);
	}
}
