package com.basic.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.basic.dto.IndentDto;
import com.basic.po.Indent;
import com.common.BaseDao;
import com.common.dto.BootTablePageDto;

@Repository
public class IndentDao extends BaseDao<Integer, Indent>{

	/**@param province..town 发单用户的省市区县乡镇编码
	 * @param costumeCode[] 服饰类型编码数组
	 * @param processType 加工类型
	 * @param keyword 模糊订单名称、订单说明
	 * @return id、订单名称、预计订单数量、预计交货日期、销售市场、订单类型、接单省、城市、接单企业省、市、接单要求、发单企业、发布日期、有效日期
	 * */
	@SuppressWarnings("unchecked")
	public BootTablePageDto<IndentDto> search2(Long province,Long city,Long county,Long town, Integer[] costumeCode,String processType,String keyword){
		StringBuffer userQuerySql = new StringBuffer(" where 1=1 ");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		//发单用户的省市区县乡镇
		if(province != null){
			userQuerySql.append(" and province =:province");
			params.add("province");
			values.add(province);
		}
		if(city != null){
			userQuerySql.append(" and city =:city");
			params.add("city");
			values.add(city);
		}
		if(county != null){
			userQuerySql.append(" and county =:county");
			params.add("county");
			values.add(county);
		}
		if(town != null){
			userQuerySql.append(" and town =:town");
			params.add("town");
			values.add(town);
		}
		//判断是否要加入发单地区的筛选
		StringBuffer subSql = new StringBuffer();
		StringBuffer subCostumeCode = new StringBuffer();
		if(costumeCode != null){
			subCostumeCode.append("costume_code like '%"+costumeCode[0]+"%'");
			for(int i=1; i<costumeCode.length; i++)
				subCostumeCode.append(" or costume_code like '%"+costumeCode[i]+"%'");
		}
		
		subSql.append(" from process_indent pi,(select user_id,1 as userType,province,city from basic_person ")
			.append(userQuerySql)
			.append(" union select user_id,2 as userType,province,city from basic_enterprise ")
			.append(userQuerySql)
			.append(") as user where pi.create_by = user.user_id and (indent_name like :keyword or description like :keyword)");
		
		//服饰类型
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
		StringBuffer sql = new StringBuffer("select indent_num as indentNum, indent_name as indentName, process_type as processType,quantity, pre_delivery_date as preDeliveryDate, is_urgency as isUrgency,indent_type as indentType,"
				+ "userType,province,city,cond_province as condProvince,cond_city as condCity, cond_demand as condDemand, create_time as createTime,effective_date as effectiveDate ");
		sql.append(subSql);
		System.out.println(sql);
		List<Object[]> scalars = new ArrayList<Object[]>();
		scalars.add(new Object[]{"indentNum",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"indentName",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"processType",StandardBasicTypes.BYTE});
		scalars.add(new Object[]{"quantity",StandardBasicTypes.INTEGER});
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
		List<IndentDto> indents = (List<IndentDto>)super.findByNativeSql(sql.toString(), params, values, scalars, 0, 20, IndentDto.class);
		return new BootTablePageDto<IndentDto>(total, indents);
	}
}
