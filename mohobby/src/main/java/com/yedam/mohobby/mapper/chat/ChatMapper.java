package com.yedam.mohobby.mapper.chat;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yedam.mohobby.service.chat.ChatUserVO;
import com.yedam.mohobby.service.chat.ChatVO;
import com.yedam.mohobby.service.chat.CreateRoomVO;
import com.yedam.mohobby.service.chat.MessageVO;
import com.yedam.mohobby.service.chat.RoomVO;

@Mapper
public interface ChatMapper {
public List<RoomVO>getChatRoom(String memberId);
public List<RoomVO>getChatMoimRoom(String memberId);
public List<ChatVO>getChat(String roomNo);
public ChatUserVO getOtherUser(ChatUserVO chatUser);
public int insertMessage(MessageVO message);
public int createRoom(CreateRoomVO cr);
public List<ChatVO>getTargetId(String roomNo);
public ChatVO getprofileImg(ChatVO chat);

}
