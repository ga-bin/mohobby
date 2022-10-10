package com.yedam.mohobby.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yedam.mohobby.service.admin.MemberVO;

@Service
public interface MemberService {
		// 회원 한명 정보 가져오기(회원 마이페에지, 회원 아이디 있는지 조회)
		public MemberVO getMember(String memberId);
		
		// 로그인 아이디, 비밀번호가 일치하는지 체크
		public MemberVO checkIdPassword(MemberVO memberVO);

		// 이메일 통해서 회원인지 조회
		public MemberVO getMemberByEmail(String email);
		
		// 회원정보 전체 조회
		public List<MemberVO> getAllMember();
		
		//회원가입 insert
		public void insertMember(MemberVO memberVO);
		
		// 회원탈퇴 날짜 update
		public void deleteMember(String memberId);
		
		// 회원정보 수정 update
		public void updateMember(MemberVO memberVO);
		
		// 회원 비밀번호 수정
		public void updatePassword(MemberVO memberVO);
		
		// 챌린지 등급 update(plsql만들기)
		public void updateChallGrade(MemberVO memberVO);
		
		// role변환(멋진활동가, 만능 재주꾼, 일반회원)
		public void updateRole(MemberVO memberVO);
		
}
