package com.yedam.mohobby.service.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResNoticeVO {
	private String targetId;
	private String myId;
	private String profileImge;
	private String nickname;
	private int postId;
	private int boardId;
	private int moimId;
	private int contentType=-1; // 0 : 댓글 1:좋아요
	private int likeStatus; // 0 : 누르지않은 상태 1:누른상태
	private int boardType; // 0: sns , 1:소모임, 2:강의
}
