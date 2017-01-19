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
import org.springframework.transaction.annotation.Transactional;

import com.basic.dao.IndentDao;
import com.basic.dao.IndentQuoteDao;
import com.basic.dto.IndentDto;
import com.basic.po.Indent;
import com.basic.vo.ConfirmIndentVo;
import com.basic.vo.IndentDistVo;
import com.basic.vo.IndentVo;
import com.basic.vo.MyQuotedVo;
import com.basic.vo.NewstQuoteIndentVo;
import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;
import com.sys.biz.ConstantDictBiz;
import com.sys.po.ConstantDict;
import com.util.DateTransform;
import com.util.SMS;

@Service
public class IndentBiz extends BaseBiz<IndentDao, Integer, Indent> {

	@Autowired
	private PersonBiz personBiz;
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private ConstantDictBiz constantDictBiz;
	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	@Autowired
	private IndentQuoteDao indentQuoteDao;
	
	/**@param province..town 接单用户的省市区县乡镇编码
	 * @param costumeCode[] 服饰类型编码数组 
	 * @param keyword 模糊匹配 订单名称、订单说明、详细说明、加工类型、服饰类型
 	 * @param sortMark 排序标志,所有都按从大到小
	 * @param isUrgency 只看急单
	 * @param offset
	 * @param limit
	 * @return id、订单名称、预计订单数量、预计交货日期、销售市场、订单类型、接单省、城市、接单企业省、市、接单要求、发单企业、发布日期、有效日期
	 * */
	public BootTablePageDto<IndentDto> search(Long province,Long city,Long county,Long town, Integer[] costumeCodes, 
			Integer processType, Byte saleMarket,String keyword,Byte sortMark, Byte userType, Boolean isUrgency,int offset,int limit,Long total){
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
		
		BootTablePageDto<IndentDto> result = dao.search(province,city,county,town,costumeCodes,processTypeStr,saleMarket,keyword,sortMark,userType,isUrgency,offset,limit,total);
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
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length() >0 && endDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");			
		}
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
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length() >0 && endDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");			
		}
		return dao.getMyQuoted(indentNum, indentName, state, beginTime, endTime, enterpriseId, total, offset, limit);
	}
	
	/**个人中心-我收到报价的订单*/
	public BootTablePageDto<IndentVo> myReceivedQuote(Long indentNum, String indentName, String beginDate, String endDate,
			int createBy, Long total, int offset, int limit){
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length() >0 && endDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");			
		}
		return dao.myReceivedQuote(indentNum, indentName, beginTime, endTime, createBy, total, offset, limit);
	}
	
	/**个人中心-我收到的订单*/
	public BootTablePageDto<MyQuotedVo> myReceivedIndent(Long indentNum, String indentName, String beginDate, String endDate,
			int enterpriseId, Long total, int offset, int limit){
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length() >0 && endDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");			
		}
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
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length() >0 && endDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");			
		}
		return dao.myConfirmed(indentNum, indentName, beginTime, endTime, createBy, total, offset, limit);
	}
	
	/**后台分页查询*/
	public BootTablePageDto<Indent> findByPage(Long indentNum,Byte state,String beginDate,String endDate, int offset, int limit, Long total){
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length() >0 && endDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");			
		}
		return dao.findByPage(indentNum, state, beginTime, endTime, offset, limit, total);
	}
	
	/**根据发单用户类型获取最新的10条记录，订单主页使用
	 * */
	public List<IndentDistVo> getNewstByUserType(byte createUserType){
		String hql = null;
		if(createUserType != 2){
			hql = "select i.indentNum as indentNum, i.indentName as indentName, i.costumeCode as costumeCode, i.processType as processType, i.isUrgency as isUrgency , i.quantity as quantity, i.createTime as createTime, p.province as province, p.city as city from Indent i,Person p where i.createBy = p.basicUser.id and i.createUserType =:createUserType order by i.createTime desc";
		}else if(createUserType == 2){
			hql = "select i.indentNum as indentNum, i.indentName as indentName, i.costumeCode as costumeCode, i.processType as processType, i.isUrgency as isUrgency , i.quantity as quantity, i.createTime as createTime, e.province as province, e.city as city from Indent i,Enterprise e where i.createBy = e.basicUser.id and i.createUserType =:createUserType order by i.createTime desc";
		}
		return dao.findByPage(hql, 0, 10, new String[]{"createUserType"}, new Byte[]{createUserType},IndentDistVo.class);
	}
	
	/**取前10条最新报价*/
	@SuppressWarnings("unchecked")
	public List<NewstQuoteIndentVo> getNewstQuote(){
		String hql = "select indentNum,count(indentNum) from IndentQuote group by indentNum order by createTime desc";
		List<Object[]> list1 = (List<Object[]>)dao.findByPage(hql,0,10,new String[]{},new Object[]{});
		if(list1.size() == 0)
			return null;
		ArrayList<Long> indentNums = new ArrayList<Long>();
		for(int i=0; i<list1.size(); i++){
			indentNums.add((Long)list1.get(i)[0]);
		}
		hql = "select indentNum as indentNum,indentName as indentName,quantity as quantity,processType as processType from Indent where indentNum in (:indentNums)";
		List<NewstQuoteIndentVo> list = dao.find(hql, new String[]{"indentNums"}, new Object[]{indentNums}, NewstQuoteIndentVo.class);
		for(int i=0; i<list1.size(); i++)
			list.get(i).setCountNum((Long)list1.get(i)[1]);
		return list;
	}

	/**删除订单*/
	@Transactional
	public Integer delByIndentNum(long indentNum) {
		indentQuoteDao.delByIndentNum(indentNum);;
		dao.delByIndentNum(indentNum);
		return 200;
	}
	
	/**首页最新订单
	 * @return 订单名称、数量、地区
	 * */
	@SuppressWarnings("unchecked")
	public List<Indent> getHomeList(HashMap<Long,String> districtCodeNameMap){
		String hql = "select new Indent(indentNum,indentName,quantity,isUrgency,createBy,createUserType) from Indent order by createTime desc";
		List<Indent> indents = (List<Indent>)dao.findByPage(hql, 0, 8, new String[]{}, null);
		for(int i=0; i<indents.size(); i++){
			Indent indent = indents.get(i);
			Object[] districts;
			if(indent.getCreateUserType()==2){
				districts = enterpriseBiz.getDistrict(indent.getCreateBy());
			}else{
				districts = personBiz.getDistrict(indent.getCreateBy());
			}
//			String district = "";
//			if(districts.length==1)
//				district = districtCodeNameMap.get(districts[0]);
//			else if(districts.length==2)
				String district = districtCodeNameMap.get(districts[0])+districtCodeNameMap.get(districts[1]);
			indent.setDistrict(district);
		}
		return indents;
	}
}
