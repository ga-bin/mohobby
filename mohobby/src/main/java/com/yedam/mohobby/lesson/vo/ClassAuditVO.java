package com.yedam.mohobby.lesson.vo;

import lombok.Data;

@Data
public class ClassAuditVO {
    private int auditId;
    private String classId;
    private int auditStatus;
    private String refusal;
}
