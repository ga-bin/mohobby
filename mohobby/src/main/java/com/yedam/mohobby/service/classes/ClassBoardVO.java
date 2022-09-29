package com.yedam.mohobby.service.classes;

import java.util.Date;

import lombok.Data;

@Data
public class ClassBoardVO {
    private int boardId; //게시글번호
    private String memberId; //회원아이디
    private int classId; //강의id
    private int boardType; //글 유형
    private String title; //글 제목
    private String content; //글 내용
    private int rate; //점수
    private int replyCheck; //답변처리
    private Date writeDate; //작성시간
}
