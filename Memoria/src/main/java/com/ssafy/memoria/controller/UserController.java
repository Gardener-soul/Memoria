package com.ssafy.memoria.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.memoria.model.dto.User;
import com.ssafy.memoria.model.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api(tags="로그인 컨트롤러")
@CrossOrigin("*")
public class UserController {
	// UserService 라고 하는 친구를 주입
	@Autowired
	private UserService userService;

	// 전체 유저 가져오기
	@GetMapping("users")
	public List<User> userList() {
		return userService.getUserList();
	}

	// 회원가입을 해보자 form 태그 형식으로 넘어왔다.
	@PostMapping("signup")
	public ResponseEntity<Integer> signup(@RequestBody User user) {
		int result = userService.signup(user);
		// result 가 0이면 등록 x
		// result 가 1이면 등록 o
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User tmp = userService.login(user);
		System.out.println(tmp);
		// 로그인 실패
		if(tmp == null)
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		
		User userInfo = new User(tmp.getUserNo(), tmp.getUserName());
		
		return new ResponseEntity<User>(userInfo, HttpStatus.OK);
	}

	@GetMapping("logout")
	public ResponseEntity<Void> logout(HttpSession session) {
//		session.removeAttribute("loginUser");
		session.invalidate();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
