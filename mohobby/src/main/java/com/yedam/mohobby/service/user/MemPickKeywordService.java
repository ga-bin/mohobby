package com.yedam.mohobby.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MemPickKeywordService {
	// 회원 관심사 list select
	public List<MemPickKeywordVO> selectPickKeywordList(String memberId);
		
	// 회원 관심사 insert
	public void insertPickKeyword(MemPickKeywordVO memPickKeywordVO);
		
	// 회원 관심사 delete
	public void deletePickKeyword(MemPickKeywordVO memPickKeywordVO);
}
