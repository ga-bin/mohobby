package com.yedam.mohobby.service.chat;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendNoticeVO {
private List<String>memberId;
private String content;
private int roomNo;
private String msgTime;
}
