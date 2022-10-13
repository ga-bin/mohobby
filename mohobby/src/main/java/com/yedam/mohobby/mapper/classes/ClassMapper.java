package com.yedam.mohobby.mapper.classes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.mohobby.service.classes.ClassListRequestVO;
import com.yedam.mohobby.service.classes.ClassesVO;
import com.yedam.mohobby.service.communal.JjimVO;

@Mapper
public interface ClassMapper {
	//강의개설
	public void insertClass(ClassesVO classes);
	
	//전체조회
	public List<ClassesVO> listAll(ClassListRequestVO req);
	
	//단건조회
	public ClassesVO listOne(ClassListRequestVO req);

	//찜등록
    public void addJjim(JjimVO jjim);

    //찜등록
    public void deleteJjim(JjimVO jjim);
}
