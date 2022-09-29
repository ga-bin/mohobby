package com.yedam.mohobby.service.challenge;

import java.util.Date;

import lombok.Data;

@Data
//챌린지
public class ChallengeVO {
    private int challId; //챌린지 ID
    private int howManyCert; // 하루 인증 횟수
    private String challName; //챌린지명
    private Date startDate; // 시작일자
    private Date endDate; // 종료일자
    private String challInfo; //챌린지설명
    private int challPtp; //참여인원수
    private String imgUrl; //사진주소
    private int rewardBonus; // 추가상금
    private int reward; //상금
    private String keywordId; //키워드 아이디
}