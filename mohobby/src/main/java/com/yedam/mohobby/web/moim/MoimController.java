package com.yedam.mohobby.web.moim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.moim.MoimService;
import com.yedam.mohobby.service.moim.MoimVO;

@RestController
public class MoimController {
	@Autowired
	MoimService service;
	
	@PostMapping("/moimInsert")
	public MoimVO insertMoim(MoimVO moimVO) {
		service.moimInsert(moimVO);
		return moimVO;
	}
}
