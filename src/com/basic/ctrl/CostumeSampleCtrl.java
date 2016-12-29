package com.basic.ctrl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import com.basic.po.BasicUser;
import com.basic.po.CostumeSample;
import com.basic.vo.CostumeSampleVo;
import com.basic.vo.SampleVo;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.sys.ctrl.UserCtrl;
import com.sys.po.User;
import com.util.FileUtil;
import com.util.JacksonJson;

@Controller
@RequestMapping("costumeSample")
public class CostumeSampleCtrl extends BaseCtrl<CostumeSampleBiz,Integer,CostumeSample>{

	@Autowired
	private AdPositionBiz adPositionBiz;
	
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
				img = this.transferFile(smPic[0],uploadDir,createBy,"0");
				for(int i=1;i<smPic.length;i++){
					img +=  ","+this.transferFile(smPic[i],uploadDir,createBy,i+"");
				}
				c.setSmImg(img);
			}
			
			if(detailPic.length > 0){
				String img = null;
				img = this.transferFile(detailPic[0],uploadDir,createBy,"0");
				for(int i=1;i<detailPic.length;i++){
					img +=  ","+this.transferFile(detailPic[i],uploadDir,createBy,i+"");
				}
				c.setDetailImg(img);
			}
		} catch (IllegalStateException | IOException ex) {
			ex.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "上传图片出错,请重试");
		}
		c.setNum(biz.generateNumber(c.getEnterpriseNum()));
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
				img = this.transferFile(smPic[0],uploadDir,createBy,"0");
				for(int i=1;i<smPic.length;i++){
					img +=  ","+this.transferFile(smPic[i],uploadDir,createBy,i+"");
				}
				if(c.getSmImg().length() > 0)
					c.setSmImg(c.getSmImg()+','+img);
				else
					c.setSmImg(img);
			}
			
			if(detailPic.length > 0){
				String img = null;
				img = this.transferFile(detailPic[0],uploadDir,createBy,"0");
				for(int i=1;i<detailPic.length;i++){
					img +=  ","+this.transferFile(detailPic[i],uploadDir,createBy,i+"");
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
		List<AdPosition> adPositions = adPositionBiz.getByCode("costume_sample");
		mav.addObject("adPositions", JacksonJson.beanToJson(adPositions));
		
		//保留页面顶部搜索框的状态
		mav.addObject("tabIndex",2);
		mav.addObject("sampleKeyword",sampleKeyword);
		return mav;
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
}