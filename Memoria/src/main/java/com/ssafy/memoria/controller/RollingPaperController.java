package com.ssafy.memoria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.memoria.model.dto.RollingPaper;
import com.ssafy.memoria.model.dto.SearchCondition;
import com.ssafy.memoria.model.service.RollingPaperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/rollingPaper")
@Api(tags="롤링페이퍼 컨트롤러")
public class RollingPaperController {

	@Autowired
	private RollingPaperService rollingPaperService;

	//1. 목록
	@GetMapping("list")
	@ApiOperation(value="롤링페이퍼 조회", notes="검색조건도 넣으면 같이 가져온다.")
	public ResponseEntity<?> list(SearchCondition condition){
//		List<RollingPaper> list = rollingPaperService.getList(); //전체 조회
		List<RollingPaper> list = rollingPaperService.search(condition); //검색 조건이 있다면 그것으로 조회
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<RollingPaper>>(list, HttpStatus.OK);
	}
	
	//2. 상세보기
	@GetMapping("detail/{rollingPaperNo}")
	public ResponseEntity<RollingPaper> detail(@PathVariable int rollingPaperNo){
		RollingPaper rollingPaper = rollingPaperService.getRollingPaper(rollingPaperNo);
		//꼬옥 주소창을 통해 접근하려고 하는 악의무리가 있기 때문에 만약 없는 값을 보냈을때... 처리를 해주어라. (해볼것)
		return new ResponseEntity<RollingPaper>(rollingPaper, HttpStatus.OK);
	}
	
	//3. 등록
	@PostMapping("write")
	public ResponseEntity<RollingPaper> write(RollingPaper rollingPaper){
		rollingPaperService.writeRollingPaper(rollingPaper);
		return new ResponseEntity<RollingPaper>(rollingPaper, HttpStatus.CREATED);
	}
	
	//4. 삭제
	@DeleteMapping("delete/{rollingPaperNo}")
	public ResponseEntity<Void> delete(@PathVariable int rollingPaperNo){
		rollingPaperService.removeRollingPaper(rollingPaperNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//5. 수정
	@ApiIgnore
	@PutMapping("update") //JSON 형태의 데이터로 넘어왔을 떄 처리하고 싶은데?
	public ResponseEntity<Void> update(@RequestBody RollingPaper rollingPaper){
		rollingPaperService.modifyRollingPaper(rollingPaper);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
//	@PutMapping("/board/{id}") //JSON 형태의 데이터로 넘어왔을 떄 처리하고 싶은데?
//	public ResponseEntity<Void> update(@RequestBody Board board, @PathVariable int id){
//		board.setId(id);
//		boardService.modifyBoard(board);
//		//위와같은 상황 대비
//		
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
}