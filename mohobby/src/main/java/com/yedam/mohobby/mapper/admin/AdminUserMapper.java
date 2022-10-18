package com.yedam.mohobby.mapper.admin;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.mohobby.service.admin.FlaggingVO;

@Mapper
public interface AdminUserMapper {

	// 각 달별로 가입한 회원 수
	public int signUpMemList(List<HashMap<String, String>> dateList);
	
	// 각 달별로 탈퇴한 회원 수
	public int leaveMemList(List<HashMap<String, String>> dateList);
	
	// 신고된 유저 목록
	public List<FlaggingVO> flagUserList();
	
	// 신고된 유저 정보 업데이트
	public void updateFlagUser(FlaggingVO flaggingVO);

	//신고하기
	public void insertFlagging(FlaggingVO flaggingVO);
}
