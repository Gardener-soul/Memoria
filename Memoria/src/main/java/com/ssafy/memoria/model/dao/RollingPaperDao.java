package com.ssafy.memoria.model.dao;

import java.util.List;

import com.ssafy.memoria.model.dto.RollingPaper;

public interface RollingPaperDao {

	public List<RollingPaper> selectAll();

	public int selectLetterCount(int rollingPaperNo);
	
	public RollingPaper selectOne(int rollingPaperNo);

	public int insertRollingPaper(RollingPaper rollingPaper);

	public void deleteRollingPaper(int rollingPaperNo);

	public void updateRollingPaper(RollingPaper rollingPaper);
	
}
