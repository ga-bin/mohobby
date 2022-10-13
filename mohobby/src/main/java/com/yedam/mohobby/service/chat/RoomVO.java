package com.yedam.mohobby.service.chat;

import lombok.Data;

@Data
public class RoomVO {
	private String nickName;
	private int roomNo;
	private String profileImg;
	private String content;
	private String msgTime;
	private String checkIn;
	private String nonReadChat;
	
}
