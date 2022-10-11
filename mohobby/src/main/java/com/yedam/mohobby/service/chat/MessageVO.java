package com.yedam.mohobby.service.chat;

import lombok.Data;

@Data
public class MessageVO {
	private String chatId;
	private int roomNo;
	private String msgTime;
	private String content;
	private String memberId;
}
