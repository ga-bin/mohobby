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

@RestController
public class MypageMoimController {

@Autowired
MypageMoimService service;

@GetMapping("/mypageprofilemoim")
public List<MoimVO> getProfileMoim(@RequestBody String memberId) {
	return service.getProfileMoim(memberId);
}

@PutMapping("/mypageprofilemoim")
public void updateProfileMoim(@RequestBody MoimMemberVO moimMemberVO) {
	service.updateProfileMoim(moimMemberVO);
}

@GetMapping("/mypagetakemoim")
public List<MypageMoimVO> getTakeMoim(@RequestBody String memberId, @RequestBody String moimCatg) {
	return service.getTakeMoim(memberId, moimCatg);
}

@GetMapping("/mypagemanagemoim")
public List<MypageMoimVO> getManageMoim(@RequestBody String memberId, @RequestBody String moimCage) {
	return service.getManageMoim(memberId, moimCage);
}

}
