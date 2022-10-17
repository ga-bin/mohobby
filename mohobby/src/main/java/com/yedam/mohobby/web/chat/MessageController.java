//package com.yedam.mohobby.web.chat;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.stereotype.Controller;
//
//import com.yedam.mohobby.service.chat.ContentVO;
//import com.yedam.mohobby.service.chat.NoticeService;
//import com.yedam.mohobby.service.chat.NoticeVO;
//import com.yedam.mohobby.service.chat.RevNoticeVO;
//import com.yedam.mohobby.service.chat.SendNoticeVO;
//import com.yedam.mohobby.service.chat.SnsNoticeVO;
//import com.yedam.mohobby.service.user.MemberService;
//
//@Controller
//public class MessageController {
//
//	@Autowired
//	MemberService mService;
//
//	@Autowired
//	NoticeService nService;
//
//	@Autowired
//	SimpMessageSendingOperations sendTemplate;
//
//	@MessageMapping("/getSubscribeInfo")
//	public void getSubscribeInfo(String RoomNo) {
//		sendTemplate.convertAndSend("/topic/room/" + RoomNo, RoomNo);
//	}
//
//	@MessageMapping("/send")
//	public void send(ContentVO content) {
//		System.out.println(content);
//		sendTemplate.convertAndSend("/topic/room/" + content.getRoomNo(), content);
//	}
//
//	@MessageMapping("/getSubscribeId")
//	public void rev(String RoomNo) {
//		sendTemplate.convertAndSend("/topic/room/" + RoomNo, RoomNo);
//	}
//
//	@MessageMapping("/sendNotice")
//	public void sendNotice(SendNoticeVO sendNotice) {
//		RevNoticeVO revNotice = new RevNoticeVO();
//		revNotice.setContent(sendNotice.getContent());
//		revNotice.setRoomNo(sendNotice.getRoomNo());
//		revNotice.setMsgTime(sendNotice.getMsgTime());
//		for (int i = 0; i < sendNotice.getMemberId().size(); i++) {
//			sendTemplate.convertAndSend("/queue/" + sendNotice.getMemberId().get(i), revNotice);
//		}
//	}
//
//	@MessageMapping("/chatNotice")
//	public void chatNotice(ContentVO content) {
//		sendTemplate.convertAndSend("/" + content.getMemberId(), content);
//	}
//
//	// sns알림
//	@MessageMapping("/NoticeSnsLike")
//	public void NoticeSns(SnsNoticeVO snsNotice) {
//		NoticeVO noticeVO = new NoticeVO();
//		snsNotice.setProfileImge(mService.getMember(snsNotice.getMyId()).getProfileImg());
//		snsNotice.setNickname(mService.getMember(snsNotice.getTargetId()).getNickName());
//		noticeVO.setMemberId(snsNotice.getTargetId());
//		noticeVO.setNoticeAvatar(snsNotice.getProfileImge());
//		noticeVO.setNoticeTitle(snsNotice.getNickname());
//		if (snsNotice.getContentType() == 0) {
//			noticeVO.setNoticeSubtitle("좋아요를 눌렀습니다.");
//		}
//		else if(snsNotice.getContentType() ==1) {
//			noticeVO.setNoticeSubtitle("댓글을 남기셨습니다.");
//		}
//		nService.insertNotice(noticeVO);
//		sendTemplate.convertAndSend("/queue/" + snsNotice.getTargetId() + "/sns", snsNotice);
//	}
//}
