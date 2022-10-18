
package com.yedam.mohobby.service.moim;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yedam.mohobby.service.communal.CommentsVO;
import com.yedam.mohobby.service.user.MemberVO;

@Service
public interface MoimService {
	//소모임 등록
	public void moimInsert(MoimVO moimVO);
	
	//소모임 멤버 모집 조회(6개씩)
	public List<MoimVO> moimrecruitMember();
	
	//소모임 인기목록 조회
	public List<MoimVO> moimPopularSelect();
	
	//소모임명 조회
	public List<MoimVO> moimNameSelect();
	
	//소모임 카테고리 조회
	public List<MoimVO> moimCatgSelect();
	
	//소모임 종합 검색
	public List<MoimVO> moimAllSearch(String moimName, String moimCatg);
	
	//소모임 게시판 전체 리스트 조회
	public List<MoimBoardVO> moimAllBoard(int moimId, int boardType);
	
	//소모임 모임이름 중복체크
	public int memberIdCheck(String moimName);
	
	//소모임 공지사항 리스트 조회
	public List<MoimBoardVO> moimNoticeBoard(int moimId, int boardType);
	
	//소모임 게시글내 댓글 조회
	public List<MoimCommentVO> moimCommentAllList(int moimId, int boardType, int boardId);

	//소모임 게시글 단건조회
	public List<MoimBoardVO> moimOneBoard(int moimId, int boardType, int boardId);
	
	//소모임 게시글 댓글 등록
	public void moimCommentInsert(CommentsVO commVO);
	
	//소모임 댓글 수정
	public int moimCommentUpdate(CommentsVO commVO);

	//내가 참여한 소모임 목록
	public List<MoimVO> joinMoim(String memberId);
	
	//내가 운영중인 소모임 목록
	public List<MoimVO> operateMoim(String memberId);
	
	//소모임 게시글 댓글 삭제
	public int moimCommentDelete(int commId);
	
	//소모임 권한별 메인화면
	public int moimMainTop(String memberId);
	
	//소모임 등록 권한 업데이트
	public int moimUserUpdate(MemberVO vo);

	//소모임 단건조회
	public MoimVO getMoimInfo(int moimId);

	//소모임 N빵 전체 리스트 조회
	public List<MoimDutchVO> getAllDuchList(int moimId);
	
	//소모임 전체 멤버 리스트 조회
	public List<MoimVO> getAllMemberList(int moimId);

	//소모임 멤버 검색 조회
	public List<MoimVO> getSearchMember(int moimId);
}

