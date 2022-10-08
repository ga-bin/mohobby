package com.yedam.mohobby.service.sns;

import lombok.Data;

@Data
public class SnsMediaVO {
	private int mediaId;
	private int postId;
	private int mediaType; //0:이미지, 1:동영장
	private String imgUrl;
	private String VideoUrl;
	private String fileName;
	private String fileRealname;
	private String dirName;
}
