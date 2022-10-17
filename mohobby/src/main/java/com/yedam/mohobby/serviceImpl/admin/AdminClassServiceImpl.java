package com.yedam.mohobby.serviceImpl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.admin.AdminClassMapper;
import com.yedam.mohobby.service.admin.AdminClassService;
import com.yedam.mohobby.service.admin.AdminClassVO;
import com.yedam.mohobby.service.classes.ClassesVO;

@Service
public class AdminClassServiceImpl implements AdminClassService {

	@Autowired
	AdminClassMapper mapper;

	// 승인 여부에 따른 강의 목록
	@Override
	public List<AdminClassVO> getAuditClassList() {
		return mapper.getAuditClassList();
	}

	// 승인 신청한 강의 정보(vue에서 상세 설명 경로 가지고오기)
	
	// 신청 결과 업로드
	@Override
	public void updateAuditStatus(ClassesVO classesVO) {
		mapper.updateAuditStatus();
	}
	
	// 강사, 강의, 정산 정보, 지급 여부 등등
}
