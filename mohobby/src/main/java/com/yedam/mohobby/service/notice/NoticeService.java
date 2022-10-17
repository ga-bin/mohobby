package com.yedam.mohobby.service.notice;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface NoticeService {
	public List<NoticeVO> getAllNotice(String memberId);
	public int insertNotice(NoticeVO notice);
}
