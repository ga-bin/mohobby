package com.yedam.mohobby.service.sns;

import java.util.Date;

import lombok.Data;

@Data
public class SnsPostVO {
private int postId;
private String memberId;
private String hashtag;
private String content;
private Date writeDate;
private String thumbnail; //대표이미지
}
