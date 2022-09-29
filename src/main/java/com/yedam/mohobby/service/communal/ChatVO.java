package com.yedam.mohobby.service.communal;

import lombok.Data;

@Data
//채팅
public class ChatVO {
	private int chatId; //채팅룸 ID
	private int moimId; //소모임 ID
	private int chatType; //채팅유형
	private String filePath; //파일경로
	private String msgContent; //마지막 메세지 보낸 내용
    private String msgTime; //마지막 메세지 보낸 시간
}
