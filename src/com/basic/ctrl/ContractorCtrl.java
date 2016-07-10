package com.basic.ctrl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.basic.biz.ContractorBiz;
import com.basic.dto.ContractorDto;
import com.basic.po.Contractor;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.sys.biz.ConstantDictBiz;
import com.sys.po.ConstantDict;
import com.sys.po.User;
import com.util.MicroOfficeFile;

@Controller
@RequestMapping("contractor")
public class ContractorCtrl extends BaseCtrl<ContractorBiz, Integer, Contractor> {

	@Autowired
	private ConstantDictBiz constantDictBiz;
	
	public ContractorCtrl(){
		defaultPage = "backstage/contractor/contractor";
	}
	
	/**后台快产专家管理页面
	 * */
	@RequestMapping("backstage")
	public ModelAndView showBackstageManage(){
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		ModelAndView mav = new ModelAndView();
		mav.addObject("processTypes", processTypes);
		mav.setViewName("backstage/contractor/contractor");
		return mav;
	}

	@RequestMapping("uploadExcel")
	@ResponseBody
	public Integer uploadExcel(@RequestParam("files")MultipartFile file,HttpSession httpSession){
		try{
			MicroOfficeFile mof = new MicroOfficeFile();
			Workbook wb = mof.readExcel(file);
			List<String[]> data = mof.getAllData(wb,0);
			User loginUser = (User)httpSession.getAttribute("loginUser");
			biz.batchSaveContractor(data.subList(2, data.size()),1);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/***/
	@RequestMapping("saveData")
	@ResponseBody
	public Contractor saveData(@RequestParam("files")MultipartFile[] files, Contractor contractor,HttpSession httpSession){
		for(int i=0; i<files.length; i++){
			System.out.println(files[i].getOriginalFilename());
		}
		return null;
	}
	
	@Override
	public Contractor update(Contractor contractor,HttpSession httpSession){
		biz.update(contractor);
		return contractor;
	}
	
	/**根据ID获取快产专家DTO，快产专家信息同时包括Person信息和自身信息*/
	@RequestMapping("getById/{id}")
	@ResponseBody
	public ContractorDto getById(@PathVariable int id){
		return biz.getById(id);
	}
	
	/**分页查询
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页需要显示的记录数
	 * @return 返回contractor的部分属性，以及Person的realName属性
	 * */
	@RequestMapping("findByPageAndParams")
	@ResponseBody
	public BootTablePageDto<Map<String,Object>> findByPageAndParams(int offset, int limit){
		return biz.findByPageAndParams(offset,limit);
	}
}