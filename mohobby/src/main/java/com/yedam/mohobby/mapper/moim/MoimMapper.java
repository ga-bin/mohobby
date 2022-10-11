package com.yedam.mohobby.mapper.moim;

import java.util.List;

import com.yedam.mohobby.service.moim.MoimVO;

public interface MoimMapper {
	//전체조회
	public List<MoimVO> moimAllSelect();
	
	//소모임 등록
	public void moimInsert (MoimVO moimVO);

	//소모임 모집 멤버
	public List<MoimVO> moimrecruitMember();
	
	//인기소모임 조회
	public List<MoimVO> moimPopularSelect();
	
	//소모임명 조회
	public List<MoimVO> moimNameSelect();
	
	//소모임 카테고리 조회
	public List<MoimVO> moimCatgSelect();
	
	//소모임 종합 검색
	public List<MoimVO> moimAllSearch();
}
