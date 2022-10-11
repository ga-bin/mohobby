package com.yedam.mohobby.web.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.yedam.mohobby.service.chat.ChatVO;
import com.yedam.mohobby.service.chat.ContentVO;


@Controller
public class MessageController {

	@Autowired
	SimpMessageSendingOperations sendTemplate;
	@MessageMapping("/chat")
	public void send(ContentVO content) {
	sendTemplate.convertAndSend("/topic/room/"+content.getRoomNo(),content);
	}
	@MessageMapping("/chat1")
	public void rev(String RoomNo) {
		sendTemplate.convertAndSend("/topic/room/"+RoomNo,RoomNo);
	}
}
