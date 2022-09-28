package com.yedam.mohobby.lesson.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ScheduleVO {
    private int moimScheduleId;
    private int moimFolder;
    private String moimScheduleTitle;
    private String moimScheduleExplain;
    private Date moimScheduleStart;
    private Date moimScheduleEnd;
    private Date moimDateStart;
    private Date moimDateEnd;
}
