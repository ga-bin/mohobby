package com.yedam.mohobby.service.admin;

import lombok.Data;

@Data
public class Flagging {
    private int flagId; //신고번호
    private String flagFrom; //신고자
    private String flagTo; //신고대상자
    private String flagCode; //신고코드
    private String flagReason; //신고이유
    private int adminConfirm; //관리자 승인여부
    private int flagResult; //신고결과
}
