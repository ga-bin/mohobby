package com.yedam.mohobby.challengers.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ChallCVO {
    private int challNoId;
    private String memberId;
    private Date challCDate;
    private String challCUrl;
    private int challCApprove;
    private int challId;
}
