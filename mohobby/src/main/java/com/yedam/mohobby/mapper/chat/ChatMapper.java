package com.yedam.mohobby.mapper.chat;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.mohobby.service.chat.ChatUserVO;
import com.yedam.mohobby.service.chat.ChatVO;
import com.yedam.mohobby.service.chat.CreateRoomVO;
import com.yedam.mohobby.service.chat.MessageVO;
import com.yedam.mohobby.service.chat.NonReadChatVO;
import com.yedam.mohobby.service.chat.RoomVO;

@Mapper
public interface ChatMapper {
public List<RoomVO>getChatRoom(String memberId);
public List<RoomVO>getChatMoimRoom(String memberId);
public List<ChatVO>getChat(String roomNo);
public List<String>getTargetId(ChatUserVO chatUser);
public int insertMessage(MessageVO message);
public int createRoom(CreateRoomVO cr);
public List<NonReadChatVO>getNonReadChat(ChatUserVO chatUser);
}
