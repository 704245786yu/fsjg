package com.basic.ctrl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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

import com.ad.biz.AdPositionBiz;
import com.ad.po.AdPosition;
import com.basic.biz.BasicUserBiz;
import com.basic.biz.CostumeCategoryBiz;
import com.basic.biz.DistrictBiz;
import com.basic.biz.EnterpriseBiz;
import com.basic.biz.EnterpriseCostumeRelaBiz;
import com.basic.po.BasicUser;
import com.basic.po.Enterprise;
import com.basic.vo.AuthEnterpriseVo;
import com.basic.vo.StrengthEnterpriseVo;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.common.vo.ValidVo;
import com.sys.ctrl.UserCtrl;
import com.sys.po.User;
import com.util.DateTransform;
import com.util.FileUtil;
import com.util.JacksonJson;
import com.util.MicroOfficeFile;

@Controller
@RequestMapping("enterprise")
public class EnterpriseCtrl extends BaseCtrl<EnterpriseBiz,Integer,Enterprise>{

	@Autowired
	private CostumeCategoryBiz costumeCategoryBiz;
	@Autowired
	private BasicUserBiz basicUserBiz;
	@Autowired
	private EnterpriseCostumeRelaBiz enterpriseCostumeRelaBiz;
	@Autowired
	private DistrictBiz districtBiz;
	@Autowired
	private AdPositionBiz adPositionBiz;
	
	private static final long logoImgMaxSize = 50000;//logo图片最大50kb
	private static final long imgMaxSize = 200000;//文档最大200kb
	
	public EnterpriseCtrl(){
		defaultPage = "main/enterprise";
	}
	
	public ModelAndView showDefaultPage(HttpSession session){
		//实力工厂
		List<StrengthEnterpriseVo> enterprises = biz.getStrength(12);
		ModelAndView mav = new ModelAndView(defaultPage);
		mav.addObject("strengths",enterprises);
		//认证工厂
		List<AuthEnterpriseVo> authEnters = biz.getNewAuth(6,false);
		mav.addObject("auths",authEnters);
		//广告位
		List<AdPosition> adPositions = adPositionBiz.getByCode("enterprise_main");
		mav.addObject("adPositions", JacksonJson.beanToJson(adPositions));
		return mav;
	}
	
	/**显示后台管理页面*/
	@RequestMapping("showManage")
	public ModelAndView showManage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backstage/enterprise/enterprise");
		return mav;
	}
	
	/**根据搜索条件分页查询数据。
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * @param total 可为null
	 * */
	@RequestMapping("findByPage")
	@ResponseBody
	public BootTablePageDto<Enterprise> findByPage(String enterpriseName,Byte auditState,Integer createType,String beginDate,String endDate,int offset, int limit, Long total){
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length()>0 && endDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		return biz.findByPage(enterpriseName,auditState,createType,beginTime,endTime,offset,limit,total);
	}
	
	/**批量导入工厂信息
	 * */
	@RequestMapping("uploadExcel")
	@ResponseBody
	public ReturnValueVo uploadExcel(@RequestParam("file")MultipartFile file,HttpSession session){
		try{
			MicroOfficeFile mof = new MicroOfficeFile();
			Workbook wb = mof.readExcel(file);
			List<String[]> data = mof.getAllData(wb,0,29);
			if(data.size() < 4){
				List<String> errorInfo = new ArrayList<String>();
				errorInfo.add("读取文件出错，文件内容可能有误");
				return new ReturnValueVo(ReturnValueVo.ERROR,errorInfo);
			}
			User loginUser = UserCtrl.getLoginUser(session);
		    return biz.batchSaveEnterprise(data.subList(3, data.size()),loginUser.getId());
		}catch(Exception e){
			e.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION,"读取文件发生错误，请与管理员联系");
		}
	}
	
	/**获取加工企业的主营产品类型*/
	@RequestMapping("getCostumeCode")
	@ResponseBody
	public List<Integer> getCostumeCode(int enterpriseId){
		return biz.getCostumeCode(enterpriseId);
	}
	
	/**获取优秀企业*/
	@RequestMapping("getExcellent")
	@ResponseBody
	public List<Enterprise> getExcellent(){
		return biz.getExcellent();
	}
	
	/**最新入住的企业*/
	@RequestMapping("getNewest")
	@ResponseBody
	public List<Enterprise> getNewest(){
		return biz.getNewest(10);//获取10个工厂
	}
	
	/**最新认证加工厂*/
	@RequestMapping("getNewAuth")
	@ResponseBody
	public List<AuthEnterpriseVo> getNewAuth(){
		return biz.getNewAuth(10,true);//获取10个工厂
	}
	
	/**首页服饰分类链接显示工厂列表*/
	@RequestMapping("showList")
	public ModelAndView showList(Integer costumeCode,String enterpriseKeyword){
		ModelAndView mav = new ModelAndView("main/enterpriseList");
		List<Integer> costumeCodes = costumeCategoryBiz.getSubCode(costumeCode);
		int endIndex = costumeCodes.size()>3 ? 3 : costumeCodes.size();
		//获取子类型
		BootTablePageDto<Enterprise> result = biz.search(null,null,null,null,costumeCodes.subList(0, endIndex).toArray(new Integer[]{}),null, null,enterpriseKeyword,0,20,null);
		mav.addObject("result", result);
		
		//广告位
		List<AdPosition> adPositions = adPositionBiz.getByCode("enterprise_list");
		mav.addObject("adPositions", JacksonJson.beanToJson(adPositions));

		//保留页面顶部搜索框的状态
		mav.addObject("tabIndex",1);
		mav.addObject("enterpriseKeyword",enterpriseKeyword);
		mav.addObject("costumeCode",costumeCode);
		return mav;
	}
	
	/**工厂搜索,返回enterpriseList页面
	 * */
	@RequestMapping(value="search")
	public ModelAndView search(Long province,Long city,Long county,Long town, 
			Integer[] costumeCode, String enterpriseKeyword){
		//针对首页服饰分类链接的请求
		if(enterpriseKeyword==null)
			enterpriseKeyword = "";
		BootTablePageDto<Enterprise> result = biz.search(province,city,county,town,costumeCode,null, null,enterpriseKeyword,0,20,null);
		
		ModelAndView mav = new ModelAndView("main/enterpriseList");
		mav.addObject("result", result);
		
		//热门区域 如果通过城市点击进来，需获取所在省，以便工厂列表进行翻页
		if(city!=null && province==null){
			province = districtBiz.getPcode(city);
		}
		mav.addObject("province", province);
		mav.addObject("city", city);
		
		//广告位
		List<AdPosition> adPositions = adPositionBiz.getByCode("enterprise_list");
		mav.addObject("adPositions", JacksonJson.beanToJson(adPositions));
		
		//保留页面顶部搜索框的状态
		mav.addObject("tabIndex",1);
		mav.addObject("enterpriseKeyword",enterpriseKeyword);
		return mav;
	}
	
	/**工厂搜索,异步访问用
	 * @param offset
	 * @param total 可为null
	 * */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="search2")
	@ResponseBody
	public BootTablePageDto<Enterprise> search2(HttpServletRequest request, Long province,Long city,Long county,Long town, 
			Integer[] costumeCode, Integer processType, Integer staffNumber, String enterpriseKeyword,int offset,Long total){
		int limit = 20;
		BootTablePageDto<Enterprise> result = biz.search(province,city,county,town,costumeCode,processType, staffNumber,enterpriseKeyword,offset,limit,total);
		List<Enterprise> list = result.getRows();
		ServletContext servletContext=request.getSession().getServletContext();
		HashMap<Long,String> districtCodeNameMap = (HashMap<Long,String>)servletContext.getAttribute("districtCodeNameMap");
		for(int i=0; i<list.size(); i++){
			Enterprise e = list.get(i);
			String provinceStr = districtCodeNameMap.get(e.getProvince());
			provinceStr = provinceStr==null ? "" : provinceStr;
			String cityStr = districtCodeNameMap.get(e.getCity());
			cityStr = cityStr==null ? "" : cityStr;
			String countyStr = districtCodeNameMap.get(e.getCounty());
			countyStr = countyStr==null ? "" : countyStr;
			String townStr = districtCodeNameMap.get(e.getTown());
			townStr = townStr==null ? "" : townStr;
			e.setDetailAddr(provinceStr+" "+cityStr+" "+countyStr+" "+townStr);
		}
		return result;
	}
	
	/**工厂详情页*/
	@RequestMapping("showDetail/{id}")
	public ModelAndView showDetail(@PathVariable int id){
		ModelAndView mav = new ModelAndView("main/enterpriseDetail");
		Enterprise e = biz.getById(id);
		List<String> costumeNames = enterpriseCostumeRelaBiz.getCostumeNameByEnterpriseId(e.getId());
		List<String> districts = districtBiz.getNameByUser(e);
		mav.addObject("enterprise", e);
		mav.addObject("costumeNames", costumeNames);
		mav.addObject("districts",districts);
		
		//获取可能感兴趣的工厂
		List<Integer> costumeCodes = enterpriseCostumeRelaBiz.getCostumeCode(e.getId());
		//企业可能未设置主营产品
		if(costumeCodes.size()>0){
			List<Enterprise> enterpriseList = biz.getByCostumeCode(costumeCodes.get(0),1,3).getRows();
			List<List<String>> costumeNamesList = new ArrayList<List<String>>(enterpriseList.size());
			List<List<String>> disctricsList = new ArrayList<List<String>>(enterpriseList.size());
			for(int i=0; i<enterpriseList.size(); i++){
				Enterprise temp = enterpriseList.get(i);
				List<String> costumeNamesTemp = enterpriseCostumeRelaBiz.getCostumeNameByEnterpriseId(temp.getId());
				costumeNamesList.add(costumeNamesTemp);
				List<String> districtsTemp = districtBiz.getNameByCode(temp.getProvince(), temp.getCity(), null, null);
				disctricsList.add(districtsTemp);
			}
			mav.addObject("enterpriseList", enterpriseList);
			mav.addObject("costumeNamesList", costumeNamesList);
			mav.addObject("disctricsList", disctricsList);
		}
		return mav;
	}
	
	/**@param enterprisePic 最多选择6张图片
	 * */
	@RequestMapping("saveEnterprise")
	@ResponseBody
	public ReturnValueVo saveEnterprise(
			Enterprise e,
			@RequestParam(value="logoImg",required=false)MultipartFile logoImg,
			@RequestParam(value="licensePic",required=false)MultipartFile licensePic,
			@RequestParam(value="enterprisePic",required=false)MultipartFile[] enterprisePic,
			HttpSession session){
		//检查是否登录,判断操作用户是管理员还是普通用户自己
		Integer createBy = null;
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser != null){
			createBy = basicUser.getId();
		}else{
			User user = UserCtrl.getLoginUser(session);
			if(user!=null)
				createBy = user.getId();
		}
		if(createBy==null){
			return new ReturnValueVo(ReturnValueVo.ERROR, "请先登录");
		}
		e.getBasicUser().setCreateBy(createBy);
		
		//验证用户名、工厂名称、手机号码是否相同
		String errorMsg = this.isExistField(e);
		if(errorMsg.length() > 0)
			return new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		//验证文件类型、大小是否符合
		errorMsg = this.verifyImg(logoImg,licensePic,enterprisePic);
		if(errorMsg.length() > 0)
			return new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		//转储图片
		//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/enterprise/").getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
//		String uploadDir = session.getServletContext().getInitParameter("uploadDir/enterprise");
		try{
			if(logoImg != null)
				e.setLogo(this.transferFile(logoImg,uploadDir,createBy,"logo"));
			else
				e.setLogo("default_logo.png");//设置默认图片
			
			if(licensePic != null)
				e.setLicenseImg(this.transferFile(licensePic,uploadDir,createBy,"lic"));
			
			if(enterprisePic.length > 0){
				String enterpriseImg = null;
				enterpriseImg = this.transferFile(enterprisePic[0],uploadDir,createBy,"0pic");
				for(int i=1;i<enterprisePic.length;i++){
					enterpriseImg +=  ","+this.transferFile(enterprisePic[i],uploadDir,createBy,i+"pic");
				}
				e.setEnterpriseImg(enterpriseImg);
			}
		} catch (IllegalStateException | IOException ex) {
			ex.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "上传图片出错,请重试");
		}
		biz.save(e);
		return new ReturnValueVo(ReturnValueVo.SUCCESS, e);
	}
	
	/**更新企业用户信息。对于用户自己更改信息的需同时刷新session中的信息
	 * @param enterprisePic 最多选择6张图片
	 * */
	@RequestMapping("updateEnterprise")
	@ResponseBody
	public ReturnValueVo updateEnterprise(
			Enterprise e,
			Byte isAudit,
			String[] delImg,
			@RequestParam(value="logoImg",required=false)MultipartFile logoImg,
			@RequestParam(value="licensePic",required=false)MultipartFile licensePic,
			@RequestParam(value="enterprisePic",required=false)MultipartFile[] enterprisePic,
			HttpSession session){
		//检查是否登录,判断操作用户是管理员还是普通用户自己
		Integer updateBy = null;
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser != null){
			e.getBasicUser().setId(basicUser.getId());//设置普通/企业用户设置自己的Id,防止人为篡改http通信中的id
			updateBy = 0;
		}else{
			User user = UserCtrl.getLoginUser(session);
			if(user!=null)
				updateBy = user.getId();
		}
		if(updateBy==null){
			return  new ReturnValueVo(ReturnValueVo.ERROR, "请先登录");
		}
		e.getBasicUser().setUpdateBy(updateBy);
		e.setId(biz.getIdByUserId(e.getBasicUser().getId()));//设置普通/企业用户设置自己的Id,防止人为篡改http通信中的id
		
		//验证用户名、工厂名称、手机号码是否相同
		String errorMsg = this.isExistField(e);
		if(errorMsg.length() > 0)
			return new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		//验证文件类型、大小是否符合
		errorMsg = this.verifyImg(logoImg,licensePic,enterprisePic);
		if(errorMsg.length() > 0)
			return  new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/enterprise/").getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		//删除旧图片
		if(delImg!=null){
			FileUtil.delImg(uploadDir, delImg);
			for(int i=0; i<delImg.length; i++){
				if(delImg[i].indexOf("logo") != -1){
					e.setLogo("default_logo.png");
				}
			}
		}
		
		//转储图片
		try{
			if(logoImg != null)
				e.setLogo(this.transferFile(logoImg,uploadDir,updateBy,"logo"));
			
			if(licensePic != null)
				e.setLicenseImg(this.transferFile(licensePic,uploadDir,updateBy,"lic"));
			
			if(enterprisePic.length > 0){
				String enterpriseImg = null;
				enterpriseImg = this.transferFile(enterprisePic[0],uploadDir,updateBy,"0pic");
				for(int i=1;i<enterprisePic.length;i++){
					enterpriseImg +=  ","+this.transferFile(enterprisePic[i],uploadDir,updateBy,i+"pic");
				}
				if(e.getEnterpriseImg().length() > 0)
					e.setEnterpriseImg(e.getEnterpriseImg()+','+enterpriseImg);
				else
					e.setEnterpriseImg(enterpriseImg);
			}
		} catch (IllegalStateException | IOException ex) {
			ex.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "上传图片出错,请重试");
		}
		biz.update(e,isAudit);
		
		//用户修改自身信息成功后，更新session
		if(basicUser!=null){
			basicUser.setUserName(e.getBasicUser().getUserName());
			basicUser.setTelephone(e.getBasicUser().getTelephone());
		}
		return new ReturnValueVo(ReturnValueVo.SUCCESS, e);
	}
	
	/**@param enterprisePic 最多选择6张图片
	 * */
	@RequestMapping("updateMyInfo")
	@ResponseBody
	public ReturnValueVo updateMyInfo(
			Enterprise e,
			String[] delImg,
			@RequestParam(value="logoImg",required=false)MultipartFile logoImg,
			@RequestParam(value="licensePic",required=false)MultipartFile licensePic,
			@RequestParam(value="enterprisePic",required=false)MultipartFile[] enterprisePic,
			HttpSession session){
		//检查是否登录
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser == null)
			new ReturnValueVo(ReturnValueVo.ERROR, "请先登录");
		
		e.getBasicUser().setId(basicUser.getId());//设置id为当前登录者的id
		e.getBasicUser().setUpdateBy(0);
		
		//验证用户名、工厂名称、手机号码是否相同
		String errorMsg = this.isExistField(e);
		if(errorMsg.length() > 0)
			return new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		//验证文件类型、大小是否符合
		errorMsg = this.verifyImg(logoImg,licensePic,enterprisePic);
		if(errorMsg.length() > 0)
			return  new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/enterprise/").getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		//删除旧图片
		if(delImg!=null){
			FileUtil.delImg(uploadDir, delImg);
			for(int i=0; i<delImg.length; i++){
				if(delImg[i].indexOf("logo") != -1){
					e.setLogo("default_logo.png");
				}
			}
		}
		
		//转储图片
		try{
			if(logoImg != null)
				e.setLogo(this.transferFile(logoImg,uploadDir,0,"logo"));
			
			if(licensePic != null)
				e.setLicenseImg(this.transferFile(licensePic,uploadDir,0,"lic"));
			
			if(enterprisePic.length > 0){
				String enterpriseImg = null;
				enterpriseImg = this.transferFile(enterprisePic[0],uploadDir,0,"0pic");
				for(int i=1;i<enterprisePic.length;i++){
					enterpriseImg +=  ","+this.transferFile(enterprisePic[i],uploadDir,0,i+"pic");
				}
				if(e.getEnterpriseImg().length() > 0)
					e.setEnterpriseImg(e.getEnterpriseImg()+','+enterpriseImg);
				else
					e.setEnterpriseImg(enterpriseImg);
			}
		} catch (IllegalStateException | IOException ex) {
			ex.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "上传图片出错,请重试");
		}
		biz.update(e);
		return new ReturnValueVo(ReturnValueVo.SUCCESS, e);
	}
	
	@Override
	public Integer delete(@PathVariable Integer id, HttpSession session) {
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/enterprise/").getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		try{
			biz.deleteById(id, uploadDir);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	/**校验企业名称是否存在*/
	@RequestMapping(value="isNameExist")
	@ResponseBody
	public ValidVo enterpriseIsExist(String enterpriseName, HttpSession session){
		BasicUser b = BasicUserCtrl.getLoginUser(session);
		int id = biz.getIdByUserId(b.getId());
		return new ValidVo( ! biz.isExsit(enterpriseName,id));
	}
	
	@RequestMapping(value="getNames")
	@ResponseBody
	public List<HashMap<String,String>> getNames(String name){
		return biz.getNames(name);
	}
	
	/**保存文件到磁盘
	 * @param srcFile 原文件
	 * @param uploadDir 保存路径
	 * @param createBy 创建人ID
	 * @return 返回新文件名
	 * @throws IOException 
	 * @throws IllegalStateException */
	private String transferFile(MultipartFile srcFile, String uploadDir, int createBy, String newName) throws IllegalStateException, IOException{
		String suffix = null;
		String fileName = srcFile.getOriginalFilename();//获取上传文件的原名
		String ary[] = fileName.split("\\.");
		suffix = ary[ary.length-1];
		//通过transferTo()将文件存储到硬件中
		long time = System.currentTimeMillis();
		String newFileName = createBy+""+time+newName+"."+suffix;
		srcFile.transferTo(new File(uploadDir + newFileName));
		return newFileName;
	}
	
	/**验证图片
	 * */
	private String verifyImg(MultipartFile logoImg,MultipartFile licensePic,MultipartFile[] enterprisePic){
		String errorMsg = "";
		String contentType = null;
		if(logoImg != null){
			contentType = logoImg.getContentType();
			if( logoImg.getSize()>logoImgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
				errorMsg = "工厂logo不符合上传要求";
			}
		}
		if(licensePic != null){
			contentType = licensePic.getContentType();
			if( licensePic.getSize()>imgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
				errorMsg += "营业执照不符合上传要求";
			}
		}
		String enterprisePicError = "";
		for(int i=0;i<enterprisePic.length;i++){
			contentType = enterprisePic[i].getContentType();
			if( enterprisePic[i].getSize()>imgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
				enterprisePicError = "工厂照片不符合上传要求";
			}
		}
		errorMsg += enterprisePicError;
		return errorMsg;
	}
	
	private String isExistField(Enterprise e){
		String errorMsg = "";
		Integer basicUserId = e.getBasicUser().getId();
		if(basicUserBiz.nameIsExist(e.getBasicUser().getUserName(), basicUserId)){
			errorMsg = "用户名已存在";
		}
		if(basicUserBiz.teleIsExist(e.getBasicUser().getTelephone(), basicUserId)){
			errorMsg += " 手机号已存在";
		}
		if(biz.isExsit(e.getEnterpriseName(), e.getId())){
			errorMsg += " 工厂名称已存在";
		}
		return errorMsg;
	}
	
	@Override
	public List<Enterprise> getAll() {
		return null;
	}

	@Override
	public BootTablePageDto<Enterprise> getAllByPage(Long total, int offset,int limit) {
		return null;
	}
	
	
}
