package com.basic.ctrl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.basic.biz.CostumeSampleBiz;
import com.basic.biz.DistrictBiz;
import com.basic.biz.EnterpriseBiz;
import com.basic.biz.EnterpriseCostumeRelaBiz;
import com.basic.po.BasicUser;
import com.basic.po.CostumeSample;
import com.basic.po.Enterprise;
import com.basic.vo.CostumeSampleVo;
import com.basic.vo.Sample2Vo;
import com.basic.vo.SampleVo;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.sys.ctrl.UserCtrl;
import com.sys.po.User;
import com.util.DateTransform;
import com.util.FileUtil;
import com.util.JacksonJson;

@Controller
@RequestMapping("costumeSample")
public class CostumeSampleCtrl extends BaseCtrl<CostumeSampleBiz,Integer,CostumeSample>{

	@Autowired
	private AdPositionBiz adPositionBiz;
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	@Autowired
	private EnterpriseCostumeRelaBiz enterpriseCostumeRelaBiz;
	@Autowired
	private DistrictBiz districtBiz;
	
	private static final long imgMaxSize = 200000;//文档最大200kb
	
	public ModelAndView showDefaultPage(HttpSession session){
		ModelAndView mav = new ModelAndView("main/costumeSample");
		
		BootTablePageDto<SampleVo> result = biz.search(null,null,null,null,null,"",0,20,null);
		mav.addObject("result", result);
		
		//广告位
		List<AdPosition> adPositions = adPositionBiz.getByCode("sample_list");
		mav.addObject("adPositions", JacksonJson.beanToJson(adPositions));
		return mav;
	}
	
	/**显示后台管理页面*/
	@RequestMapping("showManage")
	public ModelAndView showManage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backstage/costumeSample/costumeSample");
		return mav;
	}
	
	/**@param enterprisePic 最多选择6张图片
	 * */
	@RequestMapping("saveData")
	@ResponseBody
	public ReturnValueVo saveData(
			CostumeSample c,
			@RequestParam(value="smPic",required=false)MultipartFile[] smPic,
			@RequestParam(value="detailPic",required=false)MultipartFile[] detailPic,
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
		c.setUpdateBy(createBy);
		
		//验证文件类型、大小是否符合
		String errorMsg = this.verifyImg(smPic,detailPic);
		if(errorMsg.length() > 0)
			return new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		//转储图片
		//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/costumeSample/").getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		try{
			if(smPic.length > 0){
				String img = null;
				img = this.transferFile(smPic[0],uploadDir,c.getEnterpriseNum(),"0");
				for(int i=1;i<smPic.length;i++){
					img +=  ","+this.transferFile(smPic[i],uploadDir,c.getEnterpriseNum(),i+"");
				}
				c.setSmImg(img);
			}
			
			if(detailPic.length > 0){
				String img = null;
				img = this.transferFile(detailPic[0],uploadDir,c.getEnterpriseNum(),"0");
				for(int i=1;i<detailPic.length;i++){
					img +=  ","+this.transferFile(detailPic[i],uploadDir,c.getEnterpriseNum(),i+"");
				}
				c.setDetailImg(img);
			}
		} catch (IllegalStateException | IOException ex) {
			ex.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "上传图片出错,请重试");
		}
		c.setNum(biz.generateNumber(c.getEnterpriseNum()).toString());
		c.setUpdateTime(new Date());
		biz.save(c);
		return new ReturnValueVo(ReturnValueVo.SUCCESS, c);
	}
	
	@RequestMapping("updateData")
	@ResponseBody
	public ReturnValueVo updateData(
			CostumeSample c,
			String[] delImg,
			@RequestParam(value="smPic",required=false)MultipartFile[] smPic,
			@RequestParam(value="detailPic",required=false)MultipartFile[] detailPic,
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
		c.setUpdateBy(createBy);
		
		//验证文件类型、大小是否符合
		String errorMsg = this.verifyImg(smPic,detailPic);
		if(errorMsg.length() > 0)
			return new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/costumeSample/").getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		//删除旧图片
		if(delImg!=null){
			FileUtil.delImg(uploadDir, delImg);
		}
		//转储图片
		try{
			if(smPic.length > 0){
				String img = null;
				img = this.transferFile(smPic[0],uploadDir,c.getEnterpriseNum(),"0");
				for(int i=1;i<smPic.length;i++){
					img +=  ","+this.transferFile(smPic[i],uploadDir,c.getEnterpriseNum(),i+"");
				}
				if(c.getSmImg().length() > 0)
					c.setSmImg(c.getSmImg()+','+img);
				else
					c.setSmImg(img);
			}
			
			if(detailPic.length > 0){
				String img = null;
				img = this.transferFile(detailPic[0],uploadDir,c.getEnterpriseNum(),"0");
				for(int i=1;i<detailPic.length;i++){
					img +=  ","+this.transferFile(detailPic[i],uploadDir,c.getEnterpriseNum(),i+"");
				}
				if(c.getDetailImg().length() > 0)
					c.setDetailImg(c.getDetailImg()+','+img);
				else
					c.setDetailImg(img);
			}
		} catch (IllegalStateException | IOException ex) {
			ex.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "上传图片出错,请重试");
		}
		c.setUpdateTime(new Date());
		biz.update(c);
		return new ReturnValueVo(ReturnValueVo.SUCCESS, c);
	}
	
	@RequestMapping("search")
	public ModelAndView search(String sampleKeyword){
		if(sampleKeyword == null)
			sampleKeyword = "";
		BootTablePageDto<SampleVo> result = biz.search(null,null,null,null,null,sampleKeyword,0,20,null);
		ModelAndView mav = new ModelAndView("main/costumeSample");
		mav.addObject("result", result);
		
		//广告位 台州市椒江牡丹服装加工厂 三门县百润服装加工厂
		List<AdPosition> adPositions = adPositionBiz.getByCode("sample_list");
		mav.addObject("adPositions", JacksonJson.beanToJson(adPositions));
		
		//保留页面顶部搜索框的状态
		mav.addObject("tabIndex",2);
		mav.addObject("sampleKeyword",sampleKeyword);
		return mav;
	}
	
	@RequestMapping("search2")
	@ResponseBody
	public BootTablePageDto<SampleVo> search2(Long province,Long city,Long county,Long town, 
			Integer costumeCode,int offset,Long total){
		BootTablePageDto<SampleVo> result = biz.search(province,city,county,town,costumeCode,"",offset,20,total);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("showDetail/{num}")
	public ModelAndView showDetail(@PathVariable String num,HttpSession session){
		ModelAndView mav = new ModelAndView("main/sampleDetail");
		CostumeSample sample = biz.getByNum(num);
		
		if(sample!=null){
			Enterprise e = enterpriseBiz.getByNum(sample.getEnterpriseNum());
			List<String> costumeNames = enterpriseCostumeRelaBiz.getCostumeNameByEnterpriseId(e.getId());
			List<String> districts = districtBiz.getNameByUser(e);
			mav.addObject("enterprise", e);
			mav.addObject("costumeNames", costumeNames);
			mav.addObject("districts",districts);
			
			ServletContext servletContext = session.getServletContext();
			HashMap<Integer,String> codeNameMap = (HashMap<Integer,String>)servletContext.getAttribute("costumeCateMap");
			List<Integer> costumeCodes = biz.getCostumeCode(e.getNumber());
			//得到产品类别,及父类别
			Set<Integer> costumeCodeSet = new HashSet<Integer>();
			for(int i=0; i<costumeCodes.size(); i++){
				int code = costumeCodes.get(i);
				costumeCodeSet.add(code);
				if(code>10000){
					costumeCodeSet.add(code/100);
				}
			}
			HashMap<Integer,String> costumeCateMap = new HashMap<Integer,String>();
			Iterator<Integer> iterator = costumeCodeSet.iterator();
			while(iterator.hasNext()){
				Integer code = iterator.next();
				costumeCateMap.put(code, codeNameMap.get(code));
			}
			mav.addObject("costumeCateMap", JacksonJson.beanToJson(costumeCateMap));
			
			//获取最新的两个服饰样品
			List<CostumeSample> newest = biz.getNewest(num, e.getNumber());
			for(int i=0;i<newest.size();i++){
				String smImg = newest.get(i).getSmImg();
				String[] imgs = smImg.split(",");
				newest.get(i).setSmImg(imgs[0]);
			}
			mav.addObject("newestList", newest);
		}
		mav.addObject("costumeSample", sample);
		return mav;
	}
	
	/**显示工厂详情页-样品展示*/
	@SuppressWarnings("unchecked")
	@RequestMapping("showEntSample/{enterpriseNum}/{costumeCode}")
	public ModelAndView showEntSample(@PathVariable String enterpriseNum,@PathVariable Long costumeCode,HttpSession session){
		ModelAndView mav = new ModelAndView("main/enterpriseSample");
		//获取工厂名称、审核状态
		Object[] entField = enterpriseBiz.getByField(enterpriseNum,"id","number","enterpriseName","auditState");
		if(entField!=null){
			Enterprise e = new Enterprise();
			e.setId((Integer)entField[0]);
			e.setNumber((String)entField[1]);
			e.setEnterpriseName((String)entField[2]);
			e.setAuditState((Byte)entField[3]);
			mav.addObject("enterprise", e);
		}
		
		//获取工厂已添加的样品的产品类别
		ServletContext servletContext = session.getServletContext();
		HashMap<Integer,String> codeNameMap = (HashMap<Integer,String>)servletContext.getAttribute("costumeCateMap");
		List<Integer> costumeCodes = biz.getCostumeCode(enterpriseNum);
		//得到产品类别,及父类别
		Set<Integer> costumeCodeSet = new HashSet<Integer>();
		for(int i=0; i<costumeCodes.size(); i++){
			int code = costumeCodes.get(i);
			costumeCodeSet.add(code);
			if(code>10000){
				costumeCodeSet.add(code/100);
			}
		}
		HashMap<Integer,String> costumeCateMap = new HashMap<Integer,String>();
		Iterator<Integer> iterator = costumeCodeSet.iterator();
		while(iterator.hasNext()){
			Integer code = iterator.next();
			costumeCateMap.put(code, codeNameMap.get(code));
		}
		mav.addObject("costumeCateMap", JacksonJson.beanToJson(costumeCateMap));
		
		if(costumeCode==0)
			costumeCode = null;//点击工厂详情里的样品展示跳转的页面
		//获取全部产品类别列表
		BootTablePageDto<Sample2Vo> result = biz.getEntSample(enterpriseNum, costumeCode, 0, 20, null);
		mav.addObject("result", result);
		return mav;
	}
	
	@RequestMapping("getEntSample")
	@ResponseBody
	public BootTablePageDto<Sample2Vo> getEntSample(String enterpriseNum,Long costumeCode,int offset,Long total){
		if(costumeCode==0){
			costumeCode = null;
		}
		return biz.getEntSample(enterpriseNum, costumeCode, offset, 20, total);
	}
	
	@RequestMapping("showMySample")
	public ModelAndView showMySample(HttpSession session){
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		String entNum = enterpriseBiz.getNumByUserId(basicUser.getId());
		ModelAndView mav = new ModelAndView("main/myCenter/mySample");
		mav.addObject("enterpriseNum", entNum);
		return mav;
	}
	
	/**个人中信-店铺管理-样品分页查询*/
	@SuppressWarnings("unchecked")
	@RequestMapping("findMySample")
	@ResponseBody
	public BootTablePageDto<CostumeSampleVo> findMySample(HttpSession session,Long num,String name, String enterpriseNum, String beginDate,String endDate, int offset, int limit, Long total){
		ServletContext servletContext=session.getServletContext();
		HashMap<Integer,String> costumeCateMap = (HashMap<Integer,String>)servletContext.getAttribute("costumeCateMap");
		BootTablePageDto<CostumeSampleVo> bt = biz.findMySample(num, name, enterpriseNum, beginDate, endDate, offset, limit, total);
		
		List<CostumeSampleVo> list = bt.getRows();
		for(int i=0; i<list.size(); i++){
			CostumeSampleVo vo = list.get(i);
			String str = costumeCateMap.get(vo.getCostumeCode());
			vo.setCostumeCate(str);
		}
		return bt;
	}
	
	/**后台分页查询*/
	@SuppressWarnings("unchecked")
	@RequestMapping("findByPage")
	@ResponseBody
	public BootTablePageDto<CostumeSampleVo> findByPage(HttpServletRequest request,Long num,String name,String enterpriseName, String beginDate,String endDate, int offset, int limit, Long total){
		ServletContext servletContext=request.getSession().getServletContext();
		HashMap<Integer,String> costumeCateMap = (HashMap<Integer,String>)servletContext.getAttribute("costumeCateMap");
		BootTablePageDto<CostumeSampleVo> bt = biz.findByPage(num, name, enterpriseName, beginDate, endDate, offset, limit, total);
		
		List<CostumeSampleVo> list = bt.getRows();
		for(int i=0; i<list.size(); i++){
			CostumeSampleVo vo = list.get(i);
			String str = costumeCateMap.get(vo.getCostumeCode());
			vo.setCostumeCate(str);
		}
		return bt;
	}
	
	@Override
	public Integer delete(@PathVariable Integer id, HttpSession session) {
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/costumeSample/").getPath();
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
	
	/**验证图片
	 * */
	private String verifyImg(MultipartFile[] smPic,MultipartFile[] detailPic){
		String errorMsg = "";
		String contentType = null;
		String picError = "";
		for(int i=0;i<smPic.length;i++){
			contentType = smPic[i].getContentType();
			if( smPic[i].getSize()>imgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
				picError = "工厂照片不符合上传要求";
			}
		}
		errorMsg += picError;
		picError = "";
		for(int i=0;i<detailPic.length;i++){
			contentType = detailPic[i].getContentType();
			if( detailPic[i].getSize()>imgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
				picError = "工厂照片不符合上传要求";
			}
		}
		errorMsg += picError;
		return errorMsg;
	}
	
	/**保存文件到磁盘 YYMMddHHmmss+ 3位随机数 + 工厂编号后4位
	 * @param srcFile 原文件
	 * @param uploadDir 保存路径
	 * @param createBy 创建人ID
	 * @return 返回新文件名
	 * @throws IOException 
	 * @throws IllegalStateException */
	private String transferFile(MultipartFile srcFile, String uploadDir, String enterpriseNum, String newName) throws IllegalStateException, IOException{
		String suffix = null;
		String fileName = srcFile.getOriginalFilename();//获取上传文件的原名
		String ary[] = fileName.split("\\.");
		suffix = ary[ary.length-1];
		//通过transferTo()将文件存储到硬件中
		String time = DateTransform.Date2String(new Date(), "YYMMddHHmmss");
		int random = new java.util.Random().nextInt(900)+100;
		String newFileName = time + random + enterpriseNum.toString().substring(15)+newName+"."+suffix;
		srcFile.transferTo(new File(uploadDir + newFileName));
		return newFileName;
	}
}