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

import com.biz.basic.DistrictBiz;
import com.common.BaseCtrl;
import com.po.basic.District;
import com.util.MicroOfficeFile;

@Controller
@RequestMapping("district")
public class DistrictCtrl extends BaseCtrl<DistrictBiz,Integer,District>{
	@Autowired
	private DistrictBiz districtBiz;
	@RequestMapping("uploadExcel")
	@ResponseBody
	public Object uploadExcel(@RequestParam("file")MultipartFile file,HttpSession httpSession){
		//String aa=file.getContentType();//application/vnd.ms-excel
		//String bb=file.getName();//file
		String fileName=file.getOriginalFilename();//江苏省-320000.xls 获取省编号已经名称
	    String[] tempStrings=fileName.split("\\.");
	    String provinceNameCode=tempStrings[0];
	    String[] tempStrings2=provinceNameCode.split("-");
	    String provinceName=tempStrings2[0];
	    String provinceCode=tempStrings2[1];
	    
		MicroOfficeFile mof = new MicroOfficeFile();
		Workbook wb = mof.readExcel(file);
		List<String[]> data = mof.getAllData(wb,0);
		return districtBiz.batchSaveDistrict(provinceName,provinceCode,data);
	}
}
