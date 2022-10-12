package com.yedam.mohobby.web.moim;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.moim.MoimService;
import com.yedam.mohobby.service.moim.MoimVO;

@RestController
@CrossOrigin(origins = "*")
public class MoimController {

	@Autowired
	MoimService service;
	
	//소모임 등록
	@PostMapping("/moimInsert")
	public MoimVO insertMoim(MoimVO moimVO) {
		service.moimInsert(moimVO);
		return moimVO;
	}

	//소모임 전체조회
	@GetMapping("/moimAllSelect")
	public List<MoimVO> getAllList(){
		return service.moimAllSelect();
	}
	
	//소모임 인기 목록 조회
	@GetMapping("/moimPopularSelect")
	public List<MoimVO> getPopularList(){
		return service.moimPopularSelect();
	}
	
	//소모임 멤버 모집 조회(6개씩)
	@GetMapping("/moimRecruitMember")
	public List<MoimVO> getmoimrecruitMember() {
		return service.moimrecruitMember();
	}
	
	//소모임명 조회
	@GetMapping("/moimNameSelect")
	public List<MoimVO> getNameList(){
		return service.moimNameSelect();
	}
	
	//소모임 카테고리 조회
	@GetMapping("/moimCatgSelect")
	public List<MoimVO> getCatgList(){
		return service.moimCatgSelect();
	}
	
	//소모임 종합검색
	@GetMapping("/moimAllSearch")
	public List<MoimVO> getAllSearch(@RequestParam("moimName")String moimName, @RequestParam("moimCatg")String moimCatg ){
		return service.moimAllSearch(moimName, moimCatg);
	}
}

