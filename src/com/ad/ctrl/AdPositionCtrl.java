package com.ad.ctrl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ad.biz.AdPositionBiz;
import com.ad.po.AdPosition;
import com.common.BaseCtrl;
import com.common.vo.ReturnValueVo;
import com.sys.ctrl.UserCtrl;
import com.util.FileUtil;

@Controller
@RequestMapping("adPosition")
public class AdPositionCtrl extends BaseCtrl<AdPositionBiz, Integer, AdPosition> {

	/**显示后台管理页面*/
	@RequestMapping("showManage")
	public ModelAndView showManage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backstage/ad/adPosition");
		return mav;
	}

	@RequestMapping("updateAdPosition")
	@ResponseBody
	public ReturnValueVo updateAdPosition(AdPosition po, MultipartFile imgFile,HttpSession session) {
		int id = UserCtrl.getLoginUser(session).getId();
		po.setUpdateBy(id);
		if(imgFile != null){
			String uploadDir = null;
			try {
				uploadDir = session.getServletContext().getResource("uploadFile/ad/").getPath();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			//删除旧图片
			if(po.getImg()!=null || po.getImg().length()>0){
				FileUtil.delImg(uploadDir, po.getImg());
			}
			//保存新图片
			try {
				po.setImg(this.transferFile(imgFile, uploadDir, 0, null));
			} catch (IllegalStateException | IOException ex) {
				ex.printStackTrace();
				return new ReturnValueVo(ReturnValueVo.EXCEPTION, "上传图片出错,请重试");
			}
		}
		biz.update(po);
		return new ReturnValueVo(ReturnValueVo.SUCCESS,po);
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
		String newFileName = time+"."+suffix;
		srcFile.transferTo(new File(uploadDir + newFileName));
		return newFileName;
	}
}