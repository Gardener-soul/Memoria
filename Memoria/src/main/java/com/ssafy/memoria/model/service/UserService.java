package com.ssafy.memoria.model.service;

import java.util.List;

import com.ssafy.memoria.model.dto.User;

public interface UserService {

	List<User> getUserList();

	int signup(User user);

	User login(User user);

}
