package com.yedam.mohobby.service.admin;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
	public MemberVO getMember(String memberId);
}
