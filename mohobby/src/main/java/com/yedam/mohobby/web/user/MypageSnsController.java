package com.yedam.mohobby.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.sns.SnsPostVO;
import com.yedam.mohobby.service.user.MypageSnsService;

@RestController
public class MypageSnsController {
	
	@Autowired
	MypageSnsService service;
	
	@GetMapping("/mypagefollower")
	public List<String> getFollower(@RequestBody String memberId) {
		return service.getFollower(memberId);
	}
	
	@GetMapping("/mypagefollowing")
	public List<String> getFollowing(@RequestBody String memberId) {
		return service.getFollowing(memberId);
	}
	
	@GetMapping("/mypageSnspost")
	public List<SnsPostVO> getPost(@RequestBody String memberId) {
		return service.getPost(memberId);
	}
	
	@GetMapping("/mypageSnsJjim")
	public List<SnsPostVO> getJjim(@RequestBody String memberId) {
		return service.getJjim(memberId);
	}
	
}
