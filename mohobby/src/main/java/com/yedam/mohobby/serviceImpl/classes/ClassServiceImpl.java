package com.yedam.mohobby.serviceImpl.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.classes.ClassMapper;
import com.yedam.mohobby.service.classes.ClassService;
import com.yedam.mohobby.service.classes.ClassesVO;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	ClassMapper classMapper;
	
	//강의개설
	@Override
	public void insertClass(ClassesVO classes) {
		classMapper.insertClass(classes);
	}

    @Override
    public List<ClassesVO> listAll() {
        return classMapper.listAll();
    }

}
