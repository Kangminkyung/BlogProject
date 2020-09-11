package com.spring.timeline.model;

import org.springframework.web.multipart.MultipartFile;

public class PhotoVO {
	
	private MultipartFile Filedata;     

	private String callback;
	    //callback URL
	
	private String callback_func;
	    //肄쒕갚�븿�닔??
	
	public MultipartFile getFiledata() {
	   return Filedata;
	}
	
	public void setFiledata(MultipartFile filedata) {
	   Filedata = filedata;
	}
	
	public String getCallback() {
	   return callback;
	}
	
	public void setCallback(String callback) {
	   this.callback = callback;
	}
	
	public String getCallback_func() {
	   return callback_func;
	}
	
	public void setCallback_func(String callback_func) {
	   this.callback_func = callback_func;
	}
}
