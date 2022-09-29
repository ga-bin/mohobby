package com.yedam.mohobby.service.challenge;

import java.util.Date;

import lombok.Data;

@Data
//챌린저스 가입 유저
public class ChallMemberVO {
    private int ptpId; // 참가ID
    private int challId; // 챌린지ID
    private String memberId; // 회원아이디
    private int ptpFee; // 등록한 상금
    private int certCnt; // 회원 인증 횟수
    private String bankCode; //은행코드
    private String account; // 계좌번호
    private Date receiveDate; // 금액입금일자
    private int clearYn; //성공여부
    private int receivePrice; // 입금금액
	private int profileYn; //0: 공개 1:비공개
}
