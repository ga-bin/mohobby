package com.yedam.mohobby.moim.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
    private int moimBoardId;
    private int moimFolder;
    private String moimBoardContent;
    private int moimClassify;
    private Date moimBoardDate;
}
