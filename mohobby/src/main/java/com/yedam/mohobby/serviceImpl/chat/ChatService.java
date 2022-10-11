package com.yedam.mohobby.serviceImpl.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.chat.ChatMapper;
import com.yedam.mohobby.service.chat.ChatUserVO;
import com.yedam.mohobby.service.chat.ChatVO;
import com.yedam.mohobby.service.chat.MessageVO;
import com.yedam.mohobby.service.chat.RoomVO;

@Service
public class ChatService implements com.yedam.mohobby.service.chat.ChatService {

	@Autowired
	ChatMapper mapper;

	@Override
	public List<RoomVO> getChatRoom(String memberId) {
		return mapper.getChatRoom(memberId);
	}

	@Override
	public List<ChatVO> getChat(String roomNo) {
		return mapper.getChat(roomNo);
	}

	@Override
	public ChatUserVO getOtherUser(ChatUserVO chatUserVO) {
		return mapper.getOtherUser(chatUserVO);
	}

	@Override
	public int insertMessage(MessageVO message) {
return mapper.insertMessage(message);		
	}

}
