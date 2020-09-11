package com.spring.friend.model;

public class FriendVO {

	private String seq;
	private String fk_email;
	private String friend_email;
	private String friend_date;
	private String status;
	
	public FriendVO() {}
	
	public FriendVO(String seq, String fk_email, String friend_email, String friend_date, String status) {
		super();
		this.seq = seq;
		this.fk_email = fk_email;
		this.friend_email = friend_email;
		this.friend_date = friend_date;
		this.status = status;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getFk_email() {
		return fk_email;
	}

	public void setFk_email(String fk_email) {
		this.fk_email = fk_email;
	}

	public String getFriend_email() {
		return friend_email;
	}

	public void setFriend_email(String friend_email) {
		this.friend_email = friend_email;
	}

	public String getFriend_date() {
		return friend_date;
	}

	public void setFriend_date(String friend_date) {
		this.friend_date = friend_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
