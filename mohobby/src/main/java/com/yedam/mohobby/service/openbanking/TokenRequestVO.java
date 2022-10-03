package com.yedam.mohobby.service.openbanking;

import lombok.Data;

@Data
public class TokenRequestVO {
	private String code;
	private String client_id;
	private String client_secret;
	private String redirect_uri;
	private String grant_type;
}
