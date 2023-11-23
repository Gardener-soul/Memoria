package com.ssafy.memoria.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.memoria.model.dto.Letter;
import com.ssafy.memoria.model.service.LetterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/letter")
@Api(tags = "편지 컨트롤러")
@CrossOrigin("*")
public class LetterController {

	@Autowired
	private LetterService letterService;

	// 1. 목록
	@GetMapping("list")
	@ApiOperation(value = "편지 조회", notes = "검색조건도 넣으면 같이 가져온다.")
	public ResponseEntity<?> list(Letter letter) {
		List<Letter> list = letterService.getList(letter); // 전체 조회
		// List<Letter> list = letterService.search(condition); // 검색 조건이 있다면 그것으로 조회
		System.out.println(list); // 전체 리스트 잘 받아와지는지 확인

		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Letter>>(list, HttpStatus.OK);
	}

	// 1.5 Admin 편지 목록
	@GetMapping("admin/list")
	@ApiOperation(value = "편지 조회", notes = "검색조건도 넣으면 같이 가져온다.")
	public ResponseEntity<?> Adminlist(Letter letter) {
		List<Letter> list = letterService.getAdminList(letter);		
		System.out.println(list); // 전체 리스트 잘 받아와지는지 확인
		
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Letter>>(list, HttpStatus.OK);
	}
	
	// 2. 상세
	@GetMapping("/{letterNo}")
	public ResponseEntity<Letter> detail(@PathVariable int letterNo) {
		Letter letter = letterService.getLetter(letterNo);
		return new ResponseEntity<Letter>(letter, HttpStatus.OK);
	}

	// 3. 등록
	@PostMapping("write")
	public ResponseEntity<?> write(Letter letter, @RequestParam(required = false) MultipartFile image) throws IOException {
		int result = letterService.writeLetter(letter, image);

		if (result == 1) {
			return new ResponseEntity<Letter>(letter, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("유저 추가 실패", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping("/image/{imgFileName}")
	public ResponseEntity<?> getImage(@PathVariable String imgFileName) {
		Resource image = letterService.loadImage(imgFileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imgFileName + "\"")
				.body(image);
	}

	// 4. 삭제
	@DeleteMapping("delete/{letterNo}")
	public ResponseEntity<Void> delete(@PathVariable int letterNo) {
		letterService.removeLetter(letterNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 5. 수정
	@CrossOrigin("*")
	@PutMapping("update")
	public ResponseEntity<Void> update(Letter letter, @RequestParam(required = false) MultipartFile image) throws IOException {
		letterService.modifyLetter(letter, image);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

//	@PutMapping("/letter/{letterNo}") //JSON 형태의 데이터로 넘어왔을 떄 처리하고 싶은데?
//	public ResponseEntity<Void> update(@RequestBody Letter letter, @PathVariable int letterNo){
//		letter.setLetterNo(letterNo);
//		letterService.modifyLetter(letter);
//		//위와같은 상황 대비
//		
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
}
