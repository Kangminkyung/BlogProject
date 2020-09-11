package com.spring.timeline.model;

import org.springframework.web.multipart.MultipartFile;

public class TimeLineVO {

	private String seq;
	private String email;
	private String content;
	private String writedate;
	private String status;
	
//	private String fileName; 
//	private String orgFilename; 
//	private String fileSize; 
	
//	private MultipartFile attach; 
								  
	public TimeLineVO() {}

	public TimeLineVO(String seq, String email, String content, String writedate, String status) {
		super();
		this.seq = seq;
		this.email = email;
		this.content = content;
		this.writedate = writedate;
		this.status = status;
		
	
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
