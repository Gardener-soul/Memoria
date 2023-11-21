package com.ssafy.memoria.model.dao;

import java.util.List;

import com.ssafy.memoria.model.dto.Letter;

public interface LetterDao {

	public List<Letter> selectAll(Letter letter);

	public Letter selectOne(int letterNo);

	public int insertLetter(Letter letter);

	public void deleteLetter(int letterNo);

	public void updateLetter(Letter letter);

	public List<Letter> selectMy(Letter letter);
	
//	public List<Letter> search(SearchCondition condition);
}
