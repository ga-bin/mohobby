package com.yedam.mohobby.service.moim;

import lombok.Data;

@Data
public class MoimMemberVO {
	private String MemberId; //회원아이디
	private int MoimId; //소모임아이디
	private int memberRole; //권한 0:일반회원 1:관리자
}
