package com.yedam.mohobby.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.admin.MemberVO;
import com.yedam.mohobby.serviceImpl.admin.MemberServiceImpi;

@RestController
@RequestMapping("/member")
public class MemberController {
	
@Autowired
MemberServiceImpi mService;

@GetMapping("/get")
public MemberVO getMember() {
	return mService.getMember("gabin");
}

}
