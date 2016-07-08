package com.basic.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basic.dao.BasicUserDao;
import com.basic.dao.EnterpriseCostumeRelaDao;
import com.basic.dao.EnterpriseDao;
import com.basic.po.BasicUser;
import com.basic.po.Enterprise;
import com.basic.po.EnterpriseCostumeRela;
import com.common.BaseBiz;
import com.common.vo.ReturnValueVo;
import com.sys.biz.ConstantDictBiz;

@Service
public class EnterpriseBiz extends BaseBiz<EnterpriseDao, Integer, Enterprise>{
	
	@Autowired
	private DistrictBiz districtBiz;
	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	@Autowired
	private ConstantDictBiz constantDictBiz;
	@Autowired
	private EnterpriseCostumeRelaDao enterpriseCostumeRelaDao;
	@Autowired
	private BasicUserDao basicUserDao;

	private static final String defaultPassword = "123456";
	
	public boolean isExsit(String enterpriseName){
		return dao.isExsit(enterpriseName);
	}
	
	public ReturnValueVo batchSaveEnterprise(List<String[]> data,Integer userId){
		HashMap<String,Integer> tradeMap = costumeCategoryBiz.getChildCostumeMap(null);	//行业类型为服饰类型的一级分类
		HashMap<String,Integer> costumeMap = costumeCategoryBiz.getAllNameCodeMap();	//服饰类型
		HashMap<String,String> processTypeMap = constantDictBiz.getNameValueMap("process_type");	//加工类型
		
		HashMap<String,Long> provinceMap = districtBiz.getNameAndCodeMap(null);//省级信息
		HashMap<Long, HashMap<String,Long>> cityMap = new HashMap<Long, HashMap<String,Long>>();//市级信息
		HashMap<Long, HashMap<String,Long>> countyMap = new HashMap<Long, HashMap<String,Long>>();//区县信息
		HashMap<Long, HashMap<String,Long>> townMap = new HashMap<Long, HashMap<String,Long>>();//镇/乡/街道信息
		
		List<String> nameList = new ArrayList<String>();	//去重用
		List<Long> teleList = new ArrayList<Long>();	//去重用
		List<Enterprise> enterpriseList = new ArrayList<Enterprise>();	//要保存的企业信息
		List<String> errorInfo = new ArrayList<String>();	//错误信息
		int rowOffset = 3;	//行偏移量
		
		for(int i=0; i<data.size(); i++){
			try{
				String[] temp = data.get(i);
				//创建BasicUser
				BasicUser basicUser = new BasicUser();
				try{
					String teleStr = temp[8];
					if(teleStr.length() != 11)
						throw new Exception("telephone length != 11");
					Long telephone = Long.parseLong(teleStr);
					teleList.add(telephone);
					basicUser.setTelephone(telephone);
					basicUser.setPassword(defaultPassword);
					basicUser.setRoleId(2);
					basicUser.setCreateBy(userId);
				}catch(Exception e){
					e.printStackTrace();
					errorInfo.add("第"+(i+rowOffset)+"行 手机号码非11位数字");
				}
				
				//创建Enterprise
				Enterprise enterprise=new Enterprise();
				//关联工厂的基本用户信息
				enterprise.setBasicUser(basicUser);
	//			enterprise.setEnterpriseNumber(temp[0]);
				//企业名称
				String enterpriseName = temp[0];
				if(enterpriseName.length() > 0 && enterpriseName.length()<=30){
					nameList.add(enterpriseName);
				}else{
					errorInfo.add("第"+(i+rowOffset)+"行 企业名称未填或超过30个字");
				}
				enterprise.setEnterpriseName(enterpriseName);
				//联系人
				enterprise.setLinkman(temp[1]);
				//省，市，区县，镇/乡/街道
				List<String> subErrorInfo = this.setDistrictCode(i+rowOffset, enterprise, temp[2], temp[3], temp[4], temp[5], provinceMap, cityMap, countyMap, townMap);
				errorInfo.addAll(subErrorInfo);
				//详细地址
				String detailAddr = temp[6];
				if(detailAddr.length() < 50){
					enterprise.setDetailAddr(detailAddr);
				}else{
					errorInfo.add("第"+(i+rowOffset)+"行 详细地址超过50个字");
				}
				//固定电话
				enterprise.setFixPhone(temp[7]);
				//QQ
				String qqStr = temp[9];
				if(qqStr.length()!=0)
					enterprise.setQq(Long.parseLong(qqStr));
				//销售市场
	//			enterprise.setSaleMarket(Byte.parseByte(temp[10]));
				//营业执照
	//			enterprise.setBusinessLicenseImg(temp[11]);
				//组织机构代码
				enterprise.setOrgCode(temp[12]);
				//所属行业
				String tradeCode = this.getTradeCode(temp[13], tradeMap);
				if(tradeCode.length() == 0)
					errorInfo.add("第"+(i+rowOffset)+"行 行业分类信息不正确");
				else
					enterprise.setTrade(tradeCode);
				//加工类型
				String processType = this.getProcessType(temp[14], processTypeMap);
				if(processType.length() == 0)
					errorInfo.add("第"+(i+rowOffset)+"行 加工类型信息不正确");
				else
					enterprise.setProcessType(processType);
				//主营产品
				List<Integer> costumeCode = this.getCostumeCategory(temp[15], costumeMap);
				if(costumeCode.size() == 0)
					errorInfo.add("第"+(i+rowOffset)+"行 主营产品信息不正确");
				else
					enterprise.setCostumeCode(costumeCode);
				//员工人数
				String staffNumber = temp[16];
				if(staffNumber.length() != 0)
					enterprise.setStaffNumber(Integer.parseInt(staffNumber));
				//高速车工人数
				String highSpeedStaffNumber = temp[17];
				if(highSpeedStaffNumber.length() != 0)
					enterprise.setHighSpeedStaffNumber(Integer.parseInt(highSpeedStaffNumber));
				//其他加工人数
				String otherStaffNumber = temp[18];
				if(otherStaffNumber.length() != 0)
					enterprise.setOtherStaffNumber(Integer.parseInt(otherStaffNumber));
				//经营年限
				String enterpriseAge = temp[19];
				if(enterpriseAge.length() != 0)
					enterprise.setEnterpriseAge(Short.parseShort(enterpriseAge));
				//生产设备
				String equipment = temp[20];
				if(equipment.length() > 300){
					errorInfo.add("第"+(i+rowOffset)+"行 生产设备字数超过300个字");
				}else{
					enterprise.setEquipment(equipment);
				}
				//产值产量
				enterprise.setYield(temp[21]);
				//合作客户
				String cooperator = temp[22];
				if(equipment.length() > 200){
					errorInfo.add("第"+(i+rowOffset)+"行 合作客户字数超过200个字");
				}else{
					enterprise.setCooperator(cooperator);
				}
				enterprise.setWebsiteUrl(temp[23]);
				enterprise.setWechat(temp[24]);
				enterprise.setEmail(temp[25]);
				//有企业logo
				//工厂图片
				//工厂介绍
				String description = temp[28];
				if(description.length() > 800){
					errorInfo.add("第"+(i+rowOffset)+"行 工厂介绍字数超过800个字");
				}else{
					enterprise.setCooperator(cooperator);
				}
				enterprise.setAuditState((byte)0);
				
				enterpriseList.add(enterprise);
			}catch(Exception e){
				e.printStackTrace();
				errorInfo.add("第"+(i+rowOffset)+"行 有错误内容");
			}
		}
		
		if(errorInfo.size() != 0)
			return new ReturnValueVo(ReturnValueVo.ERROR, errorInfo);
		
		try{
			dao.saveBatchEntity(enterpriseList);
		}catch(Exception e){
			e.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "保存数据出错请联系管理员");
		}
		return new ReturnValueVo(ReturnValueVo.SUCCESS,null);
	}
	
	/**获取行业类型编码，多个编码用空格分割*/
	private String getTradeCode(String trade, HashMap<String,Integer> tradeMap){
		StringBuffer tradeCodeBuf = new StringBuffer(); 
		String[] tradeAry = trade.split("，");	//中文逗号分割
		for(int i=0; i<tradeAry.length; i++){
			Integer tradeCode = tradeMap.get(tradeAry[i]);
			tradeCodeBuf.append(tradeCode).append(' ');
		}
		return tradeCodeBuf.toString().trim();
	}
	
	/**获取加工类型编码，多个编码用空格分割*/
	private String getProcessType(String processType, HashMap<String,String> processTypeMap){
		StringBuffer processCodeBuf = new StringBuffer();
		String[] processTypeAry = processType.split("，");	//中文逗号分割
		for(int i=0; i<processTypeAry.length; i++){
			String processTypeValue = processTypeMap.get(processTypeAry[i]);
			processCodeBuf.append(processTypeValue).append(' ');
		}
		return processCodeBuf.toString().trim();
	}
	
	/**获取服饰类型编码*/
	private List<Integer> getCostumeCategory(String costume, HashMap<String,Integer> costumeMap){
		List<Integer> costumeCodeList = new ArrayList<Integer>();
		String[] costumeAry = costume.split("，");	//中文逗号分割
		for(int i=0; i<costumeAry.length; i++){
			Integer costumeCode = costumeMap.get(costumeAry[i]);
			if(costumeCode != null)
				costumeCodeList.add(costumeCode);
		}
		return costumeCodeList;
	}
	
	/**设置地区编码信息
	 * @return errorInfo
	 * */
	private List<String> setDistrictCode(int rowNum, Enterprise enterprise, String province, String city, String county, String town,
			HashMap<String,Long> provinceMap, HashMap<Long, HashMap<String,Long>> cityMap, HashMap<Long, HashMap<String,Long>> countyMap, HashMap<Long, HashMap<String,Long>> townMap){
		List<String> errorInfo = new ArrayList<String>();	//错误信息
		if(province.length()==0 || city.length()==0 || county.length()==0){
			errorInfo.add("第"+rowNum+"行 省市区县信息填写不完整");
			return errorInfo;
		}
		Long provinceCode = provinceMap.get(province);
		if(provinceCode == null){
			errorInfo.add("第"+rowNum+"行 省份信息不正确或无此省份信息");
			return errorInfo;
		}
		enterprise.setProvince(provinceCode);
		
		HashMap<String,Long> subCityMap = cityMap.get(provinceCode);
		if(subCityMap == null){
			subCityMap = districtBiz.getNameAndCodeMap(provinceCode);
			cityMap.put(provinceCode, subCityMap);
		}
		Long cityCode = subCityMap.get(city);
		if(cityCode == null){
			errorInfo.add("第"+rowNum+"行 城市信息不正确或无此城市信息");
			return errorInfo;
		}
		enterprise.setCity(cityCode);
		
		HashMap<String,Long> subCountyMap = countyMap.get(cityCode);
		if(subCountyMap == null){
			subCountyMap = districtBiz.getNameAndCodeMap(cityCode);
			countyMap.put(cityCode, subCountyMap);
		}
		Long countyCode = subCountyMap.get(county);
		if(countyCode == null){
			errorInfo.add("第"+rowNum+"行 区县信息不正确或无此区县信息");
			return errorInfo;
		}
		enterprise.setCounty(countyCode);
		
		//镇/乡/街道信息可不填
		if(town.length() == 0)
			return errorInfo;
		HashMap<String,Long> subTownMap = townMap.get(countyCode);
		if(subTownMap == null){
			subTownMap = districtBiz.getNameAndCodeMap(countyCode);
			townMap.put(countyCode, subTownMap);
		}
		Long townCode = subTownMap.get(town);
		if(townCode == null){
			errorInfo.add("第"+rowNum+"行镇/乡/街道信息不正确或无此信息");
			return errorInfo;
		}
		return errorInfo;
	}
	
	public void cascadeSave(){
		BasicUser basicUser = new BasicUser();
		basicUser.setTelephone((long)1);
		basicUser.setPassword("123");
		basicUser.setRoleId(1);
		
		Enterprise enterprise = new Enterprise();
		enterprise.setEnterpriseName("哈哈哈");
		enterprise.setBasicUser(basicUser);
		List<Integer> costumeCode = new ArrayList<Integer>();
		enterprise.setCostumeCode(costumeCode);
		dao.save(enterprise);
		
		
		for(int i=0; i<2; i++){
			EnterpriseCostumeRela rela = new EnterpriseCostumeRela();
			rela.setEnterpriseId(enterprise.getId());
			rela.setCostumeCode(i);
			enterpriseCostumeRelaDao.save(rela);
		}
	}
	
	/**删除企业信息时同时删除关联的BasicUser、EnterpriseCostumeRela信息*/
	@Override
	@Transactional
	public void deleteById(Integer id) {
		int userId = dao.getUserId(id);
		enterpriseCostumeRelaDao.delByEnterpriseId(id);
		dao.deleteById(id);
		basicUserDao.deleteById(userId);
	}

	/**获取优秀企业
	 * 暂时获取列表中前10个
	 * */
	public List<Enterprise> getExcellent(){
		return dao.getAllByPage(0, 10);
	}
	
	/**最新入住的企业*/
	public List<Enterprise> getNewest(){
		return dao.getNewest();
	}
	
	/**最新认证加工厂*/
	public List<Enterprise> getNewAuth(){
		return dao.getNewest();
	}
}
