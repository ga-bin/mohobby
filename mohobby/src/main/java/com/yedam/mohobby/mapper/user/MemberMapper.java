package com.yedam.mohobby.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.mohobby.service.admin.MemberVO;

@Mapper
public interface MemberMapper {
	public MemberVO getMember(String memberId);
}