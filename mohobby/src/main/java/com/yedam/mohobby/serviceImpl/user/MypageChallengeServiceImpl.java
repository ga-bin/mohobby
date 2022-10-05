package com.yedam.mohobby.serviceImpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.user.MypageChallengeMapper;
import com.yedam.mohobby.service.user.MypageChallengeService;

@Service
public class MypageChallengeServiceImpl implements MypageChallengeService {

	@Autowired
	MypageChallengeMapper mapper;
}
