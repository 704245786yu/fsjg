package com.ctrl.basic;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.biz.basic.EnterpriseBiz;
import com.common.BaseCtrl;
import com.po.basic.Enterprise;
import com.sys.ctrl.UserCtrl;
import com.sys.po.User;
import com.util.MicroOfficeFile;

@Controller
@RequestMapping("enterprise")
public class EnterpriseCtrl extends BaseCtrl<EnterpriseBiz,Integer,Enterprise>{

	@Autowired
	private EnterpriseBiz enterpriseBiz;
	
	public EnterpriseCtrl(){
		defaultPage = "backstage/enterprise";
	}
	
	/**批量导入工厂信息
	 * */
	@RequestMapping("uploadExcel")
	@ResponseBody
	public Integer uploadExcel(@RequestParam("file")MultipartFile file,HttpSession session){
		try{
			MicroOfficeFile mof = new MicroOfficeFile();
			Workbook wb = mof.readExcel(file);
			List<String[]> data = mof.getAllData(wb,0);
			User loginUser = UserCtrl.getLoginUser(session);
		    enterpriseBiz.batchSaveEnterprise(data.subList(2, data.size()),loginUser.getId());
		    return 1;	//返回中文乱码
		}catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
		
	}
}
