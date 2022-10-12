package com.yedam.mohobby.serviceImpl.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.classes.ClassMapper;
import com.yedam.mohobby.service.classes.ClassListRequestVO;
import com.yedam.mohobby.service.classes.ClassService;
import com.yedam.mohobby.service.classes.ClassesVO;
import com.yedam.mohobby.service.communal.JjimVO;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	ClassMapper classMapper;
	
	//강의개설
	@Override
	public void insertClass(ClassesVO classes) {
		classMapper.insertClass(classes);
	}
	
	//목록조회
    @Override
    public List<ClassesVO> listAll(ClassListRequestVO req) {
        return classMapper.listAll(req);
    }

    //단건조회
    @Override
    public ClassesVO listOne(ClassListRequestVO req) {
        return classMapper.listOne(req);
    }
    
    //찜등록
    @Override
    public void addJjim(JjimVO jjim) {
        classMapper.addJjim(jjim);
    }

    //찜삭제
    @Override
    public void deleteJjim(JjimVO jjim) {
        classMapper.deleteJjim(jjim);
    }

}
