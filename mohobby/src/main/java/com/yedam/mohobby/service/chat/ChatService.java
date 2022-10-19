package com.yedam.mohobby.service.chat;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface ChatService {
public List<RoomVO>getChatRoom(String memberId);
public List<RoomVO>getChatMoimRoom(String memberId);
public List<ChatVO>getChat(String roomNo);
public List<String> getTargetId(ChatUserVO chatUser);

public List<NonReadChatVO>getNonReadChat(ChatUserVO chatUser);
public int insertMessage(ChatVO2 chat);
public int updateCheckTime(ChatUserVO chatUser);

public void getSnsChatRoomNo(CreateRoomVO creatRoom);
}
