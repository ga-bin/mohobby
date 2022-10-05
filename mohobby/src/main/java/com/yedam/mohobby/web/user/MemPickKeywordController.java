package com.yedam.mohobby.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.admin.MemPickKeywordVO;
import com.yedam.mohobby.service.user.MemPickKeywordService;

@RestController
public class MemPickKeywordController {
	@Autowired
	MemPickKeywordService service;
	
	// 회원 관심사 list select
//	@GetMapping("/memPickKeyword")
//	public MemPickKeywordVO selectPickKeywordList(@RequestBody String memberId) {
//		// return service.
//	}
	
	// 회원 관심사 insert
	
	// 회원 관심사 delete
}
