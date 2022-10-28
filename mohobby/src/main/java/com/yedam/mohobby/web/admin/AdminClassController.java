package com.yedam.mohobby.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.admin.AdminClassService;
import com.yedam.mohobby.service.admin.AdminClassVO;
import com.yedam.mohobby.service.classes.ClassBoardVO;
import com.yedam.mohobby.service.classes.ClassesVO;

/**
 * @create 2002/10/06
 * @author gabin
 * @title 관리자 강의
 */
@RestController
@CrossOrigin(origins = "*")
public class AdminClassController {
	
	@Autowired
	AdminClassService service;
	/**
	 * @title 강의목록 
	 * @param adminClassVO
	 * @return
	 */
	@GetMapping("/adminClassList")
	public List<AdminClassVO> getAuditClassList() {
		return service.getAuditClassList();
	};
	
	// 승인 신청한 강의 정보(vue에서 상세 설명 경로 가지고오기)
	
	// 신청 결과 업로드
	@PutMapping("/adminAuditClass")
	public void updateAuditStatus(ClassesVO classesVO) {
		service.updateAuditStatus(classesVO);
	}
	
	// 강사, 강의, 정산 정보, 지급 여부 등등
	
	// qna리스트 전체 가지고 오기
	@GetMapping("/QnAAllList")
	public List<ClassBoardVO> getAllQnAList() {
		return service.getAllQnAList();
	}
	
	// 선택한 클래스의 qna리스트 가지고 오기
	@GetMapping("/QnAList/{classId}")
	public List<ClassBoardVO> getQnAList(@PathVariable int classId) {
		return service.getQnAList(classId);
	}
	
	// qna답변 여부 업데이트
	@PutMapping("/QnAUpdate")
	public void updateQnAAnswer(@RequestBody ClassBoardVO classBoardVO) {
		service.updateQnAAnswer(classBoardVO);
	}
}
