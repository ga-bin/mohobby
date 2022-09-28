package com.yedam.mohobby.lesson.vo;

import java.util.Date;

import lombok.Data;

@Data
public class AttendanceVO {
    private String memberId;
    private String classId;
    private Date recordDate;
    private int recordType;
}
