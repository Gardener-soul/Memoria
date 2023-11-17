package com.ssafy.memoria.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.memoria.model.dao.LetterDao;
import com.ssafy.memoria.model.dto.Letter;

@Service
public class LetterServiceImpl implements LetterService {

	private LetterDao letterDao;
	
	@Autowired
	public void setLetterDao(LetterDao letterDao) {
		this.letterDao = letterDao;
	}
	
	@Override
	public List<Letter> getList(Letter letter) {
		System.out.println("모든 편지를 가지고 왔습니다.");
		return letterDao.selectAll(letter);
	}

	@Override
	public Letter getLetter(int letterNo) {
		System.out.println(letterNo + "번 편지를 읽었습니다.");
		return letterDao.selectOne(letterNo);
	}

	@Transactional
	@Override
	public Letter writeLetter(Letter letter) {
		System.out.println("편지를 작성합니다.");
		int wl = letterDao.insertLetter(letter);
		if(wl > 0) {
			return letter;
		}else {
			System.out.println("여기서 에러");
			return null;
		}
	}

	@Transactional
	@Override
	public void removeLetter(int letterNo) {
		System.out.println(letterNo + "번 편지를 삭제 했습니다.");
		letterDao.deleteLetter(letterNo);
	}

	@Transactional
	@Override
	public void modifyLetter(Letter letter) {
		letterDao.updateLetter(letter);
	}

//	@Override
//	public List<letterDao> search(SearchCondition condition) {
//		return letterDao.search(condition);
//	}
}
