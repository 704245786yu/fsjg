package com.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fileUploadUtil.UploadProgressStatus;

@Controller
@RequestMapping("fileUploadCtrl")
public class FileUploadCtrl {
	
	@RequestMapping("getFileUploadProgress")
	@ResponseBody
	public UploadProgressStatus getFileUploadProgress(HttpSession session){
		UploadProgressStatus uploadProgressStatus = (UploadProgressStatus)session.getAttribute("uploadProgressStatus");
		return uploadProgressStatus;
	}
	
	/**上传文件
	 * @param file Spring MVC会将上传的文件自动绑定到MultipartFile对象中
	 * */
	/*@RequestMapping("upload")
	@ResponseBody
	public String uploadFile(@RequestParam("name")String name, @RequestParam("files")MultipartFile[] files){
		System.out.println("name:"+name);
		for(int i=0; i<files.length; i++){
			MultipartFile file = files[i];
			if(!file.isEmpty()){
				try {
					String fieldName = file.getName();//获取表单中文件组件的名字
					String fileName = file.getOriginalFilename();//获取上传文件的原名
					long size = file.getSize();//获取文件的字节大小，单位为byte
					System.out.println("fieldName:"+fieldName+"||fileName:"+fileName+"||size:"+size);
					//通过transferTo()将文件存储到硬件中
					file.transferTo(new File("E:\\Workspaces\\MyEclipse 2015\\TestSpringMVC\\WebRoot\\upload\\"+fileName));
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "upload:"+files.length+" files";
	}*/
	
	/**上传文件
	 * @param file Spring MVC会将上传的文件自动绑定到MultipartFile对象中
	 * */
	@RequestMapping("upload")
	@ResponseBody
	public String uploadFile(@RequestParam("files")MultipartFile[] files,HttpSession httpSession){
		for(int i=0; i<files.length; i++){
			MultipartFile file = files[i];
			if(!file.isEmpty()){
				try {
					String fieldName = file.getName();//获取表单中文件组件的名字
					String fileName = file.getOriginalFilename();//获取上传文件的原名
					long size = file.getSize();//获取文件的字节大小，单位为byte
					System.out.println("fieldName:"+fieldName+"||fileName:"+fileName+"||size:"+size);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
			}
		}
		return "upload:"+files.length+" files";
	}
}
