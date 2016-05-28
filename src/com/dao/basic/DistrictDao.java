package com.dao.basic;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.BaseDao;
import com.po.basic.District;

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
		   district.setUpdateBy(Integer.parseInt(_data[3]));
		   district.setUpdateTime(null);
		   districts.add(district);
	}
	   saveBatch(districts);
   }
}
