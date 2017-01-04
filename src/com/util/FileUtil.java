package com.util;

import java.io.File;
import java.util.Date;

public class FileUtil {

	/**删除文件
	 * @param uploadDir 删除文件的目录
	 * @param fileName 要删除的文件名
	 * @return 是否删除成功
	 * */
	public static boolean delImg(String uploadDir,String fileName){
		File file = new File(uploadDir+fileName);
		file.delete();
		return true;
	}
	
	/**删除文件
	 * @param uploadDir 删除文件的目录
	 * @param fileNames 要删除的文件名
	 * @return 是否删除成功
	 * */
	public static boolean delImg(String uploadDir,String[] fileNames){
		for(int i=0; i<fileNames.length; i++){
			File file = new File(uploadDir+fileNames[i]);
			file.delete();
		}
		return true;
	}
	
	/**生成唯一文件名
	 * YYMMddHHmmss + 3位随机 + 工厂编号后4位
	 * */
	public static String genUniqueFileName(Long enterpriseNum){
		String time = DateTransform.Date2String(new Date(), "YYMMddHHmmss");
		int random = new java.util.Random().nextInt(900)+100;
		String str = time + random +enterpriseNum.toString().substring(15);
		return str;
	}
	
	/**下 载文件
	 
	public void download(HttpServletResponse response, String path, String fileName, String actualName) {
        File f = new File(path,fileName);  
        if (!f.exists()) {
    		try {
    			response.setContentType("text/html;charset=UTF-8"); 
//    			response.getWriter().write("<script>alert('无法下载该文件 !');window.history.back(-1);</script>");
    			response.getWriter().write("<script>alert('无法下载该文件 !');</script>");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
         }
        byte b[] = null;
		try(InputStream in = new FileInputStream(f)){
			b =new byte[(int)f.length()];//创建合适文件大小的数组  
			in.read(b);
			    response.setContentType(MediaType.parseMediaType());
                response.setHeader("Content-disposition", "attachment; filename=\""+new String(name.getBytes(), "iso-8859-1")+"\"");
                FileCopyUtils.copy(b, response.getOutputStream());
            	in.close(); 
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}  		
	}*/
	
}
