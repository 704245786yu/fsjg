package com.common;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dto.BootTablePageDto;

@SuppressWarnings({"rawtypes","unchecked"})
public class BaseCtrl<BIZ extends BaseBiz, ID extends Serializable, PO> {

	@Autowired
	protected BIZ biz;

	protected String defaultPage; //设置Ctrl默认显示的页面，该属性由子类初始化时设置
	
	/**显示默认的页面*/
	@RequestMapping
	public ModelAndView showDefaultPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(defaultPage);
		return mav;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public PO save(PO po){
		biz.save(po);
		return po;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public PO update(PO po){
		biz.update(po);
		return po;
	}
	
	/**@param id 主键
	 * @return 0:删除失败，1:删除成功
	 * */
	@RequestMapping("delete/{id}")
	@ResponseBody
	public Integer delete(@PathVariable ID id){
		try{
			biz.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	@RequestMapping("getAll")
	@ResponseBody
	public List<PO> getAll(){
		return biz.getAll();
	}
	
	/**分页查询所有数据
	 * @param total 总记录数
	 * @param offset 偏移量，即记录索引位置
	 * @param pageSize 每页记录数
	 * */
	@RequestMapping("getAllByPage")
	@ResponseBody
	public BootTablePageDto<PO> getAllByPage(Long total, int offset, int pageSize){
		return biz.getAllByPage(total, offset, pageSize);
	}
	
	/**上传文件
	 * @param files 前端上传文件的属性名。Spring MVC会将上传的文件自动绑定到MultipartFile对象中
	 * */
	@RequestMapping("uploadFile")
	@ResponseBody
	public Object uploadFile(@RequestParam("files")MultipartFile[] files,HttpSession httpSession){
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
