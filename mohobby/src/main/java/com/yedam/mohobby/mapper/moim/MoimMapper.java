package com.yedam.mohobby.mapper.moim;

import java.util.HashMap;
import java.util.List;

import com.yedam.mohobby.service.moim.MoimBoardVO;
import com.yedam.mohobby.service.moim.MoimVO;

public interface MoimMapper {
	
	//소모임 등록
	public void moimInsert (MoimVO moimVO);

	//소모임 멤버 모집 조회(6개씩)
	public List<MoimVO> moimrecruitMember();
	
	//인기소모임 조회
	public List<MoimVO> moimPopularSelect();
	
	//소모임명 조회
	public List<MoimVO> moimNameSelect();
	
	//소모임 카테고리 조회
	public List<MoimVO> moimCatgSelect();
	
	//소모임 종합 검색
	public List<MoimVO> moimAllSearch(HashMap<String, String> map);
	
	//소모임 게시판 전체 리스트 조회
	public List<MoimBoardVO> moimAllBoard(HashMap<String, Integer> map);
	
	//소모임 모임이름 중복검사
	public int moimIdCheck(String moimName);
}
