package com.yedam.mohobby.service.classes;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ClassService {
	//강의개설
	public void insertClass(ClassesVO classes);
	
	//전체조회
	public List<ClassesVO> listAll();
}
