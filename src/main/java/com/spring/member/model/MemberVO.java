package com.spring.member.model;

public class MemberVO {

	private String idx;
	private String email;
	private String pwd;
	private String registerday;
	private String status;
	
	public MemberVO() {}

	public MemberVO(String idx,String email,String pwd, String registerday, String status) {
		super();
		this.idx = idx;
		this.email = email;
		this.pwd = pwd;
		this.registerday = registerday;
		this.status = status;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRegisterday() {
		return registerday;
	}

	public void setRegisterday(String registerday) {
		this.registerday = registerday;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
