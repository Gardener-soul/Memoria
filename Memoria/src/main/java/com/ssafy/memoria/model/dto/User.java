package com.ssafy.memoria.model.dto;

import java.time.LocalDateTime;

public class User {
	private int userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String email;
	private String img;
	private String orgImg;
	private int eventDay;
	private String achieve;
    private LocalDateTime achieveDate;
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
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
	}

	public User(int userNo, String userId, String userPwd, String userName, String email, String img, String orgImg,
			int eventDay, String achieve, LocalDateTime achieveDate, LocalDateTime regDate, int reportCount) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.img = img;
		this.orgImg = orgImg;
		this.eventDay = eventDay;
		this.achieve = achieve;
		this.achieveDate = achieveDate;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getOrgImg() {
		return orgImg;
	}

	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}

	public int getEventDay() {
		return eventDay;
	}

	public void setEventDay(int eventDay) {
		this.eventDay = eventDay;
	}

	public String getAchieve() {
		return achieve;
	}

	public void setAchieve(String achieve) {
		this.achieve = achieve;
	}

	public LocalDateTime getAchieveDate() {
		return achieveDate;
	}

	public void setAchieveDate(LocalDateTime achieveDate) {
		this.achieveDate = achieveDate;
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
				+ ", email=" + email + ", img=" + img + ", orgImg=" + orgImg + ", eventDay=" + eventDay + ", achieve="
				+ achieve + ", achieveDate=" + achieveDate + ", regDate=" + regDate + ", reportCount=" + reportCount
				+ "]";
	}

}
