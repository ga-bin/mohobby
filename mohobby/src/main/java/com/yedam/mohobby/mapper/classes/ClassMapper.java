package com.yedam.mohobby.mapper.classes;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.mohobby.service.classes.ClassesVO;

@Mapper
public interface ClassMapper {
	//강의개설
	public void insertClass(ClassesVO classes);
}
