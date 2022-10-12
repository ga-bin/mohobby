package com.yedam.mohobby.serviceImpl.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.main.MainMapper;
import com.yedam.mohobby.service.main.MainChallVO;
import com.yedam.mohobby.service.main.MainClassVO;
import com.yedam.mohobby.service.main.MainService;
import com.yedam.mohobby.service.moim.MoimVO;
import com.yedam.mohobby.service.sns.SnsPostVO;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	MainMapper mapper;
	
	@Override
	public List<SnsPostVO> SnsHighLikesList() {
		return mapper.SnsHighLikesList();
	}

	@Override
	public List<MoimVO> MoimPopularList() {
		return mapper.MoimPopularList();
	}

	@Override
	public List<MainClassVO> collectClassList() {
		return mapper.collectClassList();
	}

	@Override
	public List<MainChallVO> challNewCertList() {
		return mapper.challNewCertList();
	}

}
