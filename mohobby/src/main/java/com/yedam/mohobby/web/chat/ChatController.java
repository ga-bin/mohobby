package com.yedam.mohobby.web.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.chat.ChatService;
import com.yedam.mohobby.service.chat.ChatVO;
import com.yedam.mohobby.service.chat.RoomVO;



@RestController
@CrossOrigin(origins = "*")
public class ChatController {
	@Autowired
	ChatService service;

	@GetMapping("/ChatList/{roomNo}")
	public List<ChatVO> allRoom(@PathVariable String roomNo) {
		return service.getChat(roomNo);
	}
	@GetMapping("/ChatRoom/{memberId}")
	public List<RoomVO> allRoom1(@PathVariable String memberId) {
		return service.getChatRoom(memberId);
	}
}
