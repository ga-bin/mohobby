package com.yedam.mohobby.web.moim;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.moim.MoimService;
import com.yedam.mohobby.service.moim.MoimVO;

@RestController
//@CrossOrigin(origin = "")
public class moimRestController {
	
	@Autowired
	MoimService service;
	
	//전체조회
	@GetMapping("/")
	public List<MoimVO> getMoimList() {
		return service.getMoimList();
	}
}
