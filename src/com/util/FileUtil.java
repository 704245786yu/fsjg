package com.util;

import java.io.File;

public class FileUtil {

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
}
