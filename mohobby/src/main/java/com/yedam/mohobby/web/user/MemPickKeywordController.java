package com.yedam.mohobby.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.user.MemPickKeywordService;
import com.yedam.mohobby.service.user.MemPickKeywordVO;

/**
 * @create 22/10/05
 * @author gabin
 * @title 회원 관심사
 */
@RestController
public class MemPickKeywordController {
	@Autowired
	MemPickKeywordService service;
	
	/**
	 * 
	 * @param memberId
	 * @return List<MemberPickKeywordVO> 
	 */
	// 회원 관심사 list select
	@GetMapping("/memPickKeyword")
	public List<MemPickKeywordVO> selectPickKeywordList(@RequestBody String memberId) {
		return service.selectPickKeywordList(memberId);
	}
	
	/**
	 * 
	 * @param memPickKeywordVO
	 */
	// 회원 관심사 insert
	@PostMapping("/memPickKeyword")
	public void insertPickKeyword(@RequestBody MemPickKeywordVO memPickKeywordVO) {
		service.insertPickKeyword(memPickKeywordVO);
	}
	
	/**
	 * 
	 * @param memPickKeywordVO
	 */
	// 회원 관심사 delete
	@DeleteMapping("/memPickKeyword")
	public void deletePickKeyword(@RequestBody MemPickKeywordVO memPickKeywordVO) {
		service.deletePickKeyword(memPickKeywordVO);
	}
}
