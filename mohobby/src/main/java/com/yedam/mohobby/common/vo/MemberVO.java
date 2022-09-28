package com.yedam.mohobby.common.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
    private String memberId;
    private int rCatgId;
    private String nickName;
    private String passWord;
    private Date birth;
    private int rCatgName;
    private String email;
    private String phoneNum;
    private String gender;
    private int userType;
    private String profilePhoto;
    private String userContent;
    private String kakaoToken;
    private String naverToken;
    private int challGrade;
}
