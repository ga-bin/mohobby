package com.yedam.mohobby.mapper.classes;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yedam.mohobby.service.classes.ClassAttendanceVO;
import com.yedam.mohobby.service.classes.ClassBoardVO;
import com.yedam.mohobby.service.classes.ClassChapterVO;
import com.yedam.mohobby.service.classes.ClassCurriculumVO;
import com.yedam.mohobby.service.classes.ClassListRequestVO;
import com.yedam.mohobby.service.classes.ClassPayVO;
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
	
	//강의게시글등록
	public void addClassBoard(ClassBoardVO board);
	
	//강의게시글수정
	public void updateClassBoard(ClassBoardVO board);
	
	//강의게시글삭제
	public void deleteClassBoard(int boardId);

	//찜등록
    public void addJjim(JjimVO jjim);

    //찜등록
    public void deleteJjim(JjimVO jjim);

    //강의 챕터 조회
    public List<ClassChapterVO> getChapterList(ClassChapterVO vo);
    
    //강의 결제내역 단건조회
    public ClassPayVO getClassPayOne(ClassPayVO vo);
    
    //강의 커리큘럼 전체 진행율 조회
  	public HashMap<String, Integer> getCurrProgressAll(@Param("classId")int classId, @Param("memberId")String memberId);
    
    //강의 커리큘럼 진행율 조회
    public ClassAttendanceVO getCurrProgress(ClassAttendanceVO vo);
    
    //커리큘럼 단건조회
    public ClassCurriculumVO getCurrInfo(int currId);
}
