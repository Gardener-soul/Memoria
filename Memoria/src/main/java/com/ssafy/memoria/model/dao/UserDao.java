package com.ssafy.memoria.model.dao;

import java.util.List;

import com.ssafy.memoria.model.dto.User;

public interface UserDao {

	List<User> selectAll();

	int insertUser(User user);
	
	User selectOne(String userId);

	void updateUser(User user);
	
}
