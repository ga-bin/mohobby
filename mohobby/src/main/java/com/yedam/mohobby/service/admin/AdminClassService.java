package com.yedam.mohobby.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yedam.mohobby.service.classes.ClassBoardVO;
import com.yedam.mohobby.service.classes.ClassesVO;

@Service
public interface AdminClassService {
	// 	강의 목록
	public List<AdminClassVO> getAuditClassList();
		
	// 승인 신청한 강의 정보(vue에서 상세 설명 경로 가지고오기)
		
	// 신청 결과 업로드
	public void updateAuditStatus(ClassesVO classesVO);

	// 강사, 강의, 정산 정보, 지급 여부 등등
	
	// qna리스트 전체 가지고 오기
	public List<ClassBoardVO> getAllQnAList();
			
	// 선택한 클래스의 qna리스트 가지고 오기
	public List<ClassBoardVO> getQnAList(int classId);

	// qna답변 여부 업데이트
	public void updateQnAAnswer(ClassBoardVO classBoardVO);
}
