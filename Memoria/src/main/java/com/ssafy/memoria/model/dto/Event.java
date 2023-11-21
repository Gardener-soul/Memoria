package com.ssafy.memoria.model.dto;

import java.time.LocalDateTime;

public class Event {

	private int eventNo;
	private int userNo;
	private String eventTitle;
	private String description;
    private int day;
    private String achieve;
    private LocalDateTime achieveDate;
    
	public Event() {
		super();
	}

	public Event(int eventNo, int userNo, String eventTitle, String description, int day, String achieve,
			LocalDateTime achieveDate) {
		super();
		this.eventNo = eventNo;
		this.userNo = userNo;
		this.eventTitle = eventTitle;
		this.description = description;
		this.day = day;
		this.achieve = achieve;
		this.achieveDate = achieveDate;
	}

	public int getEventNo() {
		return eventNo;
	}

	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
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

	@Override
	public String toString() {
		return "Event [eventNo=" + eventNo + ", userNo=" + userNo + ", eventTitle=" + eventTitle + ", description="
				+ description + ", day=" + day + ", achieve=" + achieve + ", achieveDate=" + achieveDate + "]";
	}

}
