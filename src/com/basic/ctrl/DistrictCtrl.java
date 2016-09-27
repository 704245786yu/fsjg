package com.basic.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.basic.biz.DistrictBiz;
import com.basic.po.District;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.common.vo.ValidVo;
import com.sys.ctrl.UserCtrl;
import com.sys.po.User;
import com.util.MicroOfficeFile;

@Controller
@RequestMapping("district")
public class DistrictCtrl extends BaseCtrl<DistrictBiz,Long,District>{
	
	public DistrictCtrl(){
		defaultPage = "backstage/district";
	}
	
	/**@author 裘俊宏
	 * =========支煜修改2016-05-28=========
	 * @return ReturnValueVo errorRowNum 数据有问题的行号
	 * */
	@RequestMapping("uploadExcel")
	@ResponseBody
	public ReturnValueVo uploadExcel(@RequestParam("file") MultipartFile file, HttpSession session){
		//String aa=file.getContentType();//application/vnd.ms-excel
		//String bb=file.getName();//file
		ReturnValueVo returnValue = new ReturnValueVo();
		try{
			String fileName=file.getOriginalFilename();//江苏省-320000.xls 获取省编号以及名称
		    String[] tempStrings=fileName.split("\\.");
		    String provinceNameCode=tempStrings[0];
		    String[] tempStrings2=provinceNameCode.split("-");
		    String provinceName=tempStrings2[0];
		    String provinceCode=tempStrings2[1];
		    
			MicroOfficeFile mof = new MicroOfficeFile();
			Workbook wb = mof.readExcel(file);
			List<String[]> data = mof.getAllData(wb,0);
			User loginUser = UserCtrl.getLoginUser(session);
			ArrayList<Integer> errorRowNum = biz.batchSaveDistrict(provinceName,provinceCode,data,loginUser.getId());
			if(errorRowNum == null){
				returnValue.setStatus(ReturnValueVo.SUCCESS);
			}else{
				returnValue.setStatus(ReturnValueVo.ERROR);
				returnValue.setValue(errorRowNum);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			returnValue.setStatus(ReturnValueVo.EXCEPTION);
		}
		return returnValue;
	}
	
	/**根据父节点获取所有子节点信息
	 * @param pCode 可为null，表示获取省级信息
	 * @return List<District> districtCode districtName
	 * */
	@RequestMapping("getByParent")
	@ResponseBody
	public List<District> getByParent(Long pCode){
		return biz.getNameAndCodeByPcode(pCode);
	}
	
	/**获取省市级联信息
	 * */
	@RequestMapping("getCascade")
	@ResponseBody
	public Map<String, List<District>> getCascade(long province, long city, long county){
		List<District> cityList = biz.getNameAndCodeByPcode(province);
		List<District> countyList = biz.getNameAndCodeByPcode(city);
		List<District> townList = biz.getNameAndCodeByPcode(county);
		Map<String, List<District>> map = new HashMap<String, List<District>>();
		map.put("cityList", cityList);
		map.put("countyList", countyList);
		map.put("townList", townList);
		return map;
	}
	
	/**判断地区名是否已经存在
	 * */
	@RequestMapping("nameIsExist")
	@ResponseBody
	public ValidVo nameIsExist(String districtName){
		boolean b = biz.nameIsExist(districtName);
		return new ValidVo(!b);
	}
	
	/**判断地区编码是否已经存在
	 * */
	@RequestMapping("codeIsExist")
	@ResponseBody
	public ValidVo codeIsExist(long districtCode){
		boolean b = biz.codeIsExist(districtCode);
		return new ValidVo(!b);
	}
	
	/**根据地区名称分页查询
	 * @param districtName 地区名称模糊匹配
	 * */
	@RequestMapping("getByNameAndPage")
	@ResponseBody
	public BootTablePageDto<District> getByNameAndPage(String districtName, int offset, int limit){
		return biz.getByNameAndPage(districtName, offset, limit);
	}
}
