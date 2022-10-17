package com.yedam.mohobby.service.sns;

import lombok.Data;

@Data
public class SnsBookmarkVO {
	private int catgId; //저장카테고리
	private int postId; //게시글id
    private String memberId; //회원아이디
    private String catgName; //카테고리이름
}
