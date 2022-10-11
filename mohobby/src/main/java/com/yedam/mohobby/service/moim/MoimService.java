
package com.yedam.mohobby.service.moim;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MoimService {
	//소모임 등록
	public void moimInsert(MoimVO moimVO);
	//전체조회
	public List<MoimVO> moimAllSelect();
	//소모임 멤버 모집
	public List<MoimVO> moimrecruitMember();
	
	//인기목록 조회
	public List<MoimVO> moimPopularSelect();
	
	//소모임명 조회
	public List<MoimVO> moimNameSelect();
	
	//소모임 카테고리 조회
	public List<MoimVO> moimCatgSelect();
	
	//소모임 종합 검색
	public List<MoimVO> moimAllSearch();
}

