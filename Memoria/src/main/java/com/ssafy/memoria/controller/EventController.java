package com.ssafy.memoria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.memoria.model.dto.Event;
import com.ssafy.memoria.model.dto.SearchCondition;
import com.ssafy.memoria.model.dto.User;
import com.ssafy.memoria.model.service.EventService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/event")
@Api(tags="롤링페이퍼 컨트롤러")
@CrossOrigin("*")
public class EventController {

	@Autowired
	private EventService eventService;

	// 목록
	@GetMapping("list")
	public ResponseEntity<?> list(Event event){
		List<Event> list = eventService.getList(); // 전체 조회
		System.out.println(list);
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Event>>(list, HttpStatus.OK);
	}

	// 수정
	@PutMapping("check")
	public ResponseEntity<Void> check(@RequestBody User user){
		eventService.completeEvent(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
