package com.ssafy.memoria.model.dao;

import java.util.List;

import com.ssafy.memoria.model.dto.Event;
import com.ssafy.memoria.model.dto.SearchCondition;
import com.ssafy.memoria.model.dto.User;

public interface EventDao {

	public List<Event> selectAll();

	public void completeEvent(User user);

	public List<Event> search(SearchCondition condition);
}
