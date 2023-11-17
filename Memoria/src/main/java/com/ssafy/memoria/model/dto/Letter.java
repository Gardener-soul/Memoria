package com.ssafy.memoria.model.dto;

import java.time.LocalDateTime;

public class Letter {
    private int letterNo;
    private int rollingPaperNo;
    private int writerNo;
    private String userName;
    private String content;
    private String font;
    private String fontColor;
    private String backColor;
    private LocalDateTime regDate;
    
	public Letter() {
		super();
	}

	public Letter(int letterNo, int rollingPaperNo, int writerNo, String content, String font, String fontColor, String backColor, LocalDateTime regDate) {
		super();
		this.letterNo = letterNo;
		this.rollingPaperNo = rollingPaperNo;
		this.writerNo = writerNo;
		this.content = content;
		this.font = font;
		this.fontColor = fontColor;
		this.backColor = backColor;
		this.regDate = regDate;
	}

	public int getLetterNo() {
		return letterNo;
	}

	public void setLetterNo(int letterNo) {
		this.letterNo = letterNo;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Letter [letterNo=" + letterNo + ", rollingPaperNo=" + rollingPaperNo + ", writerNo=" + writerNo
				+ ", userName=" + userName + ", content=" + content + ", font=" + font + ", fontColor=" + fontColor
				+ ", backColor=" + backColor + ", regDate=" + regDate + "]";
	}

}
