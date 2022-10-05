package com.yedam.mohobby.service.user;

import lombok.Data;

@Data
public class MypageMoimVO {
	private String moimName; //소모임 명
	private String moimImg; //소모임사진
	private String moimCatg; //소모임 카테고리
	private String moimRegion; //소모임 지역
	private int regCnt; //가입 인원
	
}
