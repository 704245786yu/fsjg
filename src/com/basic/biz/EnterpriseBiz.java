package com.basic.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basic.dao.BasicUserDao;
import com.basic.dao.EnterpriseCostumeRelaDao;
import com.basic.dao.EnterpriseDao;
import com.basic.po.BasicUser;
import com.basic.po.Enterprise;
import com.basic.vo.AuthEnterpriseVo;
import com.basic.vo.StrengthEnterpriseVo;
import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.sys.biz.ConstantDictBiz;
import com.sys.po.ConstantDict;
import com.util.DateTransform;
import com.util.FileUtil;

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
	
	public boolean isExsit(String enterpriseName, Integer enterpriseId){
		return dao.isExsit(enterpriseName, enterpriseId);
	}
	
	public BootTablePageDto<Enterprise> findByPage(String enterpriseName,Byte auditState,Integer createType,Date beginDate,Date endDate,int offset, int limit, Long total){
		BootTablePageDto<Enterprise> bt = dao.findByPage(enterpriseName, auditState, createType, beginDate, endDate, offset, limit, total);
		List<Enterprise> enterprises = bt.getRows();
		for(int i=0; i<enterprises.size(); i++){
			Enterprise e = enterprises.get(i);
			List<Integer> costumeCode = enterpriseCostumeRelaDao.getCostumeCode(e.getId());
			e.setCostumeCode(costumeCode);
		}
		return bt;
	}
	
	public ReturnValueVo batchSaveEnterprise(List<String[]> data,Integer userId){
		HashMap<String,Integer> tradeMap = costumeCategoryBiz.getTrade();	//行业类型为服饰类型的一级分类
		HashMap<String,Integer> costumeMap = costumeCategoryBiz.getAllNameCodeMap();	//服饰类型
		HashMap<String,String> processTypeMap = constantDictBiz.getNameValueMap("process_type");	//加工类型
		
		HashMap<String,Long> provinceMap = districtBiz.getNameAndCodeMap(null);//省级信息
		HashMap<Long, HashMap<String,Long>> cityMap = new HashMap<Long, HashMap<String,Long>>();//市级信息
		HashMap<Long, HashMap<String,Long>> countyMap = new HashMap<Long, HashMap<String,Long>>();//区县信息
		HashMap<Long, HashMap<String,Long>> townMap = new HashMap<Long, HashMap<String,Long>>();//镇/乡/街道信息
		
		HashSet<String> nameSet = new HashSet<String>();	//去重用
		HashSet<Long> teleSet = new HashSet<Long>();	//去重用
		List<Enterprise> enterpriseList = new ArrayList<Enterprise>();	//要保存的企业信息
		List<String> errorInfo = new ArrayList<String>();	//错误信息
		int rowOffset = 4;	//行偏移量
		
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
					if(!teleSet.add(telephone)) //判断Set中是否包含指定元素
						errorInfo.add("第"+(i+rowOffset)+"行 手机号码表中已存在");
					basicUser.setTelephone(telephone);
					basicUser.setPassword(defaultPassword);
					basicUser.setRoleId(2);
					basicUser.setState((byte)0);
					basicUser.setCreateBy(userId);
				}catch(Exception e){
					e.printStackTrace();
					errorInfo.add("第"+(i+rowOffset)+"行 手机号码非11位数字");
				}
				
				//创建Enterprise
				Enterprise enterprise=new Enterprise();
				//关联工厂的基本用户信息
				enterprise.setBasicUser(basicUser);
				enterprise.setNumber(this.generateNumber(enterprise.getBasicUser().getTelephone()).toString());
				//企业名称
				String enterpriseName = temp[0];
				if(enterpriseName.length() > 0 && enterpriseName.length()<=30){
					if(!nameSet.add(enterpriseName))
						errorInfo.add("第"+(i+rowOffset)+"行 企业名称表中已存在");
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
				String fixPhone = temp[7];
				if(fixPhone.length() <= 15){
					enterprise.setFixPhone(fixPhone);
				}else{
					errorInfo.add("第"+(i+rowOffset)+"行 固定电话超过15个字");
				}
				//QQ
				String qqStr = temp[9];
				if(qqStr.length()!=0)
					enterprise.setQq(Long.parseLong(qqStr));
				//销售市场
				if(!temp[10].equals("")){
					if(temp[10].equals("内销"))
						enterprise.setSaleMarket((byte)0);
					else if(temp[10].equals("外销"))
						enterprise.setSaleMarket((byte)1);
				}
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
				enterprise.setLogo("default_logo.png");
				//工厂图片
				//工厂介绍
				String description = temp[28];
				if(description.length() > 800){
					errorInfo.add("第"+(i+rowOffset)+"行 工厂介绍字数超过800个字");
				}else{
					enterprise.setDescription(description);
				}
				//审核状态
				enterprise.setAuditState((byte)2);
				enterprise.setAuditBy(userId);
				enterprise.setAuditTime(new Date());
				
				enterpriseList.add(enterprise);
			}catch(Exception e){
				e.printStackTrace();
				errorInfo.add("第"+(i+rowOffset)+"行 有错误内容");
			}
		}
		
		//检测手机号码数据库是否已存在
		List<Long> existTele = basicUserDao.teleIsExist(teleSet);
		if(existTele.size()>0)
			errorInfo.add("手机号码"+existTele.toString()+"已存在");
		//检测工厂名称数据库是否已存在
		List<String> existEnterpriseName = dao.isExist(nameSet);
		if(existEnterpriseName.size()>0)
			errorInfo.add("工厂名称"+existEnterpriseName.toString()+"已存在");
		
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
	
	/**获取行业类型编码，多个编码用,分割*/
	private String getTradeCode(String trade, HashMap<String,Integer> tradeMap){
		StringBuffer tradeCodeBuf = new StringBuffer(); 
		String[] tradeAry = trade.split("，");	//中文逗号分割
		for(int i=0; i<tradeAry.length; i++){
			Integer tradeCode = tradeMap.get(tradeAry[i]);
			tradeCodeBuf.append(tradeCode).append(',');
		}
		return tradeCodeBuf.toString().substring(0, tradeCodeBuf.length()-1);
	}
	
	/**获取加工类型编码，多个编码用,分割*/
	private String getProcessType(String processType, HashMap<String,String> processTypeMap){
		StringBuffer processCodeBuf = new StringBuffer();
		String[] processTypeAry = processType.split("，");	//中文逗号分割
		for(int i=0; i<processTypeAry.length; i++){
			String processTypeValue = processTypeMap.get(processTypeAry[i]);
			if(processTypeValue==null)
				return "";
			processCodeBuf.append(processTypeValue).append(',');
		}
		return processCodeBuf.toString().substring(0, processCodeBuf.length()-1);
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
		enterprise.setTown(townCode);
		return errorInfo;
	}
	
	/**获取加工企业的主营产品类型*/
	public List<Integer> getCostumeCode(int enterpriseId){
		return enterpriseCostumeRelaDao.getCostumeCode(enterpriseId);
	}
	
	/**实力工厂,展示员工人数多的几个个工厂
	 * @param limit 要获取的实力工厂数
	 * */
	public List<StrengthEnterpriseVo> getStrength(int limit){
		return dao.getStrength(limit);
	}
	
	/**获取优秀企业
	 * 暂时获取列表中前10个
	 * */
	public List<Enterprise> getExcellent(){
		List<Enterprise> enterprises = dao.getAllByPage(0, 10);
		for(int i=0; i<enterprises.size(); i++){
			Enterprise e = enterprises.get(i);
			List<Integer> costumeCode = enterpriseCostumeRelaDao.getCostumeCode(e.getId());
			e.setCostumeCode(costumeCode);
		}
		return enterprises;
	}
	
	/**最新入住的企业
	 * @param limit 工厂数
	 * */
	public List<Enterprise> getNewest(int limit){
		List<Enterprise> enterprises = dao.getNewest(limit);
		for(int i=0; i<enterprises.size(); i++){
			Enterprise e = enterprises.get(i);
			List<Integer> costumeCode = enterpriseCostumeRelaDao.getCostumeCode(e.getId());
			e.setCostumeCode(costumeCode);
		}
		return enterprises;
	}
	
	/**最新认证加工厂
	 * @param limit 工厂数
	 * @param isGetCostume 是否获取企业的主营产品信息。对于首页上的认证工厂无需获取，而工厂主页上的需要显示
	 * */
	public List<AuthEnterpriseVo> getNewAuth(int limit, boolean isGetCostume){
		List<AuthEnterpriseVo> enterprises = dao.getNewAuth(limit);
		if(isGetCostume){
			for(int i=0; i<enterprises.size(); i++){
				AuthEnterpriseVo e = enterprises.get(i);
				List<Integer> costumeCode = enterpriseCostumeRelaDao.getCostumeCode(e.getId());
				e.setCostumeCode(costumeCode);
			}
		}
		return enterprises;
	}
	
	/**工厂搜索
	 * @param keyword 模糊匹配企业名称、工厂描述、加工类型、主营产品
	 * @param offset
	 * @param limit
	 * @param total 可为null
	 * @return 企业logo、企业名称、加工类型、员工人数、工厂介绍、所在地区、主营产品
	 * */
	public BootTablePageDto<Enterprise> search(Long province,Long city,Long county,Long town,
			Integer[] costumeCodes, Integer processType, Integer staffNumber, String keyword,int offset,int limit,Long total){
		String processTypeStr = null;//要查询的加工类型编码
		//判断是否需要根据关键字匹配加工类型
		if(processType == null){
			if(keyword != null && keyword.length() > 0){
				//为简化查询，不匹配多个加工类型
				List<ConstantDict> processTypes = constantDictBiz.getByCodeAndConstantName("process_type", keyword);
				if(processTypes.size() != 0){
					processTypeStr = processTypes.get(0).getConstantValue();
				}
			}
		}else{
			processTypeStr = processType.toString();
		}
		
		//判断是否需要根据关键字匹配主营产品
		if(costumeCodes == null || costumeCodes.length == 0){
			List<Integer> costumeCategoryCodes = new ArrayList<Integer>();
//			int endIndex = 0;
			if(keyword.length() > 0){
				costumeCategoryCodes = costumeCategoryBiz.getCodeByCategoryName(keyword);
				//为保证性能，取前3条服饰类型记录
//				endIndex = costumeCategoryCodes.size()>3 ? 3 : costumeCategoryCodes.size();
			}
//			costumeCodes = costumeCategoryCodes.subList(0, endIndex).toArray(new Integer[]{});
			costumeCodes = costumeCategoryCodes.toArray(new Integer[]{});
		}
		
		BootTablePageDto<Enterprise> result = dao.search(province,city,county,town,
				costumeCodes,processTypeStr,staffNumber,keyword,offset,limit,total);
		List<Enterprise> enterprises = result.getRows();
		for(int i=0; i<enterprises.size(); i++){
			Enterprise e = enterprises.get(i);
			List<Integer> list = enterpriseCostumeRelaDao.getCostumeCode(e.getId());
			e.setCostumeCode(list);
		}
		return result;
	}
	
	/**根据BasicUser的id查询对应的企业信息,以及企业用户的主营产品*/
	public Enterprise getByBasicUserId(int userId){
		Criterion c = Restrictions.eq("basicUser.id", userId);
		Enterprise e = dao.findByCriteria(c).get(0);
		int eId = e.getId();
		e.setCostumeCode(enterpriseCostumeRelaDao.getCostumeCode(eId));
		return e;
	}
	
	/**根据工厂编号获取工厂信息*/
	public Enterprise getByNum(String num){
		Criterion c = Restrictions.eq("number", num);
		Enterprise e = dao.findByCriteria(c).get(0);
		int eId = e.getId();
		e.setCostumeCode(enterpriseCostumeRelaDao.getCostumeCode(eId));
		return e;
	}
	
	/**根据BasicUser的id获取企业的id
	 * */
	@SuppressWarnings("unchecked")
	public Integer getIdByUserId(int userId){
		String hql = "select id from Enterprise where basicUser.id =:userId";
		List<Integer> list = (List<Integer>)dao.find(hql, new String[]{"userId"}, new Integer[]{userId});
		return list.get(0);
	}
	
	/**根据BasicUser的id获取企业的number
	 * */
	@SuppressWarnings("unchecked")
	public String getNumByUserId(int userId){
		String hql = "select number from Enterprise where basicUser.id =:userId";
		List<String> list = (List<String>)dao.find(hql, new String[]{"userId"}, new Integer[]{userId});
		return list.get(0);
	}
	
	/**根据服饰类型编码获取关联的企业
	 * @param limit 返回的记录数
	 * */
	public BootTablePageDto<Enterprise> getByCostumeCode(int costumeCode,int offset,int limit){
		BootTablePageDto<Enterprise> dto = dao.getByCostumeCode(costumeCode,offset,limit);
		List<Enterprise> enterprises = dto.getRows();
		for(int i=0; i<enterprises.size(); i++){
			Enterprise e = enterprises.get(i);
			List<Integer> costumeCodes = enterpriseCostumeRelaDao.getCostumeCode(e.getId());
			e.setCostumeCode(costumeCodes);
		}
		return dto;
	}
	
	/**保存工厂，同时保存工厂的主营产品信息
	 * */
	@Transactional
	@Override
	public void save(Enterprise e) {
		e.getBasicUser().setPassword(defaultPassword);
		e.getBasicUser().setRoleId(2);
		e.setAuditState((byte)0);
		e.setNumber(this.generateNumber(e.getBasicUser().getTelephone()).toString());
		dao.persist(e);
		enterpriseCostumeRelaDao.save(e.getId(), e.getCostumeCode());
	}
	
	/**先更新基本用户信息，后更新企业信息*/
	@Transactional
	public void update(Enterprise e,Byte isAudit) {
		BasicUser basicUser = e.getBasicUser();
		BasicUser oldBasicUser = basicUserDao.findById(basicUser.getId());
		basicUser.setPassword(oldBasicUser.getPassword());
		basicUser.setRoleId(oldBasicUser.getRoleId());
		basicUser.setState(oldBasicUser.getState());
		basicUser.setCreateBy(oldBasicUser.getCreateBy());
		basicUser.setCreateTime(oldBasicUser.getCreateTime());
		
		Enterprise old = this.getByBasicUserId(basicUser.getId());
		Byte oldAuditState = old.getAuditState();
		//审核状态
		if(isAudit==null || ((Integer)(isAudit+1)).equals(oldAuditState)){
			e.setAuditState(oldAuditState);
			e.setAuditBy(old.getAuditBy());
			e.setAuditTime(old.getAuditTime());
		}else{
			e.setAuditState((byte)(isAudit+1));
			e.setAuditBy(e.getBasicUser().getUpdateBy());
			e.setAuditTime(new Date());
		}
		
		//更新主营产品
		List<Integer> newCodes = new ArrayList<Integer>(e.getCostumeCode().size()); 
		newCodes.addAll(e.getCostumeCode());
		List<Integer> oldCodes = enterpriseCostumeRelaDao.getCostumeCode(e.getId());
		if(oldCodes.size()!=0){
			//对于刚注册的用户，是没有设置主营产品的,oldCodes.size()为0，会导致下面的代码空指针异常
			List<Integer> clone = new ArrayList<Integer>(oldCodes);
			oldCodes.removeAll(newCodes);
			if(oldCodes.size() > 0)
				enterpriseCostumeRelaDao.delete(e.getId(), oldCodes);
			newCodes.removeAll(clone);
			if(newCodes.size() > 0){
				enterpriseCostumeRelaDao.save(e.getId(),newCodes);
			}
		}else{
			enterpriseCostumeRelaDao.save(e.getId(),newCodes);
		}
		dao.merge(e);
	}
	
	/**删除企业信息时同时删除关联的BasicUser、EnterpriseCostumeRela、图片信息*/
	@Transactional
	public void deleteById(Integer id,String uploadDir) {
		int userId = dao.getUserId(id);
		String[] imgs = dao.getImgs(id);
		if(imgs[0].length()>0 && !imgs[0].equals("default_logo.png"))
			FileUtil.delImg(uploadDir, imgs[0]);
		if(imgs[1].length()>0)
			FileUtil.delImg(uploadDir, imgs[1]);
		
		String enterpriseImg = imgs[2];
		if(enterpriseImg.length()!=0){
			String[] pics = enterpriseImg.split(",");
			FileUtil.delImg(uploadDir, pics);
		}
		enterpriseCostumeRelaDao.delByEnterpriseId(id);
		dao.deleteById(id);
		basicUserDao.deleteById(userId);
	}
	
	/**根据字段名获取数据*/
	@SuppressWarnings("unchecked")
	public Object[] getByField(String num,String ...fields){
		StringBuilder hql = new StringBuilder("select "+fields[0]);
		for(int i=1; i<fields.length; i++){
			hql.append(","+fields[i]);
		}
		hql.append(" from Enterprise where number =:num");
		List<Object[]> list = (List<Object[]>)dao.find(hql.toString(), new String[]{"num"}, new String[]{num});
		if(list.size()==1)
			return list.get(0);
		return null;
	}
	
	/**生成企业编号格式：YYMMddHHmmss+ 3位随机数 + 手机号后4位
	 * @param telephone 手机号
	 * */
	public Long generateNumber(Long telephone){
		String time = DateTransform.Date2String(new Date(), "YYMMddHHmmss");
		int random = new java.util.Random().nextInt(900)+100;
		String numStr = time + random +telephone.toString().substring(7);
		return Long.valueOf(numStr);
	}
	
	@SuppressWarnings("unchecked")
	public List<HashMap<String,String>> getNames(String name){
		String hql = "select number,enterpriseName from Enterprise where enterpriseName like :name";
		List<Object[]> list = (List<Object[]>)dao.findByPage(hql, 0, 10, new String[]{"name"}, new String[]{"%"+name+"%"});
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		for(int i=0; i<list.size(); i++){
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("num", list.get(i)[0].toString());
			map.put("name", list.get(i)[1].toString());
			result.add(map);
		}
		return result;
	}
	
	/**获取省市信息*/
	@SuppressWarnings("unchecked")
	public Object[] getDistrict(int userId){
		String hql = "select province,city from Enterprise where basicUser.id =:userId";
		List<Object[]> list = (List<Object[]>)dao.find(hql, new String[]{"userId"}, new Integer[]{userId});
		if(list.size()==0)
			return null;
		else
			return list.get(0);
	}
}
