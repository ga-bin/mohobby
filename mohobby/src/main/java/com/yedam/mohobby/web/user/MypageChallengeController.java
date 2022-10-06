package com.yedam.mohobby.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.user.MypageChallVO;
import com.yedam.mohobby.service.user.MypageChallengeService;

@RestController
public class MypageChallengeController {
	
 @Autowired
 MypageChallengeService service;


@GetMapping("/mypagejoinchall")
public List<MypageChallVO> getJoinChall(@RequestBody String memberId, @RequestBody String keywordId) {
	return service.getJoinChall(memberId, memberId);
}

@GetMapping("/mypagecompletechall")
public List<MypageChallVO> getCompletechall(@RequestBody String memberId, @RequestBody String keywordId) {
	return service.getCompleteChall(memberId, keywordId);
}
}