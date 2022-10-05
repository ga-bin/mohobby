package com.yedam.mohobby.serviceImpl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.user.MemPickKeywordMapper;
import com.yedam.mohobby.service.admin.MemPickKeywordVO;
import com.yedam.mohobby.service.user.MemPickKeywordService;

@Service
public class MemPickKeywordServiceImpli implements MemPickKeywordService {

	@Autowired
	MemPickKeywordMapper mapper;
	
	// 회원 관심사 list select
	@Override
	public List<MemPickKeywordVO> selectPickKeywordList(String memberId) {
		return mapper.selectPickKeywordList(memberId);
	}

	// 회원 관심사 insert
	@Override
	public void insertPickKeyword(MemPickKeywordVO memPickKeywordVO) {
		mapper.insertPickKeyword(memPickKeywordVO);
	}

	// 회원 관심사 delete
	@Override
	public void deletePickKeyword(MemPickKeywordVO memPickKeywordVO) {
		mapper.deletePickKeyword(memPickKeywordVO);
	}

}
