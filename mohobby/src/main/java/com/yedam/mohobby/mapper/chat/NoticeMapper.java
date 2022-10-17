package com.yedam.mohobby.mapper.chat;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.mohobby.service.notice.NoticeVO;

@Mapper
public interface NoticeMapper {
public List<NoticeVO> getAllNotice(String memberId);
public int insertNotice(NoticeVO notice);
}
