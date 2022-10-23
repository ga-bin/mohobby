package com.yedam.mohobby.mapper.moim;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.mohobby.service.communal.CommentsVO;
import com.yedam.mohobby.service.moim.MoimBoardVO;
import com.yedam.mohobby.service.moim.MoimCommentVO;
import com.yedam.mohobby.service.moim.MoimDutchPtpSoloVO;
import com.yedam.mohobby.service.moim.MoimDutchVO;
import com.yedam.mohobby.service.moim.MoimMemberVO;
import com.yedam.mohobby.service.moim.MoimVO;
import com.yedam.mohobby.service.moim.MoimVoteItemVO;
import com.yedam.mohobby.service.moim.MoimVoteListVO;
import com.yedam.mohobby.service.user.MemberVO;

public interface MoimMapper {
	//소모임 단건 조회
	public MoimVO getMoimOneInfo(int moimId);
		
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
	
	//소모임 가입 회원 수 조회하는 로직
	public int moimMemberCount(int moimId);

	//소모임 N빵 전체 리스트 조회
	public List<MoimDutchVO> getAllDuchList(int moimId);

	//소모임 전체 멤버 리스트 조회
	public List<MoimMemberVO> getAllMemberList(int moimId);
	
	//소모임 멤버 검색 조회
	public List<MoimMemberVO> getSearchMember(@Param("memberId")String memberId, @Param("moimId")int moimId);
	
	//소모임 투표 디테일 리스트
	public List<MoimVoteListVO> moimVoteAllList(@Param("moimId")int moimId);
		   
	//소모임 투표 아이템 리스트
	public List<MoimVoteListVO> moimVoteItemlist(@Param("moimId")int moimId);
		   
	//소모임 투표
	public List<MoimVoteListVO> voteItemSelect(@Param("moimId")int moimId, @Param("memberId")String memberId);
		   
	//소모임 투표 결과
	public List<MoimVoteItemVO> votereulst(@Param("voteId")int voteId);
		   
	//소모임 투표
	public List<MoimVoteListVO> vote(@Param("voteId")int voteId, @Param("memberId")String memberId, @Param("itemSelect")int itemSelect, @Param("moimId")int moimId);
		   
	//소모임 투표 선택 
	public int UserSelectvote(MoimVoteListVO vo);

	//소모임 투표 중복검사
	public int voteCheck(MoimVoteListVO vo);
	
	//소모임 투표 선택 검사
	public List<MoimVoteListVO> selectCheck(@Param("moimId")int moimId, @Param("memberId")String memberId);	
	
	//소모임 n빵 등록
	public String dutchInsert(MoimDutchVO dutchVO);
	
	//소모임 n빵 참여자 등록
	public int dutchMemberInsert(MoimDutchPtpSoloVO vo);
	
	//소모임 n빵 디테일 조회
	public List<MoimDutchVO> nbbangSelect(int moimId);
	
	//소모임 투표 항목
	public void insertVoteList(MoimVoteListVO vo);
	public void insertVoteListFor(MoimVoteListVO vo);
	
}
