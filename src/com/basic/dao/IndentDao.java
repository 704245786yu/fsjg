package com.basic.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.basic.dto.IndentDto;
import com.basic.po.Indent;
import com.basic.vo.ConfirmIndentVo;
import com.basic.vo.IndentVo;
import com.basic.vo.MyQuotedVo;
import com.common.BaseDao;
import com.common.dto.BootTablePageDto;

@Repository
public class IndentDao extends BaseDao<Integer, Indent>{

	/**页面顶部的全局搜索：搜索订单
	 * @param keyword 模糊匹配 订单名称、订单说明、详细说明
	 * @param costumeCategoryCodes 服饰类型编码数组
	 * @return id、订单名称、预计订单数量、预计交货日期、销售市场、订单类型、接单省、城市、接单企业省、市、接单要求、发单企业、发布日期、有效日期
	 * */
	/*public BootTablePageDto<IndentDto> search1(String processType,String keyword,List<Integer> costumeCategoryCodes){
		StringBuffer subSql = new StringBuffer(" from process_indent pi,(select user_id,1 as userType,province,city from basic_person ")
			.append(" union select user_id,2 as userType,province,city from basic_enterprise ")
			.append(") as user where pi.create_by = user.user_id and (indent_name like :keyword or description like :keyword)");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		
		//服饰类型
		StringBuffer subCostumeCode = new StringBuffer();
		if(costumeCategoryCodes.size() != 0){
			subCostumeCode.append("costume_code like '%"+costumeCategoryCodes.get(0)+"%'");
			for(int i=1; i<costumeCategoryCodes.size(); i++)
				subCostumeCode.append(" or costume_code like '%"+costumeCategoryCodes.get(i)+"%'");
		}
		if(subCostumeCode.length() != 0){
			subSql.append(" and ("+subCostumeCode.toString()+")");
		}
		
		//关键字匹配订单名称、订单说明
		params.add("keyword");
		values.add("%"+keyword+"%");
		
		//加工类型
		if(processType != null){
			subSql.append(" and process_type =:processType");
			params.add("processType");
			values.add(processType);
		}
		
		StringBuffer countSql = new StringBuffer("select count(1)");
		countSql.append(subSql);
		BigInteger bigInt = (BigInteger)super.findByNativeSql(countSql.toString(), params, values).get(0);
		long total = bigInt.longValue();
		
//		订单编号、订单名称、预计订单数量、预计交货日期、订单类型、发单省、城市、接单企业省、市、接单要求、发单企业、发布日期、有效日期
		StringBuffer sql = new StringBuffer("select indent_num as indentNum, indent_name as indentName, process_type as processType,quantity, sale_market as saleMarket,pre_delivery_date as preDeliveryDate, is_urgency as isUrgency,indent_type as indentType,"
				+ "userType,province,city,cond_province as condProvince,cond_city as condCity, cond_demand as condDemand, create_time as createTime,effective_date as effectiveDate ");
		sql.append(subSql);
		System.out.println(sql);
		List<Object[]> scalars = new ArrayList<Object[]>();
		scalars.add(new Object[]{"indentNum",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"indentName",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"processType",StandardBasicTypes.BYTE});
		scalars.add(new Object[]{"quantity",StandardBasicTypes.INTEGER});
		scalars.add(new Object[]{"saleMarket",StandardBasicTypes.BYTE});
		scalars.add(new Object[]{"preDeliveryDate",StandardBasicTypes.DATE});
		scalars.add(new Object[]{"isUrgency",StandardBasicTypes.BOOLEAN});
		scalars.add(new Object[]{"indentType",StandardBasicTypes.BYTE});
		scalars.add(new Object[]{"userType",StandardBasicTypes.BYTE});
		scalars.add(new Object[]{"userType",StandardBasicTypes.BYTE});
		scalars.add(new Object[]{"province",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"city",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"condProvince",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"condCity",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"condDemand",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"createTime",StandardBasicTypes.DATE});
		scalars.add(new Object[]{"effectiveDate",StandardBasicTypes.DATE});
		List<IndentDto> indents = super.findByNativeSql(sql.toString(), params, values, scalars, 0, 20, IndentDto.class);
		return new BootTablePageDto<IndentDto>(total, indents);
	}*/
	
	/**@param province..town 接单用户的省市区县乡镇编码
	 * @param costumeCode[] 服饰类型编码数组
	 * @param processType 加工类型
	 * @param keyword 模糊匹配 订单名称、订单说明【、详细说明】
	 * @return id、订单名称、预计订单数量、预计交货日期、销售市场、订单类型、接单省、城市、接单企业省、市、接单要求、发单企业、发布日期、有效日期
	 * */
	public BootTablePageDto<IndentDto> search(Long province,Long city,Long county,Long town, 
			Integer[] costumeCodes,String processType,Byte saleMarket,String keyword,int offset,int limit,Long total){
		StringBuffer subSql = new StringBuffer(" from process_indent pi,(select user_id,1 as userType,province,city from basic_person ")
			.append(" union select user_id,2 as userType,province,city from basic_enterprise ")
			.append(") as user where pi.create_by = user.user_id and (indent_name like :keyword or description like :keyword)");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		//关键字匹配订单名称、订单说明
		params.add("keyword");
		values.add("%"+keyword+"%");
		
		//接单用户的省市区县乡镇
		if(province != null){
			subSql.append(" and cond_province =:province");
			params.add("province");
			values.add(province);
		}
		if(city != null){
			subSql.append(" and cond_city =:city");
			params.add("city");
			values.add(city);
		}
		if(county != null){
			subSql.append(" and cond_county =:county");
			params.add("county");
			values.add(county);
		}
		if(town != null){
			subSql.append(" and cond_town =:town");
			params.add("town");
			values.add(town);
		}
		
		//服饰类型
		if(costumeCodes != null && costumeCodes.length>0){
			StringBuffer subCostumeCode = new StringBuffer(" costume_code like '%"+costumeCodes[0]+"%'");
			for(int i=1; i<costumeCodes.length; i++)
				subCostumeCode.append(" or costume_code like '%"+costumeCodes[i]+"%'");
			subSql.append(" and ("+subCostumeCode.toString()+")");
		}
		
		//加工类型
		if(processType != null){
			subSql.append(" and process_type =:processType");
			params.add("processType");
			values.add(processType);
		}
		
		//销售市场
		if(saleMarket != null){
			subSql.append(" and sale_market =:saleMarket");
			params.add("saleMarket");
			values.add(saleMarket);
		}
		
		//若有total表示翻页操作，无须再次查询total
		if(total == null){
			StringBuffer countSql = new StringBuffer("select count(1)");
			countSql.append(subSql);
			BigInteger bigInt = (BigInteger)super.findByNativeSql(countSql.toString(), params, values).get(0);
			total = bigInt.longValue();
		}
		
//		订单编号、订单名称、预计订单数量、预计交货日期、订单类型、发单省、城市、接单企业省、市、接单要求、发单企业、发布日期、有效日期
		StringBuffer sql = new StringBuffer("select indent_num as indentNum, indent_name as indentName, process_type as processType,quantity, sale_market as saleMarket,pre_delivery_date as preDeliveryDate, is_urgency as isUrgency,indent_type as indentType,"
				+ "userType,province,city,cond_province as condProvince,cond_city as condCity, cond_demand as condDemand, create_time as createTime,effective_date as effectiveDate ");
		sql.append(subSql);
		List<Object[]> scalars = new ArrayList<Object[]>();
		scalars.add(new Object[]{"indentNum",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"indentName",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"processType",StandardBasicTypes.BYTE});
		scalars.add(new Object[]{"quantity",StandardBasicTypes.INTEGER});
		scalars.add(new Object[]{"saleMarket",StandardBasicTypes.BYTE});
		scalars.add(new Object[]{"preDeliveryDate",StandardBasicTypes.DATE});
		scalars.add(new Object[]{"isUrgency",StandardBasicTypes.BOOLEAN});
		scalars.add(new Object[]{"indentType",StandardBasicTypes.BYTE});
		scalars.add(new Object[]{"userType",StandardBasicTypes.BYTE});
		scalars.add(new Object[]{"userType",StandardBasicTypes.BYTE});
		scalars.add(new Object[]{"province",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"city",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"condProvince",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"condCity",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"condDemand",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"createTime",StandardBasicTypes.DATE});
		scalars.add(new Object[]{"effectiveDate",StandardBasicTypes.DATE});
		List<IndentDto> indents = super.findByNativeSql(sql.toString(), params, values, scalars, offset, limit, IndentDto.class);
		return new BootTablePageDto<IndentDto>(total, indents);
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
	 * @return 订单编号、订单名称、订单数量、订单金额、订单状态、发布日期，按发布日期倒序
	 * */
	@SuppressWarnings("unchecked")
	public BootTablePageDto<Indent> getMyReleased(Long indentNum, String indentName, Byte state, Date beginDate, Date endDate,
			int createBy, Long total, int offset, int limit){
		StringBuffer hql = new StringBuffer(" from Indent where 1=1 ");
		List<String> paramNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		if(indentNum != null){
			hql.append(" and indentNum =:indentNum");
			paramNames.add("indentNum");
			values.add(indentNum);
		}
		if(indentName.length() != 0){
			hql.append(" and indentName like :indentName");
			paramNames.add("indentName");
			values.add("%"+indentName+"%");
		}
		if(state != null){
			hql.append(" and state =:state");
			paramNames.add("state");
			values.add(state);
		}
		if(beginDate != null && endDate != null){
			hql.append(" and createTime between :beginDate and :endDate");
			paramNames.add("beginDate");
			paramNames.add("endDate");
			values.add(beginDate);
			values.add(endDate);
		}
		hql.append(" and createBy =:createBy");
		paramNames.add("createBy");
		values.add(createBy);
		String[] paramNameAry = paramNames.toArray(new String[paramNames.size()]);
		if(total == null){
			total = super.getCount("select count(1) "+hql.toString(), paramNameAry, values.toArray());
			if(total == 0)
				return new BootTablePageDto<Indent>(total, null);
		}
		List<Indent> list = (List<Indent>)super.findByPage("select new Indent(indentNum, indentName, quantity, expectPrice, state, createTime)"+hql.toString(), offset, limit, paramNameAry, values.toArray());
		return new BootTablePageDto<Indent>(total, list);
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
	 * @return 订单编号、订单名称、订单数量、订单金额、订单状态、报价金额，报价日期
	 * */
	@SuppressWarnings("unchecked")
	public BootTablePageDto<MyQuotedVo> getMyQuoted(Long indentNum, String indentName, Byte state, Date beginDate, Date endDate,
			int enterpriseId, Long total, int offset, int limit){
		StringBuffer hql = new StringBuffer(" from Indent i,IndentQuote iq where i.indentNum = iq.indentNum and iq.enterpriseId =:enterpriseId ");
		List<String> paramNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		paramNames.add("enterpriseId");
		values.add(enterpriseId);
		if(indentNum != null){
			hql.append(" and indentNum =:indentNum");
			paramNames.add("indentNum");
			values.add(indentNum);
		}
		if(indentName.length() != 0){
			hql.append(" and indentName like :indentName");
			paramNames.add("indentName");
			values.add("%"+indentName+"%");
		}
		if(state != null){
			hql.append(" and state =:state");
			paramNames.add("state");
			values.add(state);
		}
		//报价日期
		if(beginDate != null && endDate != null){
			hql.append(" and iq.createTime between :beginDate and :endDate");
			paramNames.add("beginDate");
			paramNames.add("endDate");
			values.add(beginDate);
			values.add(endDate);
		}
		String[] paramNameAry = paramNames.toArray(new String[paramNames.size()]);
		if(total == null){
			total = super.getCount("select count(1) "+hql.toString(), paramNameAry, values.toArray());
			if(total == 0)
				return new BootTablePageDto<MyQuotedVo>(total, new ArrayList<MyQuotedVo>());
		}
		List<MyQuotedVo> list = super.findByPage("select i.indentNum as indentNum, i.indentName as indentName, i.quantity as quantity, i.expectPrice as expectPrice, i.state as state, iq.quote as quote, iq.createTime as createTime"+hql.toString(), offset, limit, paramNameAry, values.toArray(),MyQuotedVo.class);
		//获取每个订单的报价人数
		List<Long> indentNums = new ArrayList<Long>(list.size());
		for(int i=0;i<list.size();i++){
			indentNums.add(list.get(i).getIndentNum());
		}
		hql = new StringBuffer("select count(1) from IndentQuote where indentNum in (:indentNums) group by indentNum");
		List<Long> counts = (List<Long>)super.find(hql.toString(), new String[]{"indentNums"}, new Object[]{indentNums});
		for(int i=0; i<counts.size(); i++){
			MyQuotedVo vo = list.get(i);
			vo.setCountNum(counts.get(i));
		}
		return new BootTablePageDto<MyQuotedVo>(total, list);
	}
	
	/**我收到的报价，返回收到报价的订单，但不包含已接单和已失效的订单
	 * @param indentNum 订单编号
	 * @param indentName 订单名称,模糊匹配
	 * @param beginDate 报价-开始日期
	 * @param endDate 报价-结束日期
	 * @param total
	 * @param offset
	 * @param limit
	 * @return 订单ID、订单编号、订单名称、订单数量、订单金额、报价人数、最新报价日期
	 */
	public BootTablePageDto<IndentVo> myReceivedQuote(Long indentNum, String indentName, Date beginDate, Date endDate,
			int createBy, Long total, int offset, int limit){
		List<String> paramNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from Indent i, IndentQuote q where i.indentNum = q.indentNum and i.createBy =:createBy and state=1");
		paramNames.add("createBy");
		values.add(createBy);
		
		if(indentNum != null){
			hql.append(" and i.indentNum =:indentNum");
			paramNames.add("indentNum");
			values.add(indentNum);
		}
		if(indentName.length() != 0){
			hql.append(" and i.indentName like :indentName");
			paramNames.add("indentName");
			values.add("%"+indentName+"%");
		}
		if(beginDate != null && endDate != null){
			hql.append(" and i.createTime between :beginDate and :endDate");
			paramNames.add("beginDate");
			paramNames.add("endDate");
			values.add(beginDate);
			values.add(endDate);
		}
		
		String[] paramNameAry = paramNames.toArray(new String[paramNames.size()]);
		if(total == null){
			total = super.getCount("select count(distinct i.id) "+hql.toString(), paramNameAry, values.toArray());
			if(total == 0)
				return new BootTablePageDto<IndentVo>(total, null);
		}
		List<IndentVo> list = super.findByPage(
				"select i.id as id, i.indentNum as indentNum, i.indentName as indentName, i.quantity as quantity, i.expectPrice as expectPrice, count(i.id) as countNum, max(q.createTime) as latestTime"
				+hql.toString()+" group by i.id", offset, limit, paramNameAry, values.toArray(),IndentVo.class);
		return new BootTablePageDto<IndentVo>(total, list);
	}
	
	/**我收到的订单
	 * @param indentNum 订单编号
	 * @param indentName 订单名称,模糊匹配
	 * @param beginDate 报价-开始日期
	 * @param endDate 报价-结束日期
	 * @param total
	 * @param offset
	 * @param limit
	 * @return 订单编号、订单名称、订单数量、订单金额、报价金额、报价日期
	 */
	public BootTablePageDto<MyQuotedVo> myReceivedIndent(Long indentNum, String indentName, Date beginDate, Date endDate,
			int enterpriseId, Long total, int offset, int limit){
		StringBuffer hql = new StringBuffer(" from Indent i,IndentQuote iq where i.indentNum = iq.indentNum and i.receivedEnterpriseId =:enterpriseId and iq.enterpriseId =:enterpriseId");
		List<String> paramNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		paramNames.add("enterpriseId");
		values.add(enterpriseId);
		if(indentNum != null){
			hql.append(" and indentNum =:indentNum");
			paramNames.add("indentNum");
			values.add(indentNum);
		}
		if(indentName.length() != 0){
			hql.append(" and indentName like :indentName");
			paramNames.add("indentName");
			values.add("%"+indentName+"%");
		}
		//报价日期
		if(beginDate != null && endDate != null){
			hql.append(" and iq.createTime between :beginDate and :endDate");
			paramNames.add("beginDate");
			paramNames.add("endDate");
			values.add(beginDate);
			values.add(endDate);
		}
		String[] paramNameAry = paramNames.toArray(new String[paramNames.size()]);
		if(total == null){
			total = super.getCount("select count(1) "+hql.toString(), paramNameAry, values.toArray());
			if(total == 0)
				return new BootTablePageDto<MyQuotedVo>(total, new ArrayList<MyQuotedVo>());
		}
		List<MyQuotedVo> list = super.findByPage("select i.indentNum as indentNum, i.indentName as indentName, i.quantity as quantity, i.expectPrice as expectPrice, iq.quote as quote, iq.createTime as createTime"+hql.toString(), offset, limit, paramNameAry, values.toArray(),MyQuotedVo.class);
		return new BootTablePageDto<MyQuotedVo>(total, list);
	}

	/**更新订单状态
	 * */
	public void updateState(long indentNum, byte state){
		String hql = "update Indent set state =:state where indentNum =:indentNum";
		super.executeUpdate(hql, new String[]{"indentNum","state"}, new Object[]{indentNum,state});
	}
	
	/**确认订单，并修改订单状态为已接单*/
	public void confirm(long indentNum, int enterpriseId, double price, int createBy){
		String hql = "update Indent set state = 2, receivedEnterpriseId =:enterpriseId, price =:price where indentNum =:indentNum and createBy =:createBy";
		super.executeUpdate(hql, new String[]{"enterpriseId","price","indentNum","createBy"}, new Object[]{enterpriseId, price, indentNum, createBy});
	}
	
	/**我确认的订单*/
	public BootTablePageDto<ConfirmIndentVo> myConfirmed(Long indentNum, String indentName, Date beginDate, Date endDate,
			int createBy, Long total, int offset, int limit){
		List<String> paramNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" where i.createBy =:createBy and state=2");
		paramNames.add("createBy");
		values.add(createBy);
		
		if(indentNum != null){
			hql.append(" and i.indentNum =:indentNum");
			paramNames.add("indentNum");
			values.add(indentNum);
		}
		if(indentName.length() != 0){
			hql.append(" and i.indentName like :indentName");
			paramNames.add("indentName");
			values.add("%"+indentName+"%");
		}
		if(beginDate != null && endDate != null){
			hql.append(" and i.createTime between :beginDate and :endDate");
			paramNames.add("beginDate");
			paramNames.add("endDate");
			values.add(beginDate);
			values.add(endDate);
		}
		
		String[] paramNameAry = paramNames.toArray(new String[paramNames.size()]);
		if(total == null){
			total = super.getCount("select count(1) from Indent i"+hql.toString(), paramNameAry, values.toArray());
			if(total == 0)
				return new BootTablePageDto<ConfirmIndentVo>(total, null);
		}
		List<ConfirmIndentVo> list = super.findByPage(
				"select i.indentNum as indentNum, i.indentName as indentName, i.expectPrice as expectPrice, i.price as price, e.enterpriseName as enterpriseName, i.updateTime as updateTime from Indent i, Enterprise e"
				+hql.toString()+" and i.receivedEnterpriseId = e.id", offset, limit, paramNameAry, values.toArray(),ConfirmIndentVo.class);
		return new BootTablePageDto<ConfirmIndentVo>(total, list);
	}
	
	/**获取订单的名称和发单人的手机号码
	 * @prama indentNum 订单编号
	 * @return String[] indentName,telephone
	 * */
	@SuppressWarnings("unchecked")
	public Indent getNameAndTele(long indentNum){
		String hql = "select new Indent(indentName, telephone) from Indent where indentNum =:indentNum";
		List<Indent> list = (List<Indent>)super.find(hql, new String[]{"indentNum"}, new Long[]{indentNum});
		return list.get(0);
	}
	
	/**后台分页查询*/
	@SuppressWarnings("unchecked")
	public BootTablePageDto<Indent> findByPage(Long indentNum,Byte state,Date beginDate,Date endDate, int offset, int limit, Long total){
		StringBuffer hql = new StringBuffer("from Indent where 1=1 ");
		List<String> paramNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		if(indentNum != null){
			hql.append(" and indentNum=:indentNum");
			paramNames.add("indentNum");
			values.add(indentNum);
		}
		if(state != null){
			hql.append(" and state=:state");
			paramNames.add("state");
			values.add(state);
		}
		if(beginDate != null){
			hql.append(" and createTime between :beginDate and :endDate");
			paramNames.add("beginDate");
			values.add(beginDate);
			paramNames.add("endDate");
			values.add(endDate);
		}
		if(total == null){
			total = super.getCount("select count(1) "+hql.toString(), paramNames.toArray(new String[]{}), values.toArray(new Object[]{}));
			if(total == 0)
				return new BootTablePageDto<Indent>(total, null);
		}
		List<Indent> list = (List<Indent>)super.findByPage(hql.toString(), offset, limit, paramNames.toArray(new String[]{}), values.toArray(new Object[]{}));
		return new BootTablePageDto<Indent>(total, list);
	}
}
