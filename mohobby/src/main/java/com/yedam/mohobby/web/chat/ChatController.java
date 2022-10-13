package com.yedam.mohobby.web.chat;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Param;
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
import com.yedam.mohobby.service.chat.ChatUserVO;
import com.yedam.mohobby.service.chat.ChatVO;
import com.yedam.mohobby.service.chat.CreateRoomVO;
import com.yedam.mohobby.service.chat.MessageVO;
import com.yedam.mohobby.service.chat.NonReadChatVO;
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
	public List<RoomVO> getChatRoom(@PathVariable String memberId) {
		return service.getChatRoom(memberId);
	}
	@GetMapping("/ChatMoimRoom/{memberId}")
	public List<RoomVO> getChatMoimRoom(@PathVariable String memberId) {
		return service.getChatMoimRoom(memberId);
	}
	
	@PostMapping("/InsertMessage")
	public int insertMessage(@RequestBody MessageVO message) {
		System.out.println(message);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return service.insertMessage(message);
	}
	
	@PostMapping("/CreateRoom")
	public int CreateRoom(@RequestBody CreateRoomVO cr) {
		return service.CreateRoom(cr);
	}
	//대화상대아이디 가져오기
	@PostMapping("/getTargetId")
	public List<String>getTargetId(@RequestBody ChatUserVO chatUser){
		return service.getTargetId(chatUser);
	}
	//안읽은 메시지 개수 가져오기
	@PostMapping("/getNonReadChat")
	public List<NonReadChatVO>getNonReadChat(@RequestBody ChatUserVO chatUser){
	return service.getNonReadChat(chatUser);	
	}
}
