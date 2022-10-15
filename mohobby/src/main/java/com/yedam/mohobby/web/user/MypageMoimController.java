package com.yedam.mohobby.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.moim.MoimMemberVO;
import com.yedam.mohobby.service.moim.MoimVO;
import com.yedam.mohobby.service.user.MypageMoimService;
import com.yedam.mohobby.service.user.MypageMoimVO;

/**
 * @create 22/10/05
 * @author gabin
 * @title 회원 마이페이지 모임
 */
@RestController
@CrossOrigin(origins = "*")
public class MypageMoimController {

@Autowired
MypageMoimService service;

/**
 * 
 * @param memberId
 * @return List<MoimVO>
 * @title 유저가 공개중인 소모임 목록
 */
@GetMapping("/mypageprofilemoim/{memberId}")
public List<MoimVO> getProfileMoim(@PathVariable String memberId) {
	return service.getProfileMoim(memberId);
}

/**
 * @param moimMemberVO
 * @title 유저가 공개중인 소모임 목록 수정
 */
@PutMapping("/mypageprofilemoim")
public void updateProfileMoim(@RequestBody MoimMemberVO moimMemberVO) {
	service.updateProfileMoim(moimMemberVO);
}

/**
 * 
 * @param memberId
 * @param moimCatg
 * @return List<MypageMoimVO>
 * @title 유저가 참여한 소모임 목록(카테고리별 조회)
 */
@GetMapping("/mypagetakemoim")
public List<MypageMoimVO> getTakeMoim(@RequestParam("memberId") String memberId, @RequestParam("moimCatg") String moimCatg) {
	return service.getTakeMoim(memberId, moimCatg);
}

/**
 * 
 * @param memberId
 * @param moimCage
 * @return List<MypageMoimVO>
 * @title 유저가 운영중인 소모임 목록(카테고리별 조회)
 */
@GetMapping("/mypagemanagemoim")
public List<MypageMoimVO> getManageMoim(@RequestParam("memberId") String memberId, @RequestParam("moimCatg") String moimCatg) {
	return service.getManageMoim(memberId, moimCatg);
}

}
