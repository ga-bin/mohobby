package com.yedam.mohobby.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.sns.SnsPostVO;
import com.yedam.mohobby.service.user.MypageSnsService;

/**
 * @create 22/10/05
 * @author gabin
 * @title 회원 마이페이지 sns
 */
@RestController
public class MypageSnsController {
	
	@Autowired
	MypageSnsService service;
	
	/**
	 * 
	 * @param memberId
	 * @return List<String>
	 */
	@GetMapping("/mypagefollower")
	public List<String> getFollower(@RequestBody String memberId) {
		return service.getFollower(memberId);
	}
	
	/**
	 * 
	 * @param memberId
	 * @return List<String>
	 */
	@GetMapping("/mypagefollowing")
	public List<String> getFollowing(@RequestBody String memberId) {
		return service.getFollowing(memberId);
	}
	
	/**
	 * 
	 * @param memberId
	 * @return List<SnsPostVO>
	 */
	@GetMapping("/mypageSnspost")
	public List<SnsPostVO> getPost(@RequestBody String memberId) {
		return service.getPost(memberId);
	}
	
	/**
	 * 
	 * @param memberId
	 * @return List<SnsPostVO>
	 */
	@GetMapping("/mypageSnsJjim")
	public List<SnsPostVO> getJjim(@RequestBody String memberId) {
		return service.getJjim(memberId);
	}
	
}
