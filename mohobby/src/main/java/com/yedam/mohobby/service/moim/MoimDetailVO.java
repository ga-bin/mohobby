package com.yedam.mohobby.service.moim;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
//댓글 단건조회 VO
public class MoimDetailVO {
	
	private String boardContent;
	private String boardWriter;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date writeDate;
	private String content; //댓글 내용
	private String commentWriter;
	private int moimId;
	private int boardType;
	private int boardId;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date commentDate;
	
}
