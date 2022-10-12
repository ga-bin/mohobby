package com.yedam.mohobby.mapper.classes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.mohobby.service.classes.ClassListRequestVO;
import com.yedam.mohobby.service.classes.ClassesVO;

@Mapper
public interface ClassMapper {
	//강의개설
	public void insertClass(ClassesVO classes);
	
	//전체조회
	public List<ClassesVO> listAll(ClassListRequestVO req);
}
