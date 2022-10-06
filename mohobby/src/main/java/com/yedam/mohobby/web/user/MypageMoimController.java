package com.yedam.mohobby.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class MypageMoimController {

@Autowired
MypageMoimService service;

/**
 * 
 * @param memberId
 * @return List<MoimVO>
 */
@GetMapping("/mypageprofilemoim")
public List<MoimVO> getProfileMoim(@RequestBody String memberId) {
	return service.getProfileMoim(memberId);
}

/**
 * 
 * @param moimMemberVO
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
 */
@GetMapping("/mypagetakemoim")
public List<MypageMoimVO> getTakeMoim(@RequestBody String memberId, @RequestBody String moimCatg) {
	return service.getTakeMoim(memberId, moimCatg);
}

/**
 * 
 * @param memberId
 * @param moimCage
 * @return List<MypageMoimVO>
 */
@GetMapping("/mypagemanagemoim")
public List<MypageMoimVO> getManageMoim(@RequestBody String memberId, @RequestBody String moimCage) {
	return service.getManageMoim(memberId, moimCage);
}

}
