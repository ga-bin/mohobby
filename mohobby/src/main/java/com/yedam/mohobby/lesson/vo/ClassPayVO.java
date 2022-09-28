package com.yedam.mohobby.lesson.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ClassPayVO {
    private int payId;
    private String classId;
    private String memberId;
    private int deliveryFee;
    private String receiver;
    private String phoneNum;
    private String postcode;
    private String address;
    private String addressDetail;
    private String deliveryMemo;
    private int classPrice;
    private int needsPrice;
    private int deliveryPrice;
    private int payPrice;
    private String payType;
    private Date payDate;
}
