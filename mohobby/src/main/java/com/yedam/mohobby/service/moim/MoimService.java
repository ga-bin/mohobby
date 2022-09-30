package com.yedam.mohobby.service.moim;

import org.springframework.stereotype.Service;

@Service
public interface MoimService {
	//소모임 등록
	public void moimInsert(MoimVO moimVO);
}
