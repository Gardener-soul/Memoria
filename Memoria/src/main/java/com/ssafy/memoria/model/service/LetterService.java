package com.ssafy.memoria.model.service;

import java.util.List;

import com.ssafy.memoria.model.dto.Letter;

public interface LetterService {
	// 편지 전체 조회
	List<Letter> getList(Letter letter);

	// 편지 상세 조회
	Letter getLetter(int letterNo);

	// 편지 등록
	Letter writeLetter(Letter letter);

	// 편지 삭제
	void removeLetter(int letterNo);

	// 편지 수정
	void modifyLetter(Letter letter);

	// 검색 버튼을 눌렀을 때 처리할 메서드
//	List<Letter> search(SearchCondition condition);
}
