package com.yedam.mohobby.lesson.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ClassesVO {
    private String classId;
    private String memberId;
    private int hCatg1Id;
    private String className;
    private int classPrice;
    private String classInfo;
    private int classType;
    private int certAble;
    private String bankCode;
    private String account;
    private String postcode;
    private String address;
    private String addressDetail;
    private Date startDate;
    private Date endDate;
}
