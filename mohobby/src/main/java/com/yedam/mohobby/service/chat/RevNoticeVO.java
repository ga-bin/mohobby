package com.yedam.mohobby.service.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevNoticeVO {
	private String content;
	private int roomNo;
	private String msgTime;
}
