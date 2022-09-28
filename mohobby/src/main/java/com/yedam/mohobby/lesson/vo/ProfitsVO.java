package com.yedam.mohobby.lesson.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ProfitsVO {
    private String classId;
    private int price;
    private int withdrawable;
    private Date standardDate;
    private int withdrawCheck;
}
