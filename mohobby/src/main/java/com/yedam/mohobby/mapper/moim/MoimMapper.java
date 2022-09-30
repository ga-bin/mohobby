package com.yedam.mohobby.mapper.moim;

import java.util.List;

import com.yedam.mohobby.service.moim.MoimVO;

public interface MoimMapper {
	//전체조회
	public List<MoimVO> moimAllSelect();
	//소모임 등록
	public void moimInsert (MoimVO moimVO);

	
	//인기소모임 조회
	public List<MoimVO> moimPopularSelect();
	
	//소모임명 조회
	public List<MoimVO> moimNameSelect();
}
