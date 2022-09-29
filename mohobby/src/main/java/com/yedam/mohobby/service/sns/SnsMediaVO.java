package com.yedam.mohobby.service.sns;

import lombok.Data;

@Data
public class SnsMediaVO {
	private int mediaId;
	private int postId;
	private int mediaType;
	private  String imgUrl;
	private  String VideoUrl;
}
