package com.yedam.mohobby.mapper.chat;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.mohobby.service.chat.ChatVO;
import com.yedam.mohobby.service.chat.RoomVO;

@Mapper
public interface ChatMapper {
public List<RoomVO>getChatRoom(String memberId);
public List<ChatVO>getChat(String roomNo);
}
