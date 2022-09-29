package com.yedam.mohobby.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.MoimMapper;
import com.yedam.mohobby.service.moim.MoimService;
import com.yedam.mohobby.service.moim.MoimVO;

@Service
public class MoimServiceImpl implements MoimService{
	
	@Autowired
	MoimMapper moimMapper;
	
	@Override
	public List<MoimVO> getMoimList() {
		return moimMapper.getMoimList();
	}
	

}
