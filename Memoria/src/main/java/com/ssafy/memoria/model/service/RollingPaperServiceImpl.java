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
		System.out.println("모든 롤링페이퍼를 가지고 왔습니다.");
		List<RollingPaper> list = rollingPaperDao.selectAll();
		
	    for (RollingPaper paper : list) {
	        int writerCount = rollingPaperDao.selectLetterCount(paper.getRollingPaperNo());
	        paper.setWriterCount(writerCount);
	    }

	    return list;
	}

	@Override
	public RollingPaper getRollingPaper(int rollingPaperNo) {
		System.out.println(rollingPaperNo + "번 글을 읽었습니다.");
//		rollingPaperDao.updateViewCnt(rollingPaperNo);
		return rollingPaperDao.selectOne(rollingPaperNo);
	}

	@Transactional
	@Override
	public RollingPaper writeRollingPaper(RollingPaper rollingPaper) {
		System.out.println("롤링페이퍼를 작성합니다.");
		int RPno = rollingPaperDao.insertRollingPaper(rollingPaper);
		if(RPno > 0) {
			return rollingPaper;
		}else {
			System.out.println("여기서 에러");
			return null;
		}
	}

	@Transactional
	@Override
	public void removeRollingPaper(int rollingPaperNo) {
		System.out.println(rollingPaperNo + "번 글을 삭제 했습니다.");
		rollingPaperDao.deleteRollingPaper(rollingPaperNo);
	}

	@Transactional
	@Override
	public void modifyRollingPaper(RollingPaper rollingPaper) {
		rollingPaperDao.updateRollingPaper(rollingPaper);
	}

//	@Override
//	public List<RollingPaper> search(SearchCondition condition) {
//		return rollingPaperDao.search(condition);
//	}
}
