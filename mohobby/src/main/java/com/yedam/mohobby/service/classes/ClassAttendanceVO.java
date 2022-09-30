package com.yedam.mohobby.service.classes;

import java.util.Date;

import lombok.Data;

@Data
public class ClassAttendanceVO {
    private int currId; //커리큘럼id
    private String memberId; //회원아이디
    private Date attdDate; //출석시간
    private Date outDate; //외출 시간
    private Date comebackDate; //복귀시간
    private Date leaveDate; //조퇴시간
    private Date exitDate; //퇴실시간
    private int recordType; //기록유형 0:출석, 1:조퇴, 2:외출, 3:결석
    private int lateCheck; //지각확인 0:정상, 1:지각
    private int viewProgress; //시청진행도
}
