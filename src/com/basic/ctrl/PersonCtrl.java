package com.basic.ctrl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.basic.biz.BasicUserBiz;
import com.basic.biz.PersonBiz;
import com.basic.po.BasicUser;
import com.basic.po.Person;
import com.common.BaseCtrl;
import com.common.dto.BootTablePageDto;
import com.common.vo.ReturnValueVo;
import com.sys.biz.ConstantDictBiz;
import com.sys.ctrl.UserCtrl;
import com.sys.po.User;
import com.util.DateTransform;
import com.util.FileUtil;

@Controller
@RequestMapping("person")
public class PersonCtrl extends BaseCtrl<PersonBiz, Integer, Person> {

	@Autowired
	private BasicUserBiz basicUserBiz;
	@Autowired
	private ConstantDictBiz constantDictBiz;
	
	private static final long imgMaxSize = 200000;//文档最大200kb
	
	public PersonCtrl(){
		defaultPage = "backstage/person";
	}
	
	/**后台个人用户管理页面
	 * */
	@RequestMapping("backstage")
	public ModelAndView showBackstageManage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backstage/person");
		return mav;
	}

	/**根据搜索条件分页查询数据。
	 * @param offset 偏移量，即记录索引位置
	 * @param limit 每页记录数
	 * @param total 可为null
	 * */
	@RequestMapping("findByPage")
	@ResponseBody
	public BootTablePageDto<Person> findByPage(String userName,Long telephone,Byte auditState,Integer createType,String beginDate,String endDate,int offset, int limit, Long total){
		Date beginTime = null;
		Date endTime = null;
		if(beginDate.length()>0 && endDate.length()>0){
			beginTime = DateTransform.String2Date(beginDate, "yyyy-MM-dd");
			endTime = DateTransform.String2Date(endDate+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		}
		return biz.findByPage(userName,telephone,auditState,createType,beginTime,endTime,offset,limit,total);
	}
	
	/**实名审核*/
	@RequestMapping("audit")
	@ResponseBody
	public ReturnValueVo audit(int id,byte auditState,HttpSession session){
		User user = UserCtrl.getLoginUser(session);
		biz.audit(id, auditState, user.getId());
		return new ReturnValueVo(ReturnValueVo.SUCCESS,null);
	}
	
	/**更新用户信息。同时刷新session中的信息*/
	@RequestMapping("updatePerson")
	@ResponseBody
	public ReturnValueVo updatePerson(Person p,
			@RequestParam(value="frontPhoto",required=false)MultipartFile frontPhoto,
			@RequestParam(value="backPhoto",required=false)MultipartFile backPhoto,
			HttpSession session){
		//检查是否登录
		BasicUser basicUser = BasicUserCtrl.getLoginUser(session);
		if(basicUser == null)
			return new ReturnValueVo(ReturnValueVo.ERROR, "请先登录");
		
		p.getBasicUser().setId(basicUser.getId());//设置id为当前登录者的id
		p.getBasicUser().setUpdateBy(0);
		
		//验证用户名、手机号码是否相同
		String errorMsg = basicUserBiz.isMyNameAndTeleExist(p.getBasicUser());
		if(errorMsg.length() > 0)
			return new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		//验证文件类型、大小是否符合
		errorMsg = this.verifyImg(frontPhoto,backPhoto);
		if(errorMsg.length() > 0)
			return  new ReturnValueVo(ReturnValueVo.ERROR, errorMsg);
		
		String uploadDir = null;
		try {
			uploadDir = session.getServletContext().getResource("uploadFile/person/").getPath();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		//转储图片
		try{
			if(frontPhoto != null){
				//删除旧图片
				String front = p.getIdFrontPhoto();
				if(front!=null && front.length()!=0){
					FileUtil.delImg(uploadDir, front);
				}
				p.setIdFrontPhoto(this.transferFile(frontPhoto,uploadDir,"front"));
			}
			
			if(backPhoto != null){
				//删除旧图片
				String back = p.getIdBackPhoto();
				if(back!=null && back.length()!=0){
					FileUtil.delImg(uploadDir, back);
				}
				p.setIdBackPhoto(this.transferFile(backPhoto,uploadDir,"back"));
			}
			
		} catch (IllegalStateException | IOException ex) {
			ex.printStackTrace();
			return new ReturnValueVo(ReturnValueVo.EXCEPTION, "上传图片出错,请重试");
		}
		biz.update(p);
		
		//更新成功后，更新session
		basicUser.setUserName(p.getBasicUser().getUserName());
		basicUser.setTelephone(p.getBasicUser().getTelephone());
		
		return new ReturnValueVo(ReturnValueVo.SUCCESS, p);
	}
	
	/**验证照片
	 * */
	private String verifyImg(MultipartFile frontPhoto, MultipartFile backPhoto){
		String errorMsg = "";
		String contentType = null;
		if(frontPhoto != null){
			contentType = frontPhoto.getContentType();
			if( frontPhoto.getSize()>imgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
				errorMsg = "身份证照(正面)不符合上传要求";
			}
		}
		if(backPhoto != null){
			contentType = backPhoto.getContentType();
			if( backPhoto.getSize()>imgMaxSize || (!contentType.equals("image/png") && !contentType.equals("image/jpeg")) ){
				errorMsg += "身份证照(反面)不符合上传要求";
			}
		}
		return errorMsg;
	}
	
	/**保存文件到磁盘
	 * @param srcFile 原文件
	 * @param uploadDir 保存路径
	 * @return 返回新文件名
	 * @throws IOException 
	 * @throws IllegalStateException */
	private String transferFile(MultipartFile srcFile, String uploadDir, String newName) throws IllegalStateException, IOException{
		String suffix = null;
		String fileName = srcFile.getOriginalFilename();//获取上传文件的原名
		String ary[] = fileName.split("\\.");
		suffix = ary[ary.length-1];
		//通过transferTo()将文件存储到硬件中
		long time = System.currentTimeMillis();
		String newFileName = time+newName+"."+suffix;
		srcFile.transferTo(new File(uploadDir + newFileName));
		return newFileName;
	}
	
}