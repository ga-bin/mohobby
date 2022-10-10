package com.yedam.mohobby.service.chat;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ChatVO {
	private String roomNo;
	private String content;
	private String memberId;
	private String minute;
	private String hour;
}
