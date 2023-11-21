package com.ssafy.memoria.model.dto;

import java.time.LocalDateTime;

public class Photo {
	private int photoNo;
	private int rollingPaperNo;
	private int writerNo;
    private String userName;
	private String img;
	private String orgImg;
	private LocalDateTime regDate;
	
	public Photo() {
		super();
	}
	
	public Photo(int photoNo, int rollingPaperNo, int writerNo, String img, String orgImg, LocalDateTime regDate) {
		super();
		this.photoNo = photoNo;
		this.rollingPaperNo = rollingPaperNo;
		this.writerNo = writerNo;
		this.img = img;
		this.orgImg = orgImg;
		this.regDate = regDate;
	}

	public int getPhotoNo() {
		return photoNo;
	}

	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}

	public int getRollingPaperNo() {
		return rollingPaperNo;
	}

	public void setRollingPaperNo(int rollingPaperNo) {
		this.rollingPaperNo = rollingPaperNo;
	}

	public int getWriterNo() {
		return writerNo;
	}

	public void setWriterNo(int writerNo) {
		this.writerNo = writerNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Photo [photoNo=" + photoNo + ", rollingPaperNo=" + rollingPaperNo + ", writerNo=" + writerNo
				+ ", userName=" + userName + ", img=" + img + ", orgImg=" + orgImg + ", regDate=" + regDate + "]";
	}

}
