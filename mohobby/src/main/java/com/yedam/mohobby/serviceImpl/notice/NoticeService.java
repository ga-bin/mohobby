package com.yedam.mohobby.serviceImpl.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.chat.NoticeMapper;
import com.yedam.mohobby.service.notice.NoticeVO;

@Service
public class NoticeService implements com.yedam.mohobby.service.notice.NoticeService {

	@Mapper
	NoticeMapper nMapper;
	
	@Override
	public List<NoticeVO> getAllNotice(String memberId) {
		return nMapper.getAllNotice(memberId);
	}

	@Override
	public int insertNotice(NoticeVO notice) {
		return nMapper.insertNotice(notice);
	}

}
