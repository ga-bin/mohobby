package com.yedam.mohobby.service.iamport;

import lombok.Data;

@Data
public class GetTokenResponseVO {
	private String access_token;
	private String now;
	private String expired_at;
}
