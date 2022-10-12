package com.yedam.mohobby.web.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.yedam.mohobby.service.chat.ChatNoticeVO;
import com.yedam.mohobby.service.chat.ChatVO;
import com.yedam.mohobby.service.chat.ContentVO;


@Controller
public class MessageController {

	@Autowired
	SimpMessageSendingOperations sendTemplate;
	@MessageMapping("/send")
	public void send(ContentVO content) {
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
	sendTemplate.convertAndSend("/topic/room/"+content.getRoomNo(),content);
	}
	@MessageMapping("/getSubscribeInfo")
	public void getSubscribeInfo(String RoomNo) {
		sendTemplate.convertAndSend("/topic/room/"+RoomNo,RoomNo);
	}
	@MessageMapping("/chatNotice")
	public void chatNotice(ContentVO content) {
		sendTemplate.convertAndSend("/"+content.getMemberId(),content);
	}
	
}
