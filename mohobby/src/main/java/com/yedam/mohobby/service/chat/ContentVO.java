package com.yedam.mohobby.service.chat;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentVO {
	private String roomNo;
	private String content;
	private String memberId;
	private String hour;
}
