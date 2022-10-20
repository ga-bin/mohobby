package com.yedam.mohobby.web.chat;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.yedam.mohobby.service.chat.ChatListContentResVO;
import com.yedam.mohobby.service.chat.ChatService;
import com.yedam.mohobby.service.chat.ContentVO;
import com.yedam.mohobby.service.notice.NoticeService;
import com.yedam.mohobby.service.notice.NoticeVO;
import com.yedam.mohobby.service.notice.ResNoticeVO;
import com.yedam.mohobby.service.user.MemberService;
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
	ChatService cService;

	@Autowired
	SimpMessageSendingOperations sendTemplate;

	@MessageMapping("/getSubscribeInfo")
	public void getSubscribeInfo(String RoomNo) {
		sendTemplate.convertAndSend("/topic/room/" + RoomNo, RoomNo);
	}

	@MessageMapping("/send")
	public void send(ContentVO content) {
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		System.out.println(content);
		
		sendTemplate.convertAndSend("/topic/room/" + content.getRoomNo(), content);
		ChatListContentResVO res = new ChatListContentResVO();
		for (int i = 0; i < content.getMemberIds().size(); i++) {
			if (cService.getCheckIn(content.getRoomNo(), content.getMemberIds().get(i)) == 0)
				res.setContent(content.getContent());
			res.setRoomNo(content.getRoomNo());
			res.setMsgTime(content.getHour());
			sendTemplate.convertAndSend("/queue/" + content.getMemberIds().get(i), content);
		}
	}

	@MessageMapping("/getSubscribeId")
	public void rev(String RoomNo) {
		sendTemplate.convertAndSend("/topic/room/" + RoomNo, RoomNo);
	}

	// 알림
	@MessageMapping("Notice")
	public void NoticeSns(ResNoticeVO resNotice) {
		NoticeVO noticeVO = new NoticeVO();
		resNotice.setNoticeId(nService.getNoticeId());
		noticeVO.setBoardType(resNotice.getBoardType());
		noticeVO.setMoimId(resNotice.getMoimId());
		noticeVO.setPostId(resNotice.getPostId());
		noticeVO.setNoticeType(resNotice.getNoticeType());
		noticeVO.setContentType(resNotice.getContentType());
		// sns 알림
		if (resNotice.getNoticeType() == 0) {
			resNotice.setProfileImge(mService.getMember(resNotice.getMyId()).getProfileImg());
			resNotice.setNickname(mService.getMember(resNotice.getMyId()).getNickName());

			// db에 담을정보
			noticeVO.setMemberId(resNotice.getTargetId());
			noticeVO.setAvatar("require(`@/assets/image/user/" + resNotice.getProfileImge() + "`)");
			noticeVO.setTitle(resNotice.getNickname());
			// sns - 좋아요 클릭시
			if (resNotice.getContentType() == 0) {
				if (resNotice.getLikeStatus() == 0) {
					noticeVO.setSubtitle("좋아요를 눌렀습니다.");
				} else if (resNotice.getLikeStatus() == 1) {
					noticeVO.setSubtitle("좋아요를 취소했습니다.");
				}
				// sns - 댓글 등록시
			} else if (resNotice.getContentType() == 1) {
				noticeVO.setSubtitle("댓글을 남기셨습니다.");
			}
		}
		// 소모임 알림
		else if (resNotice.getNoticeType() == 1) {
			resNotice.setProfileImge(moService.getMoimInfo(resNotice.getMoimId()).getMoimImg());
			resNotice.setNickname(moService.getMoimInfo(resNotice.getMoimId()).getMoimName());

			// db에 담을정보
			noticeVO.setMemberId(resNotice.getTargetId());
			noticeVO.setAvatar("require(`@/assets/image/moim/" + resNotice.getProfileImge() + "`)");
			noticeVO.setTitle(resNotice.getNickname());
			// 소모임 - 댓글
			if (resNotice.getContentType() == 0) {
				noticeVO.setSubtitle("댓글을 남기셨습니다.");
			}
			// 소모임 - 게시글 등록시
			else if (resNotice.getContentType() == 1) {
				noticeVO.setSubtitle("새로운 게시글이 등록되었습니다.");
			}
		} else if (resNotice.getBoardType() == 2) {
		}
		System.out.println(noticeVO);
		sendTemplate.convertAndSend("/queue/" + resNotice.getTargetId() + "/notice", resNotice);
		nService.insertNotice(noticeVO);
	}
}
