package com.yedam.mohobby.serviceImpl.moim;

import java.util.HashMap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.moim.MoimMapper;
import com.yedam.mohobby.service.moim.MoimService;
import com.yedam.mohobby.service.moim.MoimVO;

@Service
public class MoimServiceImpl implements MoimService{


	@Autowired
	MoimMapper mapper;

	//소모임 등록
	@Override
	public void moimInsert(MoimVO moimVO) {
		mapper.moimInsert(moimVO);	
	}

	//소모임 전체조회
	@Override
	public List<MoimVO> moimAllSelect() {
		return mapper.moimAllSelect();
	}

	//소모임 멤버 모집 조회(6개씩)
	@Override
	public List<MoimVO> moimrecruitMember() {
		return mapper.moimrecruitMember();
	}
	
	//인기소모임 조회
	@Override
	public List<MoimVO> moimPopularSelect() {
		return mapper.moimPopularSelect();
	}

	//소모임명 조회
	@Override
	public List<MoimVO> moimNameSelect() {
		return mapper.moimNameSelect();
	}	
	
	//소모임 카테고리 조회
	@Override
	public List<MoimVO> moimCatgSelect() {
		return mapper.moimCatgSelect();
	}

	//소모임 종합 검색
	@Override
	public List<MoimVO> moimAllSearch(String moimName, String moimCatg) {
		HashMap<String, String> map = new HashMap<>();
		map.put("moimName", moimName);
		map.put("moimCatg", moimCatg);
		return mapper.moimAllSearch(map);
	}	

	
	
}

