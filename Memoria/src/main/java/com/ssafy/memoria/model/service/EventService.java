package com.ssafy.memoria.model.service;

import java.util.List;

import com.ssafy.memoria.model.dto.Event;
import com.ssafy.memoria.model.dto.User;

public interface EventService {
	// 이벤트 전체 조회
	List<Event> getList();

	// 이벤트 완료 일자 제출
	void completeEvent(User user);

}
