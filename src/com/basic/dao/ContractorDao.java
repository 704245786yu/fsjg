package com.basic.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.basic.po.Contractor;
import com.basic.vo.ContractorVo;
import com.common.BaseDao;
import com.common.dto.BootTablePageDto;

@Repository
public class ContractorDao extends BaseDao<Integer, Contractor>{

	public BootTablePageDto<ContractorVo> findByPage(String userName,Long telephone,Byte auditState,Date beginDate,Date endDate,int offset, int limit, Long total){
		StringBuffer hql = new StringBuffer("from Contractor c, Person p where c.personId = p.id");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		if(userName.length()>0){
			hql.append(" and userName like :userName");
			params.add("userName");
			values.add("%"+userName+"%");
		}
		if(telephone!=null){
			hql.append(" and telephone =:telephone");
			params.add("telephone");
			values.add(telephone);
		}
		if(auditState!=null){
			hql.append(" and auditState =:auditState");
			params.add("auditState");
			values.add(auditState);
		}
		if(beginDate!=null && endDate!=null){
			hql.append(" and basicUser.createTime between :beginDate and :endDate");
			params.add("beginDate");
			values.add(beginDate);
			params.add("endDate");
			values.add(endDate);
		}
		if(total==null){
			StringBuffer countSql = new StringBuffer("select count(1) ");
			countSql.append(hql);
			total = super.getCount(countSql.toString(), params.toArray(new String[]{}), values.toArray(new Object[]{}));
			if(total == 0)
				return new BootTablePageDto<ContractorVo>(total, new ArrayList<ContractorVo>());
		}
		List<ContractorVo> list = super.findByPage(
				"select c.personId as id,c.processType as processType, p.basicUser.userName as userName, p.basicUser.telephone as telephone,"
				+ "p.realName as realName,p.basicUser.state as state, p.auditState as auditState, p.basicUser.createTime as createTime "+hql.toString(),
				offset, limit, params.toArray(new String[]{}), values.toArray(new Object[]{}),ContractorVo.class);
		return new BootTablePageDto<ContractorVo>(total,list);
	}
}
