package com.yedam.mohobby.service.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatVO {
	private String roomId;
	private String content;
}
