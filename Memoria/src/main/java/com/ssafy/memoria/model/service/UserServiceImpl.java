package com.ssafy.memoria.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.memoria.model.dao.UserDao;
import com.ssafy.memoria.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	@Override
	public int signup(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User login(User user) {
		User tmp = userDao.selectOne(user.getUserId());
		if(tmp != null && tmp.getUserPwd().equals(user.getUserPwd()))
			return tmp;
		return null;
	}
	
}
