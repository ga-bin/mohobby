package com.yedam.mohobby.mapper.user;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.mohobby.service.moim.MoimMemberVO;
import com.yedam.mohobby.service.moim.MoimVO;
import com.yedam.mohobby.service.user.MypageMoimVO;

@Mapper
public interface MypageMoimMapper {
	// 유저가 공개중인 소모임 목록
	public List<MoimVO> getProfileMoim(String memberId);
	
	// 유저가 공개중인 소모임 목록 수정
	public void updateProfileMoim(MoimMemberVO moimMemberVO);
	
	// 유저가 참여한 소모임 목록(카테고리별 조회)
	public List<MypageMoimVO> getTakeMoim(HashMap<String, String> map);

	// 유저가 운영중인 소모임 목록(카테고리별 조회)
	public List<MypageMoimVO> getManageMoim(HashMap<String, String> map);
	
}
