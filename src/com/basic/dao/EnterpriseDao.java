package com.basic.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.basic.po.Enterprise;
import com.basic.po.EnterpriseCostumeRela;
import com.basic.vo.AuthEnterpriseVo;
import com.basic.vo.StrengthEnterpriseVo;
import com.common.BaseDao;
import com.common.dto.BootTablePageDto;

@Repository
public class EnterpriseDao extends BaseDao<Integer, Enterprise>{
	
	@Autowired
	private EnterpriseCostumeRelaDao relaDao;
	
	@Transactional
	public void saveBatchEntity(List<Enterprise> enterpriseList){
		for(int i=0; i<enterpriseList.size(); i++){
			Enterprise enterprise = enterpriseList.get(i);
			super.persist(enterprise);
			int id = enterprise.getId();
			List<Integer> costumeCode = enterprise.getCostumeCode();
			for(int j=0; j<costumeCode.size(); j++){
				EnterpriseCostumeRela rela = new EnterpriseCostumeRela(id, costumeCode.get(j));
				relaDao.save(rela);
			}
		}
	}
	
	/**检查企业是否已经存在*/
	public boolean isExsit(String enterpriseName, Integer id){
		long amount = 0;
		if(id == null){
			String hql = "select count(1) from Enterprise where enterpriseName =:enterpriseName";
			amount = super.getCount(hql, new String[]{"enterpriseName"}, new String[]{enterpriseName});
		}else{
			String hql = "select count(1) from Enterprise where enterpriseName =:enterpriseName and id !=:id";
			amount = super.getCount(hql, new String[]{"enterpriseName","id"}, new Object[]{enterpriseName,id});
		}
		return amount>0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> isExist(Collection<String> enterpriseNames){
		String hql = "select enterpriseName from Enterprise where enterpriseName in (:enterpriseNames)";
		return (List<String>)super.find(hql, new String[]{"enterpriseNames"}, new Object[]{enterpriseNames});
	}
	
	/**获取关联的用户ID*/
	public Integer getUserId(int id){
		String sql = "select user_id from basic_enterprise where id =:id";
		return (Integer)super.findByNativeSql(sql, new String[]{"id"}, new Integer[]{id}).get(0);
	}
	
	/**实力工厂,展示员工人数多的几个工厂
	 * @param limit 要获取的实力工厂数
	 * */
	public List<StrengthEnterpriseVo> getStrength(int limit){
		String hql = "select id as id, enterpriseName as name, logo as logo from Enterprise order by staffNumber desc";
		return super.findByPage(hql, 0, limit, new String[]{}, null, StrengthEnterpriseVo.class);
	}
	
	/**最新入住的企业
	 * @param limit 工厂数
	 * */
	@SuppressWarnings("unchecked")
	public List<Enterprise> getNewest(int limit){
		String hql = "from Enterprise order by id desc";
		return (List<Enterprise>)super.findByPage(hql, 0, limit, new String[]{}, null);
	}
	
	/**最新认证加工厂
	 * @param limit 工厂数
	 * */
	public List<AuthEnterpriseVo> getNewAuth(int limit){
		String hql = "select id as id, enterpriseName as name, logo as logo from Enterprise where auditState = 2 order by id desc";
		return super.findByPage(hql, 0, limit, new String[]{}, null, AuthEnterpriseVo.class);
	}
	
	/**工厂搜索
	 * @param keyword 模糊匹配企业名称、工厂描述
	 * @param offset
	 * @param limit
	 * @return 企业ID、企业logo、企业名称、加工类型、员工人数、工厂介绍、所在地区、主营产品
	 * */
	public BootTablePageDto<Enterprise> search(Long province,Long city,Long county,Long town, Integer[] costumeCodes,
			String processType,Integer staffNumber,String keyword,int offset,int limit,Long total){
		StringBuffer countSql = new StringBuffer("select count(1)");
		StringBuffer subSql = new StringBuffer(" from basic_enterprise where (enterprise_name like :keyword or description like :keyword) or 1=1 ");
		List<String> params = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		//关键字匹配企业名称、工厂描述
		params.add("keyword");
		values.add("%"+keyword+"%");
		
		//省市区县乡镇
		if(province != null){
			subSql.append(" and province =:province");
			params.add("province");
			values.add(province);
		}
		if(city != null){
			subSql.append(" and city =:city");
			params.add("city");
			values.add(city);
		}
		if(county != null){
			subSql.append(" and county =:county");
			params.add("county");
			values.add(county);
		}
		if(town != null){
			subSql.append(" and town =:town");
			params.add("town");
			values.add(town);
		}
		//加工类型
		if(processType != null){
			subSql.append(" and process_type like :processType");
			params.add("processType");
			values.add("%"+processType+"%");
		}
		//服饰类型
		if(costumeCodes != null && costumeCodes.length >0){
			subSql.append(" and id in (select distinct enterprise_id from basic_enterprise_costume_rela where costume_code in (:costumeCodes))");
			params.add("costumeCodes");
			values.add(costumeCodes);
		}
		//工人数量
		if(staffNumber != null){
			switch(staffNumber){
			case 1:
				subSql.append(" and staff_number < 50");
				break;
			case 2:
				subSql.append(" and staff_number between 50 and 100");
				break;
			case 3:
				subSql.append(" and staff_number between 101 and 200");
				break;
			case 4:
				subSql.append(" and staff_number between 201 and 500");
				break;
			case 5:
				subSql.append(" and staff_number between 501 and 1000");
				break;
			case 6:
				subSql.append(" and staff_number > 1000");
				break;
			}
		}
		//若有total表示翻页操作，无须再次查询total
		if(total == null){
			countSql.append(subSql);
			BigInteger bigInt = (BigInteger)super.findByNativeSql(countSql.toString(), params, values).get(0);
			total = bigInt.longValue();
		}
		
		StringBuffer sql = new StringBuffer("select id, enterprise_name as enterpriseName, process_type as processType, staff_number as staffNumber, left(description,150) as description, province, city, county, logo");
		sql.append(subSql);
		List<Object[]> scalars = new ArrayList<Object[]>();
		scalars.add(new Object[]{"id",StandardBasicTypes.INTEGER});
		scalars.add(new Object[]{"enterpriseName",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"processType",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"staffNumber",StandardBasicTypes.INTEGER});
		scalars.add(new Object[]{"description",StandardBasicTypes.STRING});
		scalars.add(new Object[]{"province",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"city",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"county",StandardBasicTypes.LONG});
		scalars.add(new Object[]{"logo",StandardBasicTypes.STRING});
		List<Enterprise> enterprises = super.findByNativeSql(sql.toString(), params, values, scalars, offset, limit);
		return new BootTablePageDto<Enterprise>(total, enterprises);
	}
	
	/**根据服饰类型编码获取关联的企业
	 * @param limit 返回的记录数
	 * */
	@SuppressWarnings("unchecked")
	public BootTablePageDto<Enterprise> getByCostumeCode(int costumeCode, int offset, int limit){
		String hql = "select count(distinct enterpriseId) from EnterpriseCostumeRela where costumeCode =:costumeCode)";
		Long total = (Long)super.find(hql, new String[]{"costumeCode"}, new Integer[]{costumeCode}).get(0);
		
		hql = "from Enterprise where id in (select distinct enterpriseId from EnterpriseCostumeRela where costumeCode =:costumeCode)";
		List<Enterprise> list = (List<Enterprise>)super.findByPage(hql, offset, limit, new String[]{"costumeCode"}, new Integer[]{costumeCode});
		return new BootTablePageDto<Enterprise>(total, list);
	}
	
	/**获取工厂的图片*/
	public String[] getImgs(int id){
		String hql = "select logo,licenseImg,enterpriseImg from Enterprise where id =:id";
		Object[] obj = (Object[])super.find(hql, new String[]{"id"}, new Integer[]{id}).get(0);
		String[] imgs = new String[3];
		for(int i=0; i<obj.length; i++){
			if(obj[i] != null)
				imgs[i] = String.valueOf(obj[i]);
			else
				imgs[i] = "";
		}
		return imgs;
	}
}