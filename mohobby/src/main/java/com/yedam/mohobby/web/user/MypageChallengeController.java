package com.yedam.mohobby.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.user.MypageChallengeService;

@RestController
public class MypageChallengeController {
	
 @Autowired
 MypageChallengeService service;
}
