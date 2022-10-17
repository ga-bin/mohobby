package com.yedam.mohobby.service.notice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVO {
	private String memberId;
	private String noticeAvatar;
	private String noticeTitle;
	private String noticeSubtitle;
}
