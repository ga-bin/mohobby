package com.yedam.mohobby.service.moim;

import lombok.Data;

@Data
public class MoimDutchPtp {
	private int dutchId; //n빵id
	private String memberId; //회원아이디
	private int calcPrice; //정산금액
	private int calcCheck; //정산여부
}
