package com.yedam.mohobby.serviceImpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.user.MypageMoimMapper;
import com.yedam.mohobby.service.user.MypageMoimService;

@Service
public class MypageMoimServiceImpl implements MypageMoimService {
	
	@Autowired
	MypageMoimMapper mapper;
}
