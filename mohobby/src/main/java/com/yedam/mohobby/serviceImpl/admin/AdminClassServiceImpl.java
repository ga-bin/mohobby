package com.yedam.mohobby.serviceImpl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.admin.AdminClassMapper;
import com.yedam.mohobby.service.admin.AdminClassService;
import com.yedam.mohobby.service.admin.AdminClassVO;
import com.yedam.mohobby.service.classes.ClassBoardVO;
import com.yedam.mohobby.service.classes.ClassesVO;

@Service
public class AdminClassServiceImpl implements AdminClassService {

	@Autowired
	AdminClassMapper mapper;

	// 강의 목록
	@Override
	public List<AdminClassVO> getAuditClassList() {
		return mapper.getAuditClassList();
	}

	// 승인 신청한 강의 정보(vue에서 상세 설명 경로 가지고오기)
	
	// 신청 결과 업로드
	@Override
	public void updateAuditStatus(ClassesVO classesVO) {
		mapper.updateAuditStatus(classesVO);
	}
	
	// 강사, 강의, 정산 정보, 지급 여부 등등
	
	// qna리스트 전체 가지고 오기
	public List<ClassBoardVO> getAllQnAList() {
		return mapper.getAllQnAList();
	}
	
	// 선택한 클래스의 qna리스트 가지고 오기
	@Override
	public List<ClassBoardVO> getQnAList(int classId) {
		return mapper.getQnAList(classId);
	}
		
	// qna답변 여부 업데이트
	@Override
	public void updateQnAAnswer(ClassBoardVO classBoardVO) {
		mapper.updateQnAAnswer(classBoardVO);
	}
}
