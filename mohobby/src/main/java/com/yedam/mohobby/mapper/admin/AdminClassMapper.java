package com.yedam.mohobby.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.mohobby.service.admin.AdminClassVO;

@Mapper
public interface AdminClassMapper {

		// 승인 여부에 따른 강의 목록
		public List<AdminClassVO> getAuditClassList();
		
		// 승인 신청한 강의 정보(vue에서 상세 설명 경로 가지고오기)
		
		// 신청 결과 업로드
		public void updateAuditStatus();

		// 강사, 강의, 정산 정보, 지급 여부 등등
}
