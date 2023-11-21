package com.ssafy.memoria.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.memoria.model.dto.User;

public interface UserService {

	List<User> getUserList();
	
	// 회원가입
	int signup(User user);

	// 로그인
	User login(User user);

	// 내 프로필 수정
	User modifyUser(User user, MultipartFile image) throws IOException;
	Resource loadImage(String imageName);

}
