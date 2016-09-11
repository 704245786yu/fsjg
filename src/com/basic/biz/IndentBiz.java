package com.basic.biz;

import java.util.ArrayList;
import java.util.Date;
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
import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;
import com.sys.biz.ConstantDictBiz;
import com.sys.po.ConstantDict;
import com.util.DateTransform;

@Service
public class IndentBiz extends BaseBiz<IndentDao, Integer, Indent> {

	@Autowired
	private ConstantDictBiz constantDictBiz;
	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	
	/**页面顶部的全局搜索：搜索订单
	 * 模糊匹配 订单名称、服饰类型、加工类型、订单说明、详细说明
	 * */
	public BootTablePageDto<IndentDto> search(String keyword){
		//为简化查询，不匹配多个加工类型
		String processType = null;
		if(keyword.length() > 0){
			List<ConstantDict> processTypes = constantDictBiz.getByCodeAndConstantName("process_type", keyword);
			if(processTypes.size() != 0){
				processType = processTypes.get(0).getConstantValue();
			}
		}
		List<Integer> costumeCategoryCodes = new ArrayList<Integer>();
		int endIndex = 0;
		if(keyword.length() > 0){
			costumeCategoryCodes = costumeCategoryBiz.getCodeByCategoryName(keyword);
			//为保证性能，取前3条服饰类型记录
			endIndex = costumeCategoryCodes.size()>3 ? 3 : costumeCategoryCodes.size();
		}
		BootTablePageDto<IndentDto> result = dao.search(processType,keyword,costumeCategoryCodes.subList(0, endIndex));
		return result;
	}
	
	/**@param province..town 发单用户的省市区县乡镇编码
	 * @param costumeCode[] 服饰类型编码数组 
	 * @param keyword 模糊订单名称、订单说明、加工类型
	 * @return id、订单名称、预计订单数量、预计交货日期、销售市场、订单类型、接单省、城市、接单企业省、市、接单要求、发单企业、发布日期、有效日期
	 * */
	public BootTablePageDto<IndentDto> search2(Long province,Long city,Long county,Long town, Integer[] costumeCode, String keyword){
		//为简化查询，不匹配多个加工类型
		String processType = null;
		if(keyword.length() > 0){
			List<ConstantDict> processTypes = constantDictBiz.getByCodeAndConstantName("process_type", keyword);
			if(processTypes.size() != 0){
				processType = processTypes.get(0).getConstantValue();
			}
		}
		BootTablePageDto<IndentDto> result = dao.search2(province,city,county,town,costumeCode,processType,keyword);
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
	
	/**个人中心-我收到报价的订单*/
	public BootTablePageDto<IndentVo> myReceivedQuote(Long indentNum, String indentName, String beginDate, String endDate,
			int createBy, Long total, int offset, int limit){
		Date beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
		Date endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		return dao.myReceivedQuote(indentNum, indentName, beginTime, endTime, createBy, total, offset, limit);
	}
	
	/**确认订单*/
	public void confirm(long indentNum,int enterpriseId,double price, int createBy){
		dao.confirm(indentNum, enterpriseId, price, createBy);
	}
	
	/**我确认的订单*/
	public BootTablePageDto<ConfirmIndentVo> myConfirmed(Long indentNum, String indentName, String beginDate, String endDate,
			int createBy, Long total, int offset, int limit){
		Date beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
		Date endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		return dao.myConfirmed(indentNum, indentName, beginTime, endTime, createBy, total, offset, limit);
	}
}
