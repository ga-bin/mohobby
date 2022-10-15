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
    
    //후기평가조회
    public ClassReviewVO getClassReview(int classId);
    
    //강의게시글조회
    public List<ClassBoardVO> getClassBoardList(int classId, int boardType);
    
    //강의게시글등록
    public ClassBoardVO addClassBoard(ClassBoardVO board);

	//찜등록
    public void addJjim(JjimVO jjim);

    //찜삭제
    public void deleteJjim(JjimVO jjim);
    
    //html저장
    public void saveClassInfo(ClassInfoRequestVO req);
    
    //html경로 읽기
    public String readClassInfo(int classId);
    
    //qr코드생성
    public String createCodeImg(String link);

}
