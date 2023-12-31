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

import com.ssafy.memoria.model.dao.LetterDao;
import com.ssafy.memoria.model.dto.Letter;

@Service
public class LetterServiceImpl implements LetterService {

	private LetterDao letterDao;

	@Autowired
	public void setLetterDao(LetterDao letterDao) {
		this.letterDao = letterDao;
	}

	// 파일 입출력 멤버변수
	File uploadFolder = new File("uploads");
	Path uploadFolderPath = null;

	// 파일 입출력 생성자
	LetterServiceImpl() throws IOException {
		uploadFolderPath = Paths.get(uploadFolder.getCanonicalPath());
	}

	@Override
	public List<Letter> getList(Letter letter) {
		return letterDao.selectAll(letter);
	}
	
	@Override
	public List<Letter> getAdminList(Letter letter) {
		return letterDao.selectAdminAll(letter);
	}
	
	@Override
	public Letter getLetter(int letterNo) {
		return letterDao.selectOne(letterNo);
	}

	@Transactional
	@Override
	public int writeLetter(Letter letter, MultipartFile image) throws IOException {
		if (image != null) {
			if (!uploadFolder.exists()) {
				Files.createDirectory(uploadFolderPath);
			}

			if (!image.isEmpty() && image.getSize() != 0) {
				String today = Long.toString(System.currentTimeMillis());
				String newImageName = today + "_" + image.getOriginalFilename();

				letter.setOrgImg(image.getOriginalFilename());
				letter.setImg(newImageName);

				Path imagePath = uploadFolderPath.resolve(letter.getImg());

				image.transferTo(new File(imagePath.toString()));
			}
		}
		
		return letterDao.insertLetter(letter);
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

	@Transactional
	@Override
	public void removeLetter(int letterNo) {
		letterDao.deleteLetter(letterNo);
	}

	@Transactional
	@Override
	public void modifyLetter(Letter letter, MultipartFile image) throws IOException {
		if (image != null) {
			if (!uploadFolder.exists()) {
				Files.createDirectory(uploadFolderPath);
			}

			if (!image.isEmpty() && image.getSize() != 0) {
				String today = Long.toString(System.currentTimeMillis());
				String newImageName = today + "_" + image.getOriginalFilename();

				letter.setOrgImg(image.getOriginalFilename());
				letter.setImg(newImageName);

				Path imagePath = uploadFolderPath.resolve(letter.getImg());

				image.transferTo(new File(imagePath.toString()));
			}
		}
		
		letterDao.updateLetter(letter);
	}

	@Override
	public List<Letter> getMyList(Letter letter) {
		return letterDao.selectMy(letter);
	}
}
