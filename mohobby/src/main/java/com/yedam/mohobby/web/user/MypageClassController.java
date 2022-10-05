package com.yedam.mohobby.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.classes.ClassesVO;
import com.yedam.mohobby.service.user.MypageClassService;
import com.yedam.mohobby.service.user.MypageClassVO;

@RestController
public class MypageClassController {

	@Autowired
	MypageClassService service;
	
	// 유저가 공개중안 강의 목록(강사만)S
	@GetMapping("/mypageprofileclasses")
	public List<MypageClassVO> getProfileClasses(@RequestBody String memberId) {
		return service.getProfileClasses(memberId);
	}
	
	// 유저가 공개중인 강의 목록 수정
	@PutMapping("/mypageprofileclasses")
	public void updateProfileClasses(@RequestBody ClassesVO classesVO) {
		service.updateProfileClasses(classesVO);
	}
	
	// 유저가 운영중인 강의 목록(카테고리별 조회)
	@GetMapping("/mypagemanageclasses")
	public List<MypageClassVO> getManageClasses(@RequestBody String memberId, @RequestBody String keywordId) {
		return service.getManageClasses(memberId, keywordId);
	}
	
	// 유저가 참여중인 강의 목록 (카테고리별 조회)
	@GetMapping("/mypagetakeclasses")
	public List<MypageClassVO> getTakeClasses(@RequestBody String memberId, @RequestBody String keywordId) {
		return service.getTakeClasses(memberId, keywordId);
	}
}
