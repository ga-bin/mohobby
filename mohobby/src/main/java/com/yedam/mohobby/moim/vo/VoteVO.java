package com.yedam.mohobby.moim.vo;

import java.util.Date;

import lombok.Data;

@Data
public class VoteVO {
    private int moimVoteId;
    private int moimFolder;
    private int moimId;
    private Date moimVoteStart;
    private Date moimVoteEnd;
}
