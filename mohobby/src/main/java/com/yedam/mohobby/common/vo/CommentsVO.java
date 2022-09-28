package com.yedam.mohobby.common.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CommentsVO {
    private int commentId;
    private String memberId;
    private int commentNo;
    private int targetId;
    private int targetType;
    private String content;
    private Date writeDate;
    private int depth;
}
