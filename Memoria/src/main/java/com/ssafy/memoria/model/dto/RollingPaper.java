package com.ssafy.memoria.model.dto;

import java.time.LocalDateTime;

public class RollingPaper {
    private int rollingPaperNo;
    private int ownerNo;
    private String userName; // 우리가 가져오고 싶은 건 ownerNo 가 아닌 userName 이므로 추가 해줘야 함 !
    private String title;
    private int writerCount;
    private LocalDateTime regDate;	
    
	public RollingPaper() {
		super();
	}
	
	public RollingPaper(String title) {
		this.title = title;
	}
	
	public RollingPaper(int rollingPaperNo, int ownerNo, String title, int writerCount, LocalDateTime regDate) {
		super();
		this.rollingPaperNo = rollingPaperNo;
		this.ownerNo = ownerNo;
		this.title = title;
		this.writerCount = writerCount;
		this.regDate = regDate;
	}
	
	public int getRollingPaperNo() {
		return rollingPaperNo;
	}
	
	public void setRollingPaperNo(int rollingPaperNo) {
		this.rollingPaperNo = rollingPaperNo;
	}
	
	public int getOwnerNo() {
		return ownerNo;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setOwnerNo(int ownerNo) {
		this.ownerNo = ownerNo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getWriterCount() {
		return writerCount;
	}
	
	public void setWriterCount(int writerCount) {
		this.writerCount = writerCount;
	}
	
	public LocalDateTime getRegDate() {
		return regDate;
	}
	
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "RollingPaper [rollingPaperNo=" + rollingPaperNo + ", ownerNo=" + ownerNo + ", userName=" + userName
				+ ", title=" + title + ", writerCount=" + writerCount + ", regDate=" + regDate + "]";
	}

}
