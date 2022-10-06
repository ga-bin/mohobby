package com.yedam.mohobby.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface AdminMoimService {
	// 신고된 소모임 목록
	public List<FlaggingVO> flagMoimList();
		
	// 신고된 소모임 정보 update
	public void updateflagMoim(FlaggingVO flaggingVO);
}
