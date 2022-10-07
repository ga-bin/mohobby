package com.yedam.mohobby.web.sns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.yedam.mohobby.service.chat.ChatVO;


@Controller
public class MessageController {

	@Autowired
	SimpMessageSendingOperations sendTemplate;
	@MessageMapping("/chat")
	public void send(ChatVO chat) {
	sendTemplate.convertAndSend("/topic/room/"+chat.getRoomId(),chat.getContent());
	}
}
