package com.yedam.mohobby.mapper.classes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yedam.mohobby.service.classes.ClassBoardVO;
import com.yedam.mohobby.service.classes.ClassListRequestVO;
import com.yedam.mohobby.service.classes.ClassReviewVO;
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
	
	//후기평가조회
	public ClassReviewVO getClassReview(int classId);
	
	//강의게시글조회
	public List<ClassBoardVO> getClassBoardList(@Param("classId") int classId, @Param("boardType") int boardType);

	//찜등록
    public void addJjim(JjimVO jjim);

    //찜등록
    public void deleteJjim(JjimVO jjim);
}
