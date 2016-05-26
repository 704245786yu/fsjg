package com.biz.basic;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.BaseBiz;
import com.dao.basic.EnterpriseDao;
import com.po.basic.Enterprise;
@Service
public class EnterpriseBiz extends BaseBiz<EnterpriseDao, Integer, Enterprise>{

	private static final String defaultPassword = "123456";
	
	
	public void batchSaveEnterprise(List<String[]> data,Integer userId){
		List<Enterprise> list=new ArrayList<Enterprise>();
		for(int i=0; i<data.size(); i++){
			String[] temp = data.get(i);
			Enterprise enterprise=new Enterprise();
			enterprise.setEnterpriseNumber(temp[0]);
			enterprise.setEnterpriseName(temp[1]);
			enterprise.setLinkman(temp[2]);
			enterprise.setProvince(temp[3]);
			enterprise.setCity(temp[4]);
			enterprise.setCountry(temp[5]);
			enterprise.setTown(temp[6]);
			enterprise.setDetailAddr(temp[7]);
			enterprise.setFixPhone(temp[8]);
			enterprise.setTelephone(temp[9]);
			enterprise.setUserName(temp[9]);
			enterprise.setPassword(defaultPassword);
			enterprise.setQq(Long.parseLong(temp[10]));
			enterprise.setSaleMarket(Byte.parseByte(temp[11]));
			enterprise.setBusinessLicenseUrl(temp[12]);
			enterprise.setOrganizationCode(temp[13]);
			enterprise.setTradeId(Integer.parseInt(temp[14]));
			//15 16加工类型 主营产品数据库无对应
			enterprise.setStaffAmount(Short.parseShort(temp[17]));
			enterprise.setHighSpeedStaffAmount(Short.parseShort(temp[18]));
			enterprise.setOtherStaffAmount(Short.parseShort(temp[19]));
			enterprise.setEnterpriseAge(Short.parseShort(temp[20]));
			enterprise.setEquipmentDesc(temp[21]);
			enterprise.setYield(Integer.parseInt(temp[22]));
			enterprise.setCooperationCustomer(temp[23]);
			enterprise.setWebsiteUrl(temp[24]);
			enterprise.setWechat(temp[25]);
			enterprise.setEmail(temp[26]);
			//是否有企业logo数据库无对应字段
			
			enterprise.setCreateBy(userId);
			enterprise.setAuditState((byte)0);
			list.add(enterprise);
		}
		dao.saveBatch(list);
	}
}