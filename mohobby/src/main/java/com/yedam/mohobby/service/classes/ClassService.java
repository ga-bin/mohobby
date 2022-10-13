package com.yedam.mohobby.service.classes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yedam.mohobby.service.communal.JjimVO;

@Service
public interface ClassService {
	//강의개설
	public void insertClass(ClassesVO classes);
	
	//전체조회
	public List<ClassesVO> listAll(ClassListRequestVO req);
	
	//단건조회
    public ClassesVO listOne(ClassListRequestVO req);

	//찜등록
    public void addJjim(JjimVO jjim);

    //찜삭제
    public void deleteJjim(JjimVO jjim);
}
