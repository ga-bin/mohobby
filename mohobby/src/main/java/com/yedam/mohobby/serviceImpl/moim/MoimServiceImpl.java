package com.yedam.mohobby.serviceImpl.moim;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.moim.MoimMapper;
import com.yedam.mohobby.service.communal.CommentsVO;
import com.yedam.mohobby.service.moim.MoimBoardVO;
import com.yedam.mohobby.service.moim.MoimCommentVO;
import com.yedam.mohobby.service.moim.MoimService;
import com.yedam.mohobby.service.moim.MoimVO;
import com.yedam.mohobby.service.moim.MoimVoteListVO;
import com.yedam.mohobby.service.moim.MoimVoteVO;
import com.yedam.mohobby.service.user.MemberVO;

@Service
public class MoimServiceImpl implements MoimService{

	@Autowired
	MoimMapper mapper;

	//소모임 등록
	@Override
	public void moimInsert(MoimVO moimVO) {
		mapper.moimInsert(moimVO);	
	}

	//소모임 멤버 모집 조회(6개씩)
	@Override
	public List<MoimVO> moimrecruitMember() {
		return mapper.moimrecruitMember();
	}
	
	//인기소모임 조회
	@Override
	public List<MoimVO> moimPopularSelect() {
		return mapper.moimPopularSelect();
	}

	//소모임명 조회
	@Override
	public List<MoimVO> moimNameSelect() {
		return mapper.moimNameSelect();
	}	
	
	//소모임 카테고리 조회
	@Override
	public List<MoimVO> moimCatgSelect() {
		return mapper.moimCatgSelect();
	}

	//소모임 종합 검색
	@Override
	public List<MoimVO> moimAllSearch(String moimName, String moimCatg) {
		HashMap<String, String> map = new HashMap<>();
		map.put("moimName", moimName);
		map.put("moimCatg", moimCatg);
		return mapper.moimAllSearch(map);
	}

	//소모임 게시판 전체 리스트 조회
	@Override
	public List<MoimBoardVO> moimAllBoard(int moimId, int boardType) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("moimId", moimId);
		map.put("boardType", boardType);
		return mapper.moimAllBoard(map);
	}
	
	//소모임명 중복 체크
	@Override
	public int memberIdCheck(String moimName) {
		return mapper.moimIdCheck(moimName);
	}

	//소모임 공지사항 리스트 출력
	@Override
	public List<MoimBoardVO> moimNoticeBoard(int moimId, int boardType) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("moimId", moimId);
		map.put("boardType", boardType);
		return mapper.moimNoticeBaord(map);
	}

	//소모임 게시글내 댓글 단건조회
	@Override
	public List<MoimCommentVO> moimCommentAllList(int moimId, int boardType, int boardId) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("moimId", moimId);
		map.put("boardType", boardType);
		map.put("boardId", boardId);
		System.out.println(moimId);
		System.out.println(boardType);
		System.out.println(boardId);
		return mapper.moimCommentAllList(map);
	}

	//소모임 게시글 단건 조회
	@Override
	public List<MoimBoardVO> moimOneBoard(int moimId, int boardType, int boardId) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("moimId", moimId);
		map.put("boardType", boardType);
		map.put("boardId", boardId);
		return mapper.moimOneBoard(map);
	}

	//소모임 게시글 댓글 등록
	@Override
	public void moimCommentInsert(CommentsVO commVO) {
		mapper.moimCommentInsert(commVO);
	}

	//내가 참여중인 소모임 리스트
	@Override
	public List<MoimVO> joinMoim(String memberId) {
		return mapper.joinMoim(memberId);
	}
	
	//소모임 댓글 수정
	@Override
	public int moimCommentUpdate(CommentsVO commVO) {
		return mapper.moimCommentUpdate(commVO);
	}

	//내가 운영중인 소모임 리스트
	@Override
	public List<MoimVO> operateMoim(String memberId) {
		return mapper.operateMoim(memberId);
	}

	//소모임 권한별 메인화면
	@Override
	public int moimMainTop(String memberId) {
		return mapper.moimMainTop(memberId);
	}

	 //소모임 등록 권한 업데이트
	 @Override
	 public int moimUserUpdate(MemberVO vo) {
	    return mapper.moimUserUpdate(vo);
	 }
	 
	 //소모임 투표 디테일 리스트
	 @Override
	 public List<MoimVoteListVO> moimVoteAllList(int moimId, int voteId) {
		 return mapper.moimVoteAllList(moimId, voteId);
	 }

	//소모임 단건 조회
	@Override
	public MoimVO getMoimInfo(int moimId) {
		return mapper.getMoimInfo(moimId);
	}
	
	//소모임 게시글 댓글 삭제
	@Override
	public int moimCommentDelete(int commId) {
		System.out.println("serviceimpl" + commId);
		return mapper.moimCommentDelete(commId);
	}

}

