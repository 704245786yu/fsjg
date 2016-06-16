package com.biz.basic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.dao.basic.DistrictDao;
import com.po.basic.District;

@Service
public class DistrictBiz extends BaseBiz<DistrictDao,Integer,District>{
	
	private static final String treeRoot = "100000000000";//100000000000是自定义的根节点，已经手动在数据库添加
	
	/**批量保存省市街道信息
	 * */
	public Integer batchSaveDistrict1(String proviceName,String provinceCode,List<String[]> data,Integer userId){
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
		List<District> districts=dao.getAll();
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
//		for (String[] _tempData : tempData) {
//			System.out.println(_tempData[0]+_tempData[1]+_tempData[2]);
//		}
		List<District> _districts=new ArrayList<>();
		   for (String[] _data : tempData) {
			   District  district=new District();
			   district.setDistrictCode(Long.parseLong(_data[0]));
			   district.setDistrictName(_data[1]);
			   district.setpCode(Long.parseLong(_data[2]));
			   district.setUpdateBy(Integer.parseInt(_data[3]));
			   district.setUpdateTime(null);
			   _districts.add(district);
		}
		dao.saveBatch(_districts);
		return 1;  
	}
	
	/**批量保存省市街道信息
	 * @author zhiyu
	 * @return ArrayList<Integer> errorRowNum 数据有问题的行号
	 * */
	public ArrayList<Integer> batchSaveDistrict(String proviceName,String provinceCode,List<String[]> data,Integer userId){
		System.out.println("一共有"+data.size()+"行记录");
		//将已经添加的地区编号加入，做防重复验证(载入文档当中的)
		List<String> existDistrict = new ArrayList<>();
		
		ArrayList<District> list = new ArrayList<District>();
		
		//添加省份信息
		District province = new District(Long.parseLong(provinceCode), proviceName, null, userId);
		list.add(province);
		
		//记录有问题的数据行号
		ArrayList<Integer> errorRowNum = new ArrayList<Integer>();
		//开始读取每行的数据
		int length=data.size();
		for(int i=1;i<length;i++)
		{
			try{
				String[] dataRow=data.get(i);
				if( !dataRow[1].equals("") && !existDistrict.contains(dataRow[1]) ){//市级
					existDistrict.add(dataRow[1]);
					District city =new District(Long.parseLong(dataRow[1]), dataRow[2], province.getDistrictCode(), userId);
					list.add(city);
				}
				if( !dataRow[3].equals("") && !existDistrict.contains(dataRow[3]) ){//区级
					existDistrict.add(dataRow[3]);
					District county = new District(Long.parseLong(dataRow[3]), dataRow[4], Long.parseLong(dataRow[1]), userId);
					list.add(county);
				}
				if( !dataRow[5].equals("") && !existDistrict.contains(dataRow[5]) ){//城镇
					existDistrict.add(dataRow[5]);
					District town = new District(Long.parseLong(dataRow[5]), dataRow[6], Long.parseLong(dataRow[3]), userId);
					list.add(town);
				}
			}catch(Exception e){
				errorRowNum.add(i);
				e.printStackTrace();
			}
		}
		if(errorRowNum.size() == 0){
			dao.saveOrUpdateBatch(list);
			return null;
		}else
			return errorRowNum;
	}
	
	/**根据父节点代码获取地区名字和地区代码
	 * @param pCode 可为null，表示获取省级信息
	 * @return List<District> districtCode districtName
	 * */
	@SuppressWarnings("unchecked")
	public List<District> getNameAndCodeByPcode(Long pCode){
		if(pCode == null){
			String hql = "select new District(districtCode, districtName) from District where pCode is null";
			return (List<District>)dao.find(hql);
		}else{
			String hql = "select new District(districtCode, districtName) from District where pCode =:pCode";
			return (List<District>)dao.find(hql, new String[]{"pCode"}, new Long[]{pCode});
		}
	}
	
}
