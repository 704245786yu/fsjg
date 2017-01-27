package com.basic.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basic.dao.BasicUserDao;
import com.basic.dao.ContractorDao;
import com.basic.dao.PersonDao;
import com.basic.dto.ContractorDto;
import com.basic.po.BasicUser;
import com.basic.po.Contractor;
import com.basic.po.Person;
import com.basic.po.UserAbstract;
import com.basic.vo.ContractorHomeVo;
import com.basic.vo.ContractorSimpleVo;
import com.basic.vo.ContractorVo;
import com.common.BaseBiz;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.sys.biz.ConstantDictBiz;
import com.util.NumberTransform;

@Service
public class ContractorBiz extends BaseBiz<ContractorDao, Integer, Contractor> {

	@Autowired
	private PersonDao personDao;
	@Autowired
	private DistrictBiz districtBiz;
	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	@Autowired
	private ConstantDictBiz constantDictBiz;
	@Autowired
	private BasicUserDao basicUserDao;
	
	private static final String defaultPassword = "123456";
	
	/**批量保存快产专家信息
	 * 快产专家拥有个人Person的所有字段，先保存Person后保存contractor
	 * */
	public ReturnValueVo batchSaveContractor(List<String[]> data,Integer userId){
		HashMap<String,Integer> costumeMap = costumeCategoryBiz.getAllNameCodeMap();	//服饰类型
		HashMap<String,String> processTypeMap = constantDictBiz.getNameValueMap("process_type");	//加工类型
		
		HashMap<String,Long> provinceMap = districtBiz.getNameAndCodeMap(null);//省级信息
		HashMap<Long, HashMap<String,Long>> cityMap = new HashMap<Long, HashMap<String,Long>>();//市级信息
		HashMap<Long, HashMap<String,Long>> countyMap = new HashMap<Long, HashMap<String,Long>>();//区县信息
		HashMap<Long, HashMap<String,Long>> townMap = new HashMap<Long, HashMap<String,Long>>();//镇/乡/街道信息
		
		HashSet<Long> teleSet = new HashSet<Long>();	//去重用
		
		List<Person> personList = new ArrayList<Person>();	//个人信息
		List<Contractor> contractorList = new ArrayList<Contractor>();	//快产专家（个人承包商）信息
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
					basicUser.setRoleId(1);
					basicUser.setState((byte)0);
					basicUser.setCreateBy(userId);
				}catch(Exception e){
					e.printStackTrace();
					errorInfo.add("第"+(i+rowOffset)+"行 手机号码非11位数字");
				}
				
				Person person = new Person();
				person.setBasicUser(basicUser);
				person.setRealName(temp[0]);
				person.setGender(temp[1]);
				try{
					person.setAge(Byte.parseByte(temp[2]));
				}catch(Exception e){
					e.printStackTrace();
					errorInfo.add("第"+(i+rowOffset)+"行 年龄有误");
				}
				//省，市，区县，镇/乡/街道
				List<String> subErrorInfo = this.setDistrictCode(i+rowOffset, person, temp[3], temp[4], temp[5], temp[6], provinceMap, cityMap, countyMap, townMap);
				errorInfo.addAll(subErrorInfo);
				//详细地址
				String detailAddr = temp[7];
				if(detailAddr.length() < 50){
					person.setDetailAddr(detailAddr);
				}else{
					errorInfo.add("第"+(i+rowOffset)+"行 详细地址超过50个字");
				}
				person.setEmail(temp[15]);
				//QQ
				String qqStr = temp[16];
				if(qqStr.length()!=0)
					person.setQq(Long.parseLong(qqStr));
				//固定电话
				person.setFixPhone(temp[17]);
				person.setWechat(temp[18]);
				person.setPostalCode(temp[19]);
				//身份证号码
				person.setIdNum(temp[20]);
				//审核状态
				person.setAuditState((byte)2);
				person.setAuditBy(userId);
				person.setAuditTime(new Date());
				
				personList.add(person);
				
				//快产专家
				Contractor contractor = new Contractor();
				//加工类型
				String processType = this.getProcessType(temp[9], processTypeMap);
				if(processType.length() == 0)
					errorInfo.add("第"+(i+rowOffset)+"行 加工类型信息不正确");
				else
					contractor.setProcessType(processType);
				//专业技能(原主营产品)
				String costumeCode = this.getCostumeCategory(temp[10], costumeMap);
				if(costumeCode.length() == 0)
					errorInfo.add("第"+(i+rowOffset)+"行 专业技能信息不正确");
				else
					contractor.setCostumeCode(costumeCode);
				contractor.setProcessYear(NumberTransform.getShort(temp[11]));
				contractor.setWorkerAmount(NumberTransform.getShort(temp[12]));
				//工作场地
				String workSpaceStr = temp[13];
				if(workSpaceStr.equals("在家"))
					contractor.setWorkSpace((byte)0);
				else if(workSpaceStr.equals("到厂"))
					contractor.setWorkSpace((byte)1);
				else
					errorInfo.add("第"+(i+rowOffset)+"行工作场地不正确");
					
				//报价
				String quote = temp[14];
				if(quote.length() > 200){
					errorInfo.add("第"+(i+rowOffset)+"行 报价字数超过20个字");
				}else{
					contractor.setQuote(quote);
				}
				String equipment = temp[21];
				if(equipment.length() > 100){
					errorInfo.add("第"+(i+rowOffset)+"行 生产设备字数超过100个字");
				}else{
					contractor.setEquipment(equipment);
				}
				String processDesc = temp[22];
				if(processDesc.length() > 100){
					errorInfo.add("第"+(i+rowOffset)+"行 加工说明字数超过100个字");
				}else{
					contractor.setProcessDesc(processDesc);
				}
				contractorList.add(contractor);
			}catch(Exception e){
				e.printStackTrace();
				errorInfo.add("第"+(i+rowOffset)+"行 有错误内容");
			}
		}
		
		//检测手机号码数据库是否已存在
		List<Long> existTele = basicUserDao.teleIsExist(teleSet);
		if(existTele.size()>0)
			errorInfo.add("手机号码"+existTele.toString()+"已存在");
		
		if(errorInfo.size() != 0)
			return new ReturnValueVo(ReturnValueVo.ERROR, errorInfo);
		
		try{
			dao.saveBatchEntity(personList,contractorList);
		}catch(Exception e){
			e.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "保存数据出错请联系管理员");
		}
		return new ReturnValueVo(ReturnValueVo.SUCCESS,null);
	}
	
	@Transactional
	public void save(Person p,Contractor c) {
		p.getBasicUser().setPassword(defaultPassword);
		p.getBasicUser().setRoleId(3);
		p.getBasicUser().setState((byte)0);
		p.setAuditState((byte)0);
		personDao.persist(p);
		
		c.setPersonId(p.getId());
		dao.save(c);
	}
	
	@Transactional
	public void update(Person p,Contractor c) {
		p.getBasicUser().setRoleId(3);
		if(p.getAuditState()==null)
			p.setAuditState((byte)0);
		personDao.merge(p);
		dao.update(c);
	}
	
	/**根据ID获取快产专家DTO，快产专家信息同时包括Person信息和自身信息*/
	public ContractorDto getById(int id){
		Person person = personDao.findById(id);
//		person.getBasicUser().setPassword(null);//不允许返回password
		Contractor contractor = dao.findById(id);
		ContractorDto dto = new ContractorDto(person, contractor);
		return dto;
	}
	
	public BootTablePageDto<ContractorSimpleVo> search(Long province,Long city,Long county,Long town,
			Integer[] costumeCodes, Byte processYear, int offset,int limit,Long total){
		BootTablePageDto<ContractorSimpleVo> result = dao.search(province, city, county, town, costumeCodes, processYear, offset, limit, total);
		if(result.getTotal()!=0){
			List<ContractorSimpleVo> list = result.getRows();
			for(int i=0;i<list.size();i++){
				ContractorSimpleVo c = list.get(i);
				List<String> names = districtBiz.getNameByCode(c.getProvince(), c.getCity(), c.getCounty(), c.getTown());
				StringBuilder sb = new StringBuilder();
				int times = 2;//选了区县、镇乡则显示，不选择则不显示
				if(county!=null)
					times = 3;
				if(town!=null)
					times = 4;
				for(int j=0;j<names.size() && j<times;j++){
					sb.append(names.get(j));
				}
				c.setDistrict(sb.toString());
			}
		}
		return result;
	}
	
	public BootTablePageDto<ContractorVo> findByPage(String userName,Long telephone,Byte auditState,Date beginDate,Date endDate,int offset, int limit, Long total){
		return dao.findByPage(userName, telephone, auditState, beginDate, endDate, offset, limit, total);
	}
	
	@Override
	public void update(Contractor entity) {
		dao.saveOrUpdate(entity);
	}

	@Override
	public void deleteById(Integer id) {
		super.deleteById(id);
		personDao.deleteById(id);
	}

	/**首页快产人才展示
	 * @return 性别、年龄、工龄、员工人数、地址（省、市）
	 * */
	public List<ContractorHomeVo> getHomeList(){
		List<ContractorHomeVo> list = dao.getHomeList();
		for(int i=0;i<list.size();i++){
			ContractorHomeVo c = list.get(i);
			List<String> names = districtBiz.getNameByCode(c.getProvince(), c.getCity(), null, null);
			StringBuilder sb = new StringBuilder();
			for(int j=0;j<names.size();j++){
				sb.append(names.get(j));
			}
			c.setDistrict(sb.toString());
		}
		return list;
	}
	
	/**快产人才主页用，推荐快产团队
	 * 获取前工人数前10的快产团队
	 * */
	public List<ContractorHomeVo> getRecommend(){
		return dao.getRecommend();
	}
	
	/**设置地区编码信息
	 * @return errorInfo
	 * */
	private List<String> setDistrictCode(int rowNum, UserAbstract userAbstract, String province, String city, String county, String town,
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
		userAbstract.setProvince(provinceCode);
		
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
		userAbstract.setCity(cityCode);
		
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
		userAbstract.setCounty(countyCode);
		
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
		userAbstract.setTown(townCode);
		return errorInfo;
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
	private String getCostumeCategory(String costume, HashMap<String,Integer> costumeMap){
		StringBuilder sb = new StringBuilder();
		String[] costumeAry = costume.split("，");	//中文逗号分割
		//不能多余两个
		if(costumeAry.length > 2)
			return "";
		Integer costumeCode = costumeMap.get(costumeAry[0]);
		if(costumeCode != null)
			sb.append(costumeCode);
		for(int i=1; i<costumeAry.length; i++){
			costumeCode = costumeMap.get(costumeAry[i]);
			if(costumeCode != null)
				sb.append(","+costumeCode);
		}
		return sb.toString();
	}
}
