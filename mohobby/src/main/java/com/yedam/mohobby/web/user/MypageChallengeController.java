package com.yedam.mohobby.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.user.MypageChallVO;
import com.yedam.mohobby.service.user.MypageChallengeService;

/**
 * @create 22/10/05
 * @author gabin
 * @title 회원 마이페이지 챌린지
 */
@RestController
public class MypageChallengeController {
	
 @Autowired
 MypageChallengeService service;

/**
 * 
 * @param memberId
 * @param keywordId
 * @return List<MypageChallVO>
 */
@GetMapping("/mypagejoinchall")
public List<MypageChallVO> getJoinChall(@RequestBody String memberId, @RequestBody String keywordId) {
	return service.getJoinChall(memberId, memberId);
}

/**
 * 
 * @param memberId
 * @param keywordId
 * @return List<MypageChallVO>
 */
@GetMapping("/mypagecompletechall")
public List<MypageChallVO> getCompletechall(@RequestBody String memberId, @RequestBody String keywordId) {
	return service.getCompleteChall(memberId, keywordId);
}
}