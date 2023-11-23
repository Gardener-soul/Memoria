package com.ssafy.memoria.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.memoria.model.dao.RollingPaperDao;
import com.ssafy.memoria.model.dto.RollingPaper;

@Service
public class RollingPaperServiceImpl implements RollingPaperService {

	private RollingPaperDao rollingPaperDao;

	@Autowired
	public void setRollingPaperDao(RollingPaperDao rollingPaperDao) {
		this.rollingPaperDao = rollingPaperDao;
	}

	@Override
	public List<RollingPaper> getList() {
		List<RollingPaper> list = rollingPaperDao.selectAll();
		
	    for (RollingPaper paper : list) {
	        int writerCount = rollingPaperDao.selectLetterCount(paper.getRollingPaperNo());
	        paper.setWriterCount(writerCount);
	    }

	    return list;
	}

	@Override
	public RollingPaper getRollingPaper(int rollingPaperNo) {
		return rollingPaperDao.selectOne(rollingPaperNo);
	}

	@Transactional
	@Override
	public RollingPaper writeRollingPaper(RollingPaper rollingPaper) {
		int RPno = rollingPaperDao.insertRollingPaper(rollingPaper);
		
		if(RPno > 0) {
			return rollingPaper;
		}else {
			return null;
		}
	}

	@Transactional
	@Override
	public void removeRollingPaper(int rollingPaperNo) {
		rollingPaperDao.deleteRollingPaper(rollingPaperNo);
	}

	@Transactional
	@Override
	public void modifyRollingPaper(RollingPaper rollingPaper) {
		rollingPaperDao.updateRollingPaper(rollingPaper);
	}
}
