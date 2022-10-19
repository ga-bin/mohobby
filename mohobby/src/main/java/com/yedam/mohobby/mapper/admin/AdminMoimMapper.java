package com.yedam.mohobby.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.mohobby.service.admin.FlaggingVO;

@Mapper
public interface AdminMoimMapper {

	// 신고된 소모임 목록
	public List<FlaggingVO> flagMoimList();
	
	// 신고된 소모임 정보 update
	public void updateFlagMoim(FlaggingVO flaggingVO);
	
}
