package com.yedam.mohobby.service.openbanking;

import lombok.Data;

@Data
public class BankRealNameResponseVO {
	private ResponseHeaderVO header;
	private String dpnm;
	private String acno;
}
