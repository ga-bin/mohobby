package com.yedam.mohobby.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.mohobby.service.user.MemPickKeywordVO;

@Mapper
public interface MemPickKeywordMapper {
	// 회원 관심사 list select
	public List<MemPickKeywordVO> selectPickKeywordList(String memberId);
	
	// 회원 관심사 insert
	public void insertPickKeyword(MemPickKeywordVO memPickKeywordVO);
	
	// 회원 관심사 delete
	public void deletePickKeyword(MemPickKeywordVO memPickKeywordVO);
}
