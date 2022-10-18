package com.yedam.mohobby.mapper.moim;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import com.yedam.mohobby.service.communal.CommentsVO;
import com.yedam.mohobby.service.moim.MoimBoardVO;
import com.yedam.mohobby.service.moim.MoimCommentVO;
import com.yedam.mohobby.service.moim.MoimDutchVO;
import com.yedam.mohobby.service.moim.MoimVO;
import com.yedam.mohobby.service.moim.MoimVoteListVO;
import com.yedam.mohobby.service.moim.MoimVoteVO;
import com.yedam.mohobby.service.user.MemberVO;

public interface MoimMapper {
	
	//소모임 등록
	public void moimInsert (MoimVO moimVO);

	//소모임 멤버 모집 조회(6개씩)
	public List<MoimVO> moimrecruitMember();
	
	//인기소모임 조회
	public List<MoimVO> moimPopularSelect();
	
	//소모임명 조회
	public List<MoimVO> moimNameSelect();
	
	//소모임 카테고리 조회
	public List<MoimVO> moimCatgSelect();
	
	//소모임 종합 검색
	public List<MoimVO> moimAllSearch(HashMap<String, String> map);
	
	//소모임 게시판 전체 리스트 조회
	public List<MoimBoardVO> moimAllBoard(HashMap<String, Integer> map);
	
	//소모임 모임이름 중복검사
	public int moimIdCheck(String moimName);
	
	//소모임 공지사항 리스트 조회
	public List<MoimBoardVO> moimNoticeBaord(HashMap<String, Integer> map);
	
	//소모임 게시판내 댓글 조회
	public List<MoimCommentVO> moimCommentAllList(HashMap<String, Integer> map);
	
	//소모임 게시글 단건 조회
	public List<MoimBoardVO> moimOneBoard(HashMap<String, Integer> map);
	
	//소모임 게시글 댓글 등록
	public void moimCommentInsert(CommentsVO commVO);
	
	//소모임 댓글 수정
	public int moimCommentUpdate(CommentsVO commVO);
  
	//내가 참여중인 소모임
	public List<MoimVO> joinMoim(String memberId);

	//내가 운영중인 소모임
	public List<MoimVO> operateMoim(String memberId);
	
	//소모임 게시글 댓글 삭제
	public int moimCommentDelete(@Param("commId")int commId);
	
	//소모임 권한별 메인화면
	public int moimMainTop(@Param("memberId")String memberId);
	
	//소모임 등록 권한 업데이트
	public int moimUserUpdate(MemberVO vo);
	
	//소모임 단건조회
	public MoimVO getMoimInfo(int moimId);
	
	//소모임 대표 조회
	public MoimVO getMoimInfo(int moimId);

	//소모임 N빵 전체 리스트 조회
	public List<MoimDutchVO> getAllDuchList(int moimId);


	//소모임 전체 멤버 리스트 조회
	public List<MoimVO> getAllMemberList(int moimId);
	
	//소모임 멤버 검색 조회
	public List<MoimVO> getSearchMember(int moimId);
}
