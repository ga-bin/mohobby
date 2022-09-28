package com.yedam.mohobby.lesson.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ClassBoardVO {
    private int boardId;
    private String classId;
    private String memberId;
    private int boardType;
    private String title;
    private String content;
    private int rate;
    private int replyCheck;
    private Date writeDate;
}
