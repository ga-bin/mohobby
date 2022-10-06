package com.yedam.mohobby.serviceImpl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.admin.AdminMoimMapper;
import com.yedam.mohobby.service.admin.AdminMoimService;
import com.yedam.mohobby.service.admin.FlaggingVO;

@Service
public class AdminMoimServiceImpl implements AdminMoimService {

	@Autowired
	AdminMoimMapper mapper;

	@Override
	public List<FlaggingVO> flagMoimList() {
		return mapper.flagMoimList();
	}

	@Override
	public void updateflagMoim(FlaggingVO flaggingVO) {
		mapper.updateflagMoim(flaggingVO);
	}
}
