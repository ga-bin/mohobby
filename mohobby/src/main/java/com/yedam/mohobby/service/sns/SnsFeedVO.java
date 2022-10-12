package com.yedam.mohobby.service.sns;

import java.util.Date;

import lombok.Data;

@Data
public class SnsFeedVO {
		private int likeStatus; //좋아요상태
        private int postId; //게시글번호
        private String memberId; //유저아이디
        private String hashtag; //해시태그
        private String content; //내용
        private Date writeDate; //피드등록일
        private String thumbnail; //대표이미지
        private int likes; //좋아요 수
        private int cmts; //댓글 수
        private String profileImg; //프로필이미지
        private String intro; //회원설명
        private String nickName; //닉네임
}
