package com.yedam.mohobby.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.admin.AdminMoimService;
import com.yedam.mohobby.service.admin.FlaggingVO;

/**
 * @create 2002/10/06
 * @author gabin
 * @title 관리자 sns
 */
@RestController
@CrossOrigin(origins = "*")
public class AdminMoimController {
	
	@Autowired
	AdminMoimService service;
	
	/**
	 * @title 신고된 소모임 목록
	 * @return List<FlaggingVO>
	 */
	@GetMapping("/adminflagmoim")
	public List<FlaggingVO> flagMoimList() {
		return service.flagMoimList();
	}
	
	/**
	 * 
	 * @param flaggingVO
	 */
	@PutMapping("/updateflagmoim")
	public void updateFlagMoim(FlaggingVO flaggingVO) {
		service.updateFlagMoim(flaggingVO);
	}
	
}
