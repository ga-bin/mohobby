package com.yedam.mohobby.service.chat;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ChatService {
public List<RoomVO>getChatRoom(String memberId);
public List<ChatVO>getChat(String roomNo);
public ChatUserVO getOtherUser(ChatUserVO chatUserVO);
public int insertMessage(MessageVO message);
}
