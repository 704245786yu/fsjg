package com.biz.basic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.dao.basic.DistrictDao;
import com.po.basic.District;
import com.util.JacksonJson;

@Service
public class DistrictBiz extends BaseBiz<DistrictDao,Integer,District>{
	private static final String treeRoot = "100000000000";//100000000000是自定义的根节点，已经手动在数据库添加
	@Autowired
	private DistrictDao districtDao;
	/**批量保存省市街道信息
	 * */
	public Integer batchSaveDistrict(String proviceName,String provinceCode,List<String[]> data,Integer userId){
		//JacksonJson.printBeanToJson(data);
		//TODO something
		
		String userIdString=userId.toString();
		//用来放置符合条件的数据
		List<String[]> tempData=new ArrayList<>();
		//构建数组，和数据库格式一致
		String[] provinceData=null;
		//将已经添加的编号加入，做防重复验证(第一步载入数据库已经存在的，第二部载入文档当中的)
		List<String> ifExistList=new ArrayList<>();
		//取出数据库数据
		List<District> districts=districtDao.getDistricts();
		for (District district : districts) {
			ifExistList.add(district.getDistrictCode().toString());
		}
		//添加省份信息
		if(!ifExistList.contains(provinceCode)){
			provinceData=new String[]{provinceCode,proviceName,treeRoot,userIdString,null};
			tempData.add(provinceData);
			ifExistList.add(provinceCode);
		}
		//开始读取每行的数据
		int rows=data.size();
		for(int i=1;i<rows;i++)//去掉标题，从第二行开始
		{
			String[] dataRow=data.get(i);
			if(!ifExistList.contains(dataRow[1])){//市级
				 provinceData=new String[]{dataRow[1],dataRow[2],provinceCode,userIdString,null};
				 tempData.add(provinceData);
				 ifExistList.add(dataRow[1]);
			}
			if(!ifExistList.contains(dataRow[3])){//区级
				 provinceData=new String[]{dataRow[3],dataRow[4],dataRow[1],userIdString,null};
				 tempData.add(provinceData);
				 ifExistList.add(dataRow[3]);
			}
			if(!ifExistList.contains(dataRow[5])){//城镇
				 provinceData=new String[]{dataRow[5],dataRow[6],dataRow[3],userIdString,null};
				 tempData.add(provinceData);
				 ifExistList.add(dataRow[5]);
			}
		}
		for (String[] _tempData : tempData) {
			System.out.println(_tempData[0]+_tempData[1]+_tempData[2]);
		}
		districtDao.save(tempData);
		return 1;
	}
}
