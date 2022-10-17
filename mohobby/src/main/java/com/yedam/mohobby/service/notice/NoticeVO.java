package com.yedam.mohobby.service.notice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVO {
	private String memberId;
	private String Avatar;
	private String Title;
	private String Subtitle;
	private int noticeId;
	private int postId;
	private int moimId;
	private int noticeType;
	private int boardType;
}
