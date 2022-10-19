package com.yedam.mohobby.service.chat;

import lombok.Data;

@Data
public class CreateRoomVO {
	private String myId;
	private String targetId;
	private int vRoomNo;
}
