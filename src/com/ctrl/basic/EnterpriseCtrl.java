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
	
	@RequestMapping("uploadExcel")
	@ResponseBody
	public Object uploadExcel(@RequestParam("file")MultipartFile file,HttpSession httpSession){
		try{
			MicroOfficeFile mof = new MicroOfficeFile();
			Workbook wb = mof.readExcel(file);
			List<String[]> data = mof.getAllData(wb,0);
			User loginUser = (User)httpSession.getAttribute("loginUser");
		    enterpriseBiz.batchSaveEnterprise(data.subList(2, data.size()),1);
		    return "上传成功";
		}catch(Exception ex){
			ex.printStackTrace();
			return "上传失败";
		}
		
	}
}
