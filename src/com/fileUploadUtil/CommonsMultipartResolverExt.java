package com.fileUploadUtil;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUpload;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CommonsMultipartResolverExt extends CommonsMultipartResolver {

	/**可以在这个方法中能够获取到FileUpload对象
	 * */
	@Override
	protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
		System.out.println("\n ============parseRequest============ \n");
		String encoding = determineEncoding(request);
		FileUpload servletFileUpload = prepareFileUpload(encoding);
		//设置监听器
//		System.out.println("fileUpload class:"+servletFileUpload.getClass());
//		System.out.println("fileUpload instance:"+servletFileUpload);
		
		servletFileUpload.setProgressListener(new FileUploadListener(request.getSession()));
		return super.parseRequest(request);
	}
	
}
