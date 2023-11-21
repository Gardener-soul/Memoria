package com.ssafy.memoria.model.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.memoria.model.dao.UserDao;
import com.ssafy.memoria.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	// 파일 입출력 멤버변수
	File uploadFolder = new File("uploads");
	Path uploadFolderPath = null;

	// 파일 입출력 생성자
	UserServiceImpl() throws IOException {
		uploadFolderPath = Paths.get(uploadFolder.getCanonicalPath());
	}

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
	
	@Transactional
	@Override
	public void modifyUser(User user, MultipartFile image) throws IOException {
		System.out.println("내 정보를 수정합니다.");
		
		if (image != null) {
			if (!uploadFolder.exists()) {
				Files.createDirectory(uploadFolderPath);
			}

			if (!image.isEmpty() && image.getSize() != 0) {
				String today = Long.toString(System.currentTimeMillis());
				String newImageName = today + "_" + image.getOriginalFilename();

				user.setOrgImg(image.getOriginalFilename());
				user.setImg(newImageName);

				Path imagePath = uploadFolderPath.resolve(user.getImg());

				image.transferTo(new File(imagePath.toString()));
			}
		}
		
		userDao.updateUser(user);
	}
	
	@Override
	public Resource loadImage(String imageName) {
		Path characterIamge = uploadFolderPath.resolve(imageName);
		Resource resource;
		try {
			resource = new UrlResource(characterIamge.toUri());
			return resource;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
