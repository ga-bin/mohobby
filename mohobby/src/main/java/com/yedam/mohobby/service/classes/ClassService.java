package com.yedam.mohobby.service.classes;

import org.springframework.stereotype.Service;

@Service
public interface ClassService {
	//강의개설
	public void insertClass(ClassesVO classes);
}
