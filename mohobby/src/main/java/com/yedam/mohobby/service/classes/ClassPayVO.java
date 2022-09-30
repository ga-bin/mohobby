package com.yedam.mohobby.service.classes;

import java.util.Date;

import lombok.Data;

@Data
public class ClassPayVO {
    private int payId; //주문id
    private String memberId; //회원아이디
    private int classId; //강의 아이디
    private int dlvyFee; //배송비
    private String receiver; //수령인
    private String phoneNum; //휴대폰번호
    private String postcode; //우편번호
    private String address; //주소
    private String addressDetail; //상세주소
    private String dlvyMemo; //요청사항
    private int classPrice; //수강료
    private int needsPrice; //준비물금액
    private int totlaPrice; //결제금액 
    private String payType; //결제방식 카드결제,카카오페이,네이버페이,토스
    private Date payDate; //결제시간
    private String payCode; //결제코드
    private int point;  //사용한포인트
    private int classStatus;  //0:수강중, 1:수강완료
}
