package com.yedam.mohobby.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.admin.MemberVO;
import com.yedam.mohobby.service.user.MemberService;

/**
 * @create 2002/10/04
 * @author gabin
 * @title 회원 관리
 */
@RestController
@CrossOrigin(origins = "*")
public class MemberController {
	@Autowired
	MemberService service;
	
	/**
	 * 
	 * @param memberId
	 * @return MemberVO
	 */
	// 회원 한명 정보 가져오기(회원 마이페에지, 회원 아이디 있는지 조회)
	@GetMapping("/member/{memberId}")
	public MemberVO getMember(@PathVariable String memberId) {
		return service.getMember(memberId);
	}
	
	/**
	 * 
	 * @param memberVO
	 */
	//회원가입 insert
	@PostMapping("/member")
	public void insertMember(@RequestBody MemberVO memberVO) {
		service.insertMember(memberVO);
	}
	
	/**
	 * 
	 * @param memberId
	 */
	// 회원탈퇴 날짜 update
	@PutMapping("/memberdelete")
	public void deleteMember(@RequestBody String memberId) {
		service.deleteMember(memberId);
	}
	
	/**
	 * 
	 * @param memberVO
	 */
	// 회원정보 수정 update
	@PutMapping("/memberupdate")
	public void updateMember(@RequestBody MemberVO memberVO) {
		service.updateMember(memberVO);
	}
	
	/**
	 * 
	 * @param memberVO
	 */
	// 회원 비밀번호 수정
	@PutMapping("/memberupdatepassword")
	public void updatePassword(@RequestBody MemberVO memberVO) {
		service.updatePassword(memberVO);
	}
	
	/**
	 * 
	 * @param memberVO
	 */
	// 챌린지 등급 update(plsql만들기)s
	@PutMapping("/memberupdatechallgrade")
	public void updateChallGrade(@RequestBody MemberVO memberVO) {
		service.updateChallGrade(memberVO);
	}
	
	/**
	 * 
	 * @param memberVO
	 */
	// role변환(멋진활동가, 만능 재주꾼, 일반회원)
	@PutMapping("/memberupdaterole")
	public void updateRole(@RequestBody MemberVO memberVO) {
		service.updateRole(memberVO);
	}
}
