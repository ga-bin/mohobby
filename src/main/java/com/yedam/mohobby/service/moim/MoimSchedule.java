package com.yedam.mohobby.service.moim;

import java.util.Date;

import lombok.Data;

@Data
public class MoimSchedule {
	private int skedId; //일정 아이디
	private String memberId; //회원아이디
	private String title; //일정제목
	private String info; //일정설명
	private Date startTime; //일정 시작시간
	private Date endTime; //일정 종료시간
	private Date startDate; //시작일자
	private Date endDate; //종료일자
}
