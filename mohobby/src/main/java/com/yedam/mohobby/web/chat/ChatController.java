package com.yedam.mohobby.web.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.chat.ChatService;
import com.yedam.mohobby.service.chat.ChatUserVO;
import com.yedam.mohobby.service.chat.ChatVO;
import com.yedam.mohobby.service.chat.ChatVO2;
import com.yedam.mohobby.service.chat.CreateRoomVO;
import com.yedam.mohobby.service.chat.NonReadChatVO;
import com.yedam.mohobby.service.chat.RoomVO;
import com.yedam.mohobby.service.communal.AES256Util;
import com.yedam.mohobby.service.notice.NoticeService;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {
	@Autowired
	ChatService cService;
	
	AES256Util aes = new AES256Util();
	
	@Autowired
	NoticeService nService;

	@GetMapping("/getSnsChatRoomNo")
	public CreateRoomVO getSnsChatRoomNo(CreateRoomVO createRoom) {
		cService.getSnsChatRoomNo(createRoom);
		return createRoom;
	}
	
	@GetMapping("/getChatList/{roomNo}")
	public List<ChatVO> getChat(@PathVariable String roomNo) {
		return cService.getChat(roomNo);
	}

	@GetMapping("/ChatRoom/{memberId}")
	public List<RoomVO> getChatRoom(@PathVariable String memberId) {
		return cService.getChatRoom(memberId);
	}

	@GetMapping("/ChatMoimRoom/{memberId}")
	public List<RoomVO> getChatMoimRoom(@PathVariable String memberId) {
		return cService.getChatMoimRoom(memberId);
	}

	@PostMapping("/InsertMessage")
	public int insertMessage(@RequestBody ChatVO2 chat) throws Exception {
		chat.setContent(aes.encrypt(chat.getContent()));
		return cService.insertMessage(chat);
	}

	// 대화상대아이디 가져오기
	@PostMapping("/getTargetId")
	public List<String> getTargetId(@RequestBody ChatUserVO chatUser) {
		return cService.getTargetId(chatUser);
	}

	// 안읽은 메시지 개수 가져오기
	@PostMapping("/getNonReadChat")
	public List<NonReadChatVO> getNonReadChat(@RequestBody ChatUserVO chatUser) {
		return cService.getNonReadChat(chatUser);
	}
	// 대화방 입장상태 최신화
	@PostMapping("/updateCheckTime")
	public int updateCheckTime(@RequestBody ChatUserVO chatUser) {
		return cService.updateCheckTime(chatUser);
	}
	@DeleteMapping("/deleteNotice")
	public int deleteNotice(@RequestParam int noticeId) {
		return nService.delectNotice(noticeId);
	}
}