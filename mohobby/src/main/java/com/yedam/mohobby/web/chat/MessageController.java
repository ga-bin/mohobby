package com.yedam.mohobby.web.chat;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.yedam.mohobby.service.chat.ContentVO;
import com.yedam.mohobby.service.notice.NoticeService;
import com.yedam.mohobby.service.notice.NoticeVO;
import com.yedam.mohobby.service.notice.ResNoticeVO;
import com.yedam.mohobby.service.user.MemberService;
import com.yedam.mohobby.service.chat.RevNoticeVO;
import com.yedam.mohobby.service.moim.MoimService;

@Controller
public class MessageController {

	@Autowired
	MemberService mService;

	@Autowired
	NoticeService nService;

	@Autowired
	MoimService moService;

	@Autowired
	SimpMessageSendingOperations sendTemplate;

	@MessageMapping("/getSubscribeInfo")
	public void getSubscribeInfo(String RoomNo) {
		sendTemplate.convertAndSend("/topic/room/" + RoomNo, RoomNo);
	}

	@MessageMapping("/send")
	public void send(ContentVO content) {
		System.out.println(content);
		sendTemplate.convertAndSend("/topic/room/" + content.getRoomNo(), content);
	}

	@MessageMapping("/getSubscribeId")
	public void rev(String RoomNo) {
		sendTemplate.convertAndSend("/topic/room/" + RoomNo, RoomNo);
	}

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

	@MessageMapping("/chatNotice")
	public void chatNotice(ContentVO content) {
		sendTemplate.convertAndSend("/" + content.getMemberId(), content);
	}

	// 알림
	@MessageMapping("Notice")
	public void NoticeSns(ResNoticeVO resNotice) {
		NoticeVO noticeVO = new NoticeVO();
		//sns 알림
		if (resNotice.getBoardType() == 0) {
			resNotice.setProfileImge(mService.getMember(resNotice.getMyId()).getProfileImg());
			resNotice.setNickname(mService.getMember(resNotice.getMyId()).getNickName());

			noticeVO.setMemberId(resNotice.getTargetId());
			noticeVO.setNoticeAvatar(resNotice.getProfileImge());
			noticeVO.setNoticeTitle(resNotice.getNickname());

			if (resNotice.getContentType() == 0) {
				if (resNotice.getLikeStatus() == 0) {
					noticeVO.setNoticeSubtitle("좋아요를 눌렀습니다.");
				} else if (resNotice.getLikeStatus() == 1) {
					noticeVO.setNoticeSubtitle("좋아요를 취소했습니다.");
				}
			} else if (resNotice.getContentType() == 1) {
				noticeVO.setNoticeSubtitle("댓글을 남기셨습니다.");
			}
		}
		//소모임 알림
		else if (resNotice.getBoardType()==1) {
			resNotice.setProfileImge(moService.getMoimInfo(resNotice.getMyId()).getMoimImg());
			resNotice.setNickname(moService.getMoimInfo(resNotice.getMyId()).getMoimName());
			
			noticeVO.setMemberId(resNotice.getTargetId());
			noticeVO.setNoticeAvatar(resNotice.getProfileImge());
			noticeVO.setNoticeTitle(resNotice.getNickname());
		}
		else if(resNotice.getBoardType()==2) {
			
			
			
		}

		sendTemplate.convertAndSend("/queue/" + resNotice.getTargetId() + "/notice", resNotice);
		//nService.insertNotice(noticeVO);
	}
	// 소모임알리

}
