package com.basic.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.basic.po.District;
import com.common.BaseDao;
import com.common.dto.BootTablePageDto;

@Repository
public class DistrictDao extends BaseDao<Long, District>{

	public void save(List<String[]> data){
		List<District> districts=new ArrayList<>();
		District district=null;
		for(String[] _data : data) {
			district=new District();
			district.setDistrictCode(Long.parseLong(_data[0]));
			district.setDistrictName(_data[1]);
			district.setpCode(Long.parseLong(_data[2]));
			district.setUpdateBy(Integer.parseInt(_data[3]));
			district.setUpdateTime(null);
			districts.add(district);
		}
		saveBatch(districts);
	}
   
	/**根据地区名称分页查询
	 * @param districtName 地区名称模糊匹配
	 * */
	@SuppressWarnings("unchecked")
	public BootTablePageDto<District> getByNameAndPage(String districtName, int offset, int limit){
		String hql = " from District where districtName like :districtName";
		long total = getCount("select count(1) "+hql, new String[]{"districtName"}, new String[]{"%"+districtName+"%"});
		List<District> list = (List<District>)super.findByPage(hql, offset, limit, new String[]{"districtName"}, new String[]{"%"+districtName+"%"});
		return new BootTablePageDto<>(total, list);
	}
	
	/**根据地区编码获取地区名称,多用于返回用户或订单的省、市、区县、乡镇信息*/
	@SuppressWarnings("unchecked")
	public List<String> getNameByCode(List<Long> codes){
		String hql = "select districtName from District where districtCode in (:codes)";
		return (List<String>)super.find(hql, new String[]{"codes"}, new Object[]{codes});
	}
	
	public boolean nameIsExist(String districtName){
		String hql = "select count(1) from District where districtName =:districtName";
		long amount = super.getCount(hql, new String[]{"districtName"}, new String[]{districtName});
		if(amount > 0)
			return true;
		else
			return false;
	}
	
	public boolean codeIsExist(long districtCode){
		String hql = "select count(1) from District where districtCode =:districtCode";
		long amount = super.getCount(hql, new String[]{"districtCode"}, new Long[]{districtCode});
		if(amount > 0)
			return true;
		else
			return false;
	}
}
