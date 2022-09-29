package com.yedam.mohobby.service.communal;

import java.util.Date;

import lombok.Data;

@Data
//댓글
public class CommentsVO {
    private int commId; //댓글번호
    private String memberId; //회원아이디
    private int parentCommId; //상위댓글번호
    private int targetId; //타겟아이디
    private int targetType; // 타겟유형
    private String content; // 댓글내용
    private Date writeDate; // 등록일자
}
