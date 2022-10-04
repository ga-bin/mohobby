package com.yedam.mohobby.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.user.MemberMapper;
import com.yedam.mohobby.service.admin.MemberService;
import com.yedam.mohobby.service.admin.MemberVO;

@Service
public class MemberServiceImpi implements MemberService{
	
	@Autowired
	MemberMapper mapper;
	
	@Override
	public MemberVO getMember(String memberId) {
		return mapper.getMember(memberId);
	}
}
