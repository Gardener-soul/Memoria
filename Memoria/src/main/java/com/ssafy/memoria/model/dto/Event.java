package com.ssafy.memoria.model.dto;

public class Event {
	private int eventNo;
	private String eventTitle;
	private String description;
    private int eventDay;

	public Event() {
		super();
	}

	public Event(int eventNo, String eventTitle, String description, int eventDay) {
		super();
		this.eventNo = eventNo;
		this.eventTitle = eventTitle;
		this.description = description;
		this.eventDay = eventDay;
	}

	public int getEventNo() {
		return eventNo;
	}

	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
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

	public int getEventDay() {
		return eventDay;
	}

	public void setEventDay(int eventDay) {
		this.eventDay = eventDay;
	}

	@Override
	public String toString() {
		return "Event [eventNo=" + eventNo + ", eventTitle=" + eventTitle + ", description=" + description
				+ ", eventDay=" + eventDay + "]";
	}

}
