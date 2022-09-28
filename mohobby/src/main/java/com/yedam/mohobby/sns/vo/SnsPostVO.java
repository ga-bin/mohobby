package com.yedam.mohobby.sns.vo;

import java.util.Date;

import lombok.Data;

@Data
public class SnsPostVO {
    private int postId;
    private String postContetns;
    private Date postRegdate;
    private String thumbnail;
    private String memberId;
}
