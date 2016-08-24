package com.basic.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.basic.biz.CostumeCategoryBiz;
import com.basic.biz.DistrictBiz;
import com.basic.biz.IndentBiz;
import com.basic.dto.IndentDto;
import com.basic.po.BasicUser;
import com.basic.po.District;
import com.basic.po.Indent;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.sys.biz.ConstantDictBiz;
import com.sys.po.ConstantDict;

@Controller
@RequestMapping("indent")
public class IndentCtrl extends BaseCtrl<IndentBiz,Integer,Indent>{

	@Autowired
	private DistrictBiz districtBiz;
	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	@Autowired
	private ConstantDictBiz constantDictBiz;
	
	private static final long imgMaxSize = 200000;//图片最大200kb
	private static final long docMaxSize = 500000;//文档最大500kb
	
	@Override
	public ModelAndView showDefaultPage(HttpSession session){
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/indent");
		mav.addObject("processTypes", processTypes);
		return mav;
	}
	
	/**显示发布订单页面*/
	@RequestMapping("showRelease")
	public ModelAndView showRelease(){
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/indentRelease");
		mav.addObject("processTypes", processTypes);
		return mav;
	}
	
	/**发布订单前，上传图片用*/
	@RequestMapping("uploadImg")
	@ResponseBody
	public ReturnValueVo uploadImg(@RequestParam("file")MultipartFile file, HttpSession session){
		//检查是否登录
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser == null){
			return  new ReturnValueVo(ReturnValueVo.ERROR, "nologin");
		}
		//判断是否为png/jpeg文件，且检测文件大小
//		String fieldName = file.getName();//获取表单中文件组件的名字
		String fileName = file.getOriginalFilename();//获取上传文件的原名
		String ary[] = fileName.split("\\.");
		String suffix = ary[ary.length-1];
		long size = file.getSize();//获取文件的字节大小，单位为byte
		String contentType = file.getContentType();
//		System.out.println("fieldName:"+fieldName+"||fileName:"+fileName+"||size:"+size);
		if( size>imgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
			return new ReturnValueVo(ReturnValueVo.ERROR, "imageError");
		}
		
		//通过transferTo()将文件存储到硬件中
		try {
			String uploadDir = session.getServletContext().getInitParameter("uploadDir");
			Date date = new Date();
			String newFileName = basicUser.getId()+""+date.getTime()+"."+suffix;
			file.transferTo(new File(uploadDir + newFileName));
			return new ReturnValueVo(ReturnValueVo.SUCCESS, newFileName);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, null);
		}
	}
	
	/**发布订单前，上传文档用*/
	@RequestMapping("uploadDoc")
	@ResponseBody
	public ReturnValueVo uploadDoc(@RequestParam("file")MultipartFile file, HttpSession session){
		//检查是否登录
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser == null){
			return  new ReturnValueVo(ReturnValueVo.ERROR, "nologin");
		}
		
		//判断是否为txt、word、excel文件，且检测文件大小
		String fileName = file.getOriginalFilename();//获取上传文件的原名
		String ary[] = fileName.split("\\.");
		String suffix = ary[ary.length-1];
		long size = file.getSize();//获取文件的字节大小，单位为byte
		if( size>docMaxSize || (!suffix.equals("txt") && !suffix.equals("doc") && !suffix.equals("docx") && !suffix.equals("xls") && !suffix.equals("xlsx")) ){
			return new ReturnValueVo(ReturnValueVo.ERROR, "docError");
		}
		
		//通过transferTo()将文件存储到硬件中
		try {
			String uploadDir = session.getServletContext().getInitParameter("uploadDir");
			Date date = new Date();
			String newFileName = basicUser.getId()+""+date.getTime()+"."+suffix;
			file.transferTo(new File(uploadDir + newFileName));
			return new ReturnValueVo(ReturnValueVo.SUCCESS, newFileName);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, null);
		}
	}
	
	/**发布订单*/
	@RequestMapping(value="release",method=RequestMethod.POST)
	public ModelAndView release(Indent indent,HttpSession session){
		//检查是否登录
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser == null){
			return new ModelAndView("redirect:../login.jsp");
		}
		indent.setIndentNum(new Date().getTime());
		indent.setState((byte)0);
		indent.setCreateBy(basicUser.getId());
		indent.setCreateUserType(basicUser.getRoleId().byteValue());
		biz.save(indent);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/indentReleaseSuccess");
		return mav;
	}
	
	/**搜索订单，不能包括订单状态为2(已接单)和3(已失效)的订单
	 * */
	@RequestMapping(value="search2",method=RequestMethod.POST)
	public ModelAndView search2(Long province,Long city,Long county,Long town, Integer[] costumeCode, String keyword){
		BootTablePageDto<IndentDto> result = biz.search2(province,city,county,town,costumeCode,keyword);
		HashMap<Integer,String> costumeCategoryMap = costumeCategoryBiz.getAllCodeNameMap();
		List<ConstantDict> processTypes = constantDictBiz.findByConstantTypeCode("process_type");
		List<District> districts =	districtBiz.getProvinceAndCity();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.addObject("costumeCategoryMap", costumeCategoryMap);
		mav.addObject("processTypes", processTypes);
		mav.addObject("districts", districts);
		mav.setViewName("main/indentList");
		System.out.println("长度："+result.getRows().size());
		return mav;
	}
	
	@RequestMapping("detail/{indentNum}")
	public ModelAndView detail(@PathVariable Long indentNum, HttpSession session){
		Indent indent = biz.getByNum(indentNum);
		//产品类型
		String[] costumeCodeStr = indent.getCostumeCode().split(",");
		List<Integer> costumeCodes = new ArrayList<Integer>();
		for(int i=0; i<costumeCodeStr.length; i++){
			costumeCodes.add(Integer.valueOf(costumeCodeStr[i]));
		}
		List<String> costumes = costumeCategoryBiz.getNameByCode(costumeCodes);
		
		//加工类型
		Byte processType = indent.getProcessType();
		ConstantDict dict = new ConstantDict();
		dict.setConstantTypeCode("process_type");
		dict.setConstantValue(processType.toString());
		List<ConstantDict> list = constantDictBiz.findByExample(dict);
		String processTypeStr = "";
		if(list.size() == 1)
			processTypeStr = list.get(0).getConstantName();
		
		//接单地区要求
		List<Long> districtCode = new ArrayList<Long>();
		districtCode.add(indent.getCondProvince());
		districtCode.add(indent.getCondCity());
		List<String> districts = districtBiz.getNameByCode(districtCode);

		ModelAndView mav = new ModelAndView("main/indentDetail");
		mav.addObject("indent", indent);
		mav.addObject("costumes", costumes);
		mav.addObject("districts", districts);
		mav.addObject("processType", processTypeStr);

		if(BasicUserCtrl.getLoginUser(session) != null){
			//发单用户所在地
			int userId = indent.getCreateBy();
			int userType = indent.getCreateUserType();
			
		}
		return mav;
	}
}
