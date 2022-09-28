package com.yedam.mohobby.challengers.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ChallengersVO {
    private int challId;
    private int challCRate;
    private int categorieId;
    private String challName;
    private Date challDateStart;
    private Date challDateEnd;
    private String challContent;
    private int challParticipants;
    private String challImgUrl;
    private int challRewardAdd;
    private int challRewar;
}
