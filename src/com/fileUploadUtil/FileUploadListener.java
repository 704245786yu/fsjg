package com.fileUploadUtil;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class FileUploadListener implements ProgressListener {

	private long account = 0;
	private HttpSession session;
	
	public FileUploadListener(){}
	
	public FileUploadListener(HttpSession session){
		this.session = session;
		UploadProgressStatus uploadProgressStatus = new UploadProgressStatus();
		session.setAttribute("uploadProgressStatus", uploadProgressStatus);
	}

	@Override
	public void update(long bytesRead, long contentLength, int items) {
		account++;
		if(account % 2000 == 0){
			System.out.println(this+"listener update======"+account);
		}
		UploadProgressStatus uploadProgressStatus = (UploadProgressStatus)session.getAttribute("uploadProgressStatus");
		//上传组件会调用该方法
		uploadProgressStatus.setBytesRead(bytesRead);
		uploadProgressStatus.setContentLength(contentLength);
		uploadProgressStatus.setItems(items);
	}

}
