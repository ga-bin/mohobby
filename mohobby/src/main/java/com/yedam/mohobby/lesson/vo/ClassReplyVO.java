package com.yedam.mohobby.lesson.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ClassReplyVO {
    private int replyId;
    private int boardId;
    private String memberId;
    private String content;
    private Date writeDate;
}
