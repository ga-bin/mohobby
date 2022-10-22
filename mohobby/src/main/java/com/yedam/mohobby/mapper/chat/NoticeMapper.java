package com.yedam.mohobby.mapper.chat;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yedam.mohobby.service.notice.NoticeVO;

@Mapper
public interface NoticeMapper {
	public List<NoticeVO> getAllNotice(String memberId);
	public int insertNotice(NoticeVO notice);
	public int delectNotice(int noticeId);
	public int getNoticeId();
	//안읽은 메세지수 전체조회
	public int getNonReadAllChat(@Param("memberId")String memberId);
}
