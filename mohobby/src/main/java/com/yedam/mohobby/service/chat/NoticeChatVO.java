package com.yedam.mohobby.service.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class NoticeChatVO {
    private String targetId;
    private String myId;
	private String profileImge;
	private String nickname;
	private int contentType; //0 : 댓글 1:좋아요
}
