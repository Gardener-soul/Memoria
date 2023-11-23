package com.ssafy.memoria.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.memoria.model.dto.Letter;

public interface LetterService {
	// 해당 롤페에 맞는 편지 전체 조회
	List<Letter> getList(Letter letter);

	// Admin 편지 전체 조회
	public List<Letter> getAdminList(Letter letter);
	
	// 편지 상세 조회
	Letter getLetter(int letterNo);

	// 편지 등록
	int writeLetter(Letter letter, MultipartFile image) throws IOException;
	Resource loadImage(String imageName);
	
	// 편지 삭제
	void removeLetter(int letterNo);

	// 편지 수정
	void modifyLetter(Letter letter, MultipartFile image) throws IOException;

	// 내가 쓴 편지 전체 조회
	List<Letter> getMyList(Letter letter);
	
	// 검색 버튼을 눌렀을 때 처리할 메서드
//	List<Letter> search(SearchCondition condition);
}
