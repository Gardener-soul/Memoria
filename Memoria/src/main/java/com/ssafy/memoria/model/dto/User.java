package com.ssafy.memoria.model.dto;

import java.time.LocalDateTime;

public class User {
	private int userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String email;
    private String nickname;
    private LocalDateTime regDate;
    private int reportCount;
    
	public User() {
		super();
	}
	
	public User(int userNo, String userName) {
		this.userNo = userNo;
		this.userName = userName;
	}
	
	public User(String userId, String userPwd, String userName, String email, String nickname) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.nickname = nickname;
	}

	public User(int userNo, String userId, String userPwd, String userName, String email, String nickname,
				LocalDateTime regDate, int reportCount) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.nickname = nickname;
		this.regDate = regDate;
		this.reportCount = reportCount;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", email=" + email + ", nickname=" + nickname + ", regDate=" + regDate + ", reportCount="
				+ reportCount + "]";
	}

}
