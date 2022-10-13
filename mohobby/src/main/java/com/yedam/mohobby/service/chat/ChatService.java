package com.yedam.mohobby.service.chat;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ChatService {
public List<RoomVO>getChatRoom(String memberId);
public List<RoomVO>getChatMoimRoom(String memberId);
public List<ChatVO>getChat(String roomNo);
public List<String> getTargetId(ChatUserVO chatUser);
public int insertMessage(MessageVO message);
public int CreateRoom(CreateRoomVO cr);
public List<NonReadChatVO>getNonReadChat(ChatUserVO chatUser);
}
