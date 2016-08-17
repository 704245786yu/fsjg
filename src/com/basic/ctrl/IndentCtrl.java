package com.basic.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.basic.biz.IndentBiz;
import com.basic.po.Indent;
import com.common.BaseCtrl;
import com.sys.biz.ConstantDictBiz;
import com.sys.po.ConstantDict;

@Controller
@RequestMapping("indent")
public class IndentCtrl extends BaseCtrl<IndentBiz,Integer,Indent>{

	@Autowired
	private ConstantDictBiz constantDictBiz;
	
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
	
	/**发布订单前，上传文件用*/
	@RequestMapping("uploadFile")
	@ResponseBody
	public String uploadFile(@RequestParam("files")MultipartFile[] files){
		for(int i=0; i<files.length; i++){
			MultipartFile file = files[i];
			if(!file.isEmpty()){
				try {
					String fieldName = file.getName();//获取表单中文件组件的名字
					String fileName = file.getOriginalFilename();//获取上传文件的原名
					long size = file.getSize();//获取文件的字节大小，单位为byte
					System.out.println("fieldName:"+fieldName+"||fileName:"+fileName+"||size:"+size);
					//通过transferTo()将文件存储到硬件中
					file.transferTo(new File("E:\\indent\\"+fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "success";
	}
	
	/**发布订单*/
	@RequestMapping("release")
	public ModelAndView release(@RequestParam("files")MultipartFile[] files){
		for(int i=0; i<files.length; i++){
			MultipartFile file = files[i];
			if(!file.isEmpty()){
				try {
					String fieldName = file.getName();//获取表单中文件组件的名字
					String fileName = file.getOriginalFilename();//获取上传文件的原名
					long size = file.getSize();//获取文件的字节大小，单位为byte
					System.out.println("fieldName:"+fieldName+"||fileName:"+fileName+"||size:"+size);
					//通过transferTo()将文件存储到硬件中
					file.transferTo(new File("E:\\indent\\"+fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
