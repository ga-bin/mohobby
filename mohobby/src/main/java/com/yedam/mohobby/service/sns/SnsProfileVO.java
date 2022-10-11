package com.yedam.mohobby.service.sns;

import lombok.Data;

@Data
public class SnsProfileVO {
    private int memberId;
    private String nickname;
    private String profileImg;
    private int postCnt;
    private int followerCnt;
    private int followingCnt;
}
