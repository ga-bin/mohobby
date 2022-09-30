package com.yedam.mohobby.service.classes;

import java.util.Date;

import lombok.Data;

@Data
public class ClassesVO {
	private int classId; // 강의id
	private String keywordId; // 키워드아이디
	private String regionId; // 지역번호
	private String memberId; // 회원아이디
	private String className; // 강의명
	private int classPrice; // 수강료
	private String classInfo; // 상세설명 html 파일 경로
	private int imgAmount; // 이미지 등록개수
	private int classType; // 강의 유형 0:온라인, 1:오프라인
	private int certAble; // 수료증 발급 가능여부 0:발급불가 1:발급가능
	private int certStandard; // 수료증 발급 기준
	private String account_holder;  //예금주
	private String bankCode; // 은행코드
	private String account; // 계좌번호
	private String postcode; // 은행코드 011:농협, 045:새마을, 088:신한, 020:우리, 카카오뱅크:090, 기업:003, 부산:032, 국민:004,
								// 대구:031, 우체국:071, K뱅크:089
	private String address;   //주소
	private String addressDetail; // 상세주소
	private Date startDate; // 시작일자
	private Date endDate; // 종료일자
	private int auditStatus; // 승인상태 0:심사대기, 1:심사중, 2:승인, 3:미승인
	private String auditReturn; // 반려사유
	private int profileYn; //0: 공개 1:비공개
}
