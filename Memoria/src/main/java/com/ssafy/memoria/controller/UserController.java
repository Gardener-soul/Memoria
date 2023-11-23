package com.ssafy.memoria.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.memoria.model.dto.Letter;
import com.ssafy.memoria.model.dto.User;
import com.ssafy.memoria.model.service.LetterService;
import com.ssafy.memoria.model.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api(tags="로그인 컨트롤러")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private LetterService letterService;

	// 전체 유저 가져오기
	@GetMapping("users")
	public List<User> userList() {
		return userService.getUserList();
	}

	// 회원가입
	@PostMapping("signup")
	public ResponseEntity<Integer> signup(@RequestBody User user) {
		int result = userService.signup(user);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}

	// 로그인
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User tmp = userService.login(user);
		// 로그인 실패
		if(tmp == null)
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		
		User userInfo = new User(tmp.getUserNo(), tmp.getUserName(), tmp.getImg(), tmp.getEventDay());
		
		return new ResponseEntity<User>(userInfo, HttpStatus.OK);
	}

	// 로그아웃
	@GetMapping("logout")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 내 프로필 수정
	@CrossOrigin("*")
	@PutMapping("update")
	public ResponseEntity<User> update(User user, @RequestParam(required = false) MultipartFile image) throws IOException {
		User tmp = userService.modifyUser(user, image);
		return new ResponseEntity<User>(tmp, HttpStatus.OK);
	}

	@GetMapping("/image/{imgFileName}")
	public ResponseEntity<?> getImage(@PathVariable String imgFileName) {
		Resource image = letterService.loadImage(imgFileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imgFileName + "\"")
				.body(image);
	}

	// 마이페이지 내 롤링페이퍼
	@GetMapping("mine")
	public ResponseEntity<?> myRollList(Letter letter) {
		List<Letter> list = letterService.getList(letter);

		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Letter>>(list, HttpStatus.OK);
	}

	// 마이페이지 내가 쓴 편지
	@GetMapping("letters")
	public ResponseEntity<?> myLetterList(Letter letter) {
		List<Letter> list = letterService.getMyList(letter);

		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Letter>>(list, HttpStatus.OK);
	}
}
