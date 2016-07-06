package com.basic.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.dao.EnterpriseDao;
import com.basic.po.BasicUser;
import com.basic.po.Enterprise;
import com.common.BaseBiz;
import com.common.vo.ReturnValueVo;
import com.sys.biz.ConstantDictBiz;

@Service
public class EnterpriseBiz extends BaseBiz<EnterpriseDao, Integer, Enterprise>{
	
	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	@Autowired
	private ConstantDictBiz constantDictBiz;

	private static final String defaultPassword = "123456";
	
	public ReturnValueVo batchSaveEnterprise(List<String[]> data,Integer userId){
		HashMap<String,Integer> tradeMap = costumeCategoryBiz.getChildCostumeMap(null);	//行业类型为服饰类型的一级分类
		HashMap<String,Integer> costumeMap = costumeCategoryBiz.getAllNameCodeMap();	//服饰类型
		HashMap<String,String> processTypeMap = constantDictBiz.getNameValueMap("process_type");	//加工类型
		
		List<String> nameList = new ArrayList<String>();	//去重用
		List<Long> teleList = new ArrayList<Long>();	//去重用
		List<Enterprise> enterpriseList = new ArrayList<Enterprise>();	//要保存的企业信息
		List<String> errorInfo = new ArrayList<String>();	//错误信息
		int rowOffset = 3;	//行偏移量
		
		for(int i=0; i<data.size(); i++){
			String[] temp = data.get(i);
			//创建BasicUser
			BasicUser basicUser = new BasicUser();
			try{
				String teleStr = temp[8];
				if(teleStr.length() != 11)
					throw new Exception("telephone length != 11");
				Long telephone = Long.parseLong(teleStr);
				basicUser.setTelephone(telephone);
				basicUser.setPassword(defaultPassword);
				basicUser.setRoleId(2);
			}catch(Exception e){
				e.printStackTrace();
				errorInfo.add("第"+(i+rowOffset)+"行 手机号码非11位数字");
			}
			
			//创建Enterprise
			Enterprise enterprise=new Enterprise();
//			enterprise.setEnterpriseNumber(temp[0]);
			//企业名称
			nameList.add(temp[0]);
			enterprise.setEnterpriseName(temp[0]);
			//联系人
			enterprise.setLinkman(temp[1]);
			//省，市，区县，镇/乡/街道
//			enterprise.setProvince(temp[2]);
//			enterprise.setCity(temp[3]);
//			enterprise.setCountry(temp[4]);
//			enterprise.setTown(temp[5]);
			//详细地址
			enterprise.setDetailAddr(temp[6]);
			//固定电话
			enterprise.setFixPhone(temp[7]);
			//QQ
			String qqStr = temp[9];
			if(qqStr != null)
				enterprise.setQq(Long.parseLong(qqStr));
			//销售市场
//			enterprise.setSaleMarket(Byte.parseByte(temp[10]));
			//营业执照
//			enterprise.setBusinessLicenseImg(temp[11]);
			//组织机构代码
			enterprise.setOrganizationCode(temp[12]);
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
			//15 16加工类型 主营产品数据库无对应
//			enterprise.setStaffAmount(Short.parseShort(temp[17]));
//			enterprise.setHighSpeedStaffAmount(Short.parseShort(temp[18]));
//			enterprise.setOtherStaffAmount(Short.parseShort(temp[19]));
//			enterprise.setEnterpriseAge(Short.parseShort(temp[20]));
			enterprise.setEquipmentDesc(temp[21]);
//			enterprise.setYield(Integer.parseInt(temp[22]));
//			enterprise.setCooperationCustomer(temp[23]);
			enterprise.setWebsiteUrl(temp[24]);
			enterprise.setWechat(temp[25]);
			enterprise.setEmail(temp[26]);
			//是否有企业logo数据库无对应字段
//			enterprise.setCreateBy(userId);
			enterprise.setAuditState((byte)0);
//			list.add(enterprise);
		}
//		dao.saveBatch(list);
		return null;
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
