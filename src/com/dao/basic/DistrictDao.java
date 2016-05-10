package com.dao.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.BaseDao;
import com.po.basic.District;
import com.po.basic.Person;

@Repository
public class DistrictDao extends BaseDao<Integer, District>{
   public void save(List<String[]> data){
	   List<District> districts=new ArrayList<>();
	   District district=null;
	   for (String[] _data : data) {
		   district=new District();
		   district.setDistrictCode(Long.parseLong(_data[0]));
		   district.setDistrictName(_data[1]);
		   district.setpCode(Long.parseLong(_data[2]));
		   district.setUpdateBy(null);
		   district.setUpdateTime(null);
		   districts.add(district);
	}
	   saveBatch(districts);
   }
   //获取已经存入的省市街道信息
   public List<District> getDistricts(){
	  return getAll();
   }
}
