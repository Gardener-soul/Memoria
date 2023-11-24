package com.ssafy.memoria.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.memoria.model.dao.EventDao;
import com.ssafy.memoria.model.dto.Event;
import com.ssafy.memoria.model.dto.User;

@Service
public class EventServiceImpl implements EventService {

	private EventDao eventDao;

	@Autowired
	public void setRollingPaperDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	public List<Event> getList() {
		System.out.println("모든 이벤트를 가지고 왔습니다.");

		return eventDao.selectAll();
	}

	@Transactional
	@Override
	public void completeEvent(User user) {
		eventDao.completeEvent(user);
	}

}
