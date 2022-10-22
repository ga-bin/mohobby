package com.yedam.mohobby.service.classes;

import lombok.Data;

@Data
public class ClassPayVO {
    private int payId; //주문id
    private String status;
    private String pgType;
    private String pgTid;
    private String pgProvider;
    private String payMethod;
    private int paidAt;
    private int paidAmount;
    private String name;
    private String merchantUid;
    private String impUid;
    private String customData;
    private String currency;
    private int cardQuota;
    private String cardNumber;
    private String cardName;
    private String buyerTel;
    private String buyerPostcode;
    private String buyerName;
    private String buyerEmail;
    private String buyerAddr;
    private String buyerAddrDetail;
    private String bankName;
    private String applyNum;
    private String vbankDate;
    private String vbankHolder;
    private String vbankName;
    private String vbankNum;
    private String memberId; //회원아이디
    private int classId; //강의 아이디
    private int dlvyFee; //배송비
    private String dlvyMemo; //요청사항
    private int classPrice; //수강료
    private int needsPrice; //준비물금액
    private int point;  //사용한포인트
    private int classStatus;  //0:수강중, 1:수강완료
}
