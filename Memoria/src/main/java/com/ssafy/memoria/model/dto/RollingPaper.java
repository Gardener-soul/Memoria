package com.ssafy.memoria.model.dto;

import java.time.LocalDateTime;

public class RollingPaper {
    private int rollingPaperNo;
    private int owner;
    private String title;
    private int writerCount;
    private LocalDateTime regDate;
    
	public RollingPaper() {
		super();
	}
	
	public RollingPaper(String title) {
		this.title = title;
	}
	
	public RollingPaper(int rollingPaperNo, int owner, String title, int writerCount, LocalDateTime regDate) {
		super();
		this.rollingPaperNo = rollingPaperNo;
		this.owner = owner;
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
	
	public int getOwner() {
		return owner;
	}
	
	public void setOwner(int owner) {
		this.owner = owner;
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
		return "RollingPaper [rollingPaperNo=" + rollingPaperNo + ", owner=" + owner + ", title=" + title
				+ ", writerCount=" + writerCount + ", regDate=" + regDate + "]";
	}
    
}
