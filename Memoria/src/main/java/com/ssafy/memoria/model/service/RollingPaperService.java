package com.ssafy.memoria.model.service;

import java.util.List;

import com.ssafy.memoria.model.dto.RollingPaper;

public interface RollingPaperService {
	// 롤링페이퍼 전체 조회
	List<RollingPaper> getList();

	// 롤링페이퍼 상세 조회
	RollingPaper getRollingPaper(int rollingPaperNo);

	// 롤링페이퍼 등록
	RollingPaper writeRollingPaper(RollingPaper rollingPaper);

	// 롤링페이퍼 삭제
	void removeRollingPaper(int rollingPaperNo);

	// 롤링페이퍼 수정
	void modifyRollingPaper(RollingPaper rollingPaper);

	// 검색 버튼을 눌렀을 때 처리할 메서드
//	List<RollingPaper> search(SearchCondition condition);
}
