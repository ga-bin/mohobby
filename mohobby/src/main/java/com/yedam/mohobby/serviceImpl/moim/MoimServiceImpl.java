package com.yedam.mohobby.serviceImpl.moim;

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


	@Override
	public void moimInsert(MoimVO moimVO) {
		mapper.moimInsert(moimVO);	
	}

	//전체조회
	@Override
	public List<MoimVO> moimAllSelect() {
		return mapper.moimAllSelect();
	}

	//인기목록 조회
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

	
}

