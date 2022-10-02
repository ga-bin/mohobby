package com.yedam.mohobby.service.openbanking;

import lombok.Data;

@Data
public class TokenResponseVO {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String expires_in;
	private String scope;
	private String user_seq_no;
}
