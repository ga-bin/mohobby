package com.yedam.mohobby.serviceImpl.moim;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.moim.MoimMapper;
import com.yedam.mohobby.service.communal.CommentsVO;
import com.yedam.mohobby.service.moim.MoimBoardVO;
import com.yedam.mohobby.service.moim.MoimCommentVO;
import com.yedam.mohobby.service.moim.MoimDutchPtpSoloVO;
import com.yedam.mohobby.service.moim.MoimDutchPtpVO;
import com.yedam.mohobby.service.moim.MoimDutchVO;
import com.yedam.mohobby.service.moim.MoimMemberVO;
import com.yedam.mohobby.service.moim.MoimScheduleVO;
import com.yedam.mohobby.service.moim.MoimService;
import com.yedam.mohobby.service.moim.MoimVO;
import com.yedam.mohobby.service.moim.MoimVoteItemVO;
import com.yedam.mohobby.service.moim.MoimVoteListVO;
import com.yedam.mohobby.service.user.MemberVO;

@Service
public class MoimServiceImpl implements MoimService {

	@Autowired
	MoimMapper mapper;

	// 소모임 단건조회
	@Override
	public MoimVO getMoimOneInfo(int moimId) {
		return mapper.getMoimOneInfo(moimId);
	}

	// 소모임 등록
	@Override
	public void moimInsert(MoimVO moimVO) {
		mapper.moimInsert(moimVO);
	}

	// 소모임 멤버 모집 조회(6개씩)
	@Override
	public List<MoimVO> moimrecruitMember() {
		return mapper.moimrecruitMember();
	}

	// 인기소모임 조회
	@Override
	public List<MoimVO> moimPopularSelect() {
		return mapper.moimPopularSelect();
	}

	// 소모임명 조회
	@Override
	public List<MoimVO> moimNameSelect() {
		return mapper.moimNameSelect();
	}

	// 소모임 카테고리 조회
	@Override
	public List<MoimVO> moimCatgSelect() {
		return mapper.moimCatgSelect();
	}

	// 소모임 종합 검색
	@Override
	public List<MoimVO> moimAllSearch(String moimName, String moimCatg) {
		HashMap<String, String> map = new HashMap<>();
		map.put("moimName", moimName);
		map.put("moimCatg", moimCatg);
		return mapper.moimAllSearch(map);
	}

	// 소모임 게시판 전체 리스트 조회
	@Override
	public List<MoimBoardVO> moimAllBoard(int moimId, int boardType) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("moimId", moimId);
		map.put("boardType", boardType);
		return mapper.moimAllBoard(map);
	}

	// 소모임명 중복 체크
	@Override
	public int memberIdCheck(String moimName) {
		return mapper.moimIdCheck(moimName);
	}

	// 소모임 공지사항 리스트 출력
	@Override
	public List<MoimBoardVO> moimNoticeBoard(int moimId, int boardType) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("moimId", moimId);
		map.put("boardType", boardType);
		return mapper.moimNoticeBaord(map);
	}

	// 소모임 게시글내 댓글 단건조회
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

	// 소모임 게시글 단건 조회
	@Override
	public List<MoimBoardVO> moimOneBoard(int moimId, int boardType, int boardId) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("moimId", moimId);
		map.put("boardType", boardType);
		map.put("boardId", boardId);
		return mapper.moimOneBoard(map);
	}

	// 소모임 게시글 댓글 등록
	@Override
	public void moimCommentInsert(CommentsVO commVO) {
		mapper.moimCommentInsert(commVO);
	}

	// 내가 참여중인 소모임 리스트
	@Override
	public List<MoimVO> joinMoim(String memberId) {
		return mapper.joinMoim(memberId);
	}

	// 소모임 댓글 수정
	@Override
	public int moimCommentUpdate(CommentsVO commVO) {
		return mapper.moimCommentUpdate(commVO);
	}

	// 내가 운영중인 소모임 리스트
	@Override
	public List<MoimVO> operateMoim(String memberId) {
		return mapper.operateMoim(memberId);
	}

	// 소모임 권한별 메인화면
	@Override
	public int moimMainTop(String memberId) {
		return mapper.moimMainTop(memberId);
	}

	 //소모임 등록 권한 업데이트
	 @Override
	 public int moimUserUpdate(MemberVO vo) {
	    return mapper.moimUserUpdate(vo);
	 }

	// 소모임 단건조회
	@Override
	public MoimVO getMoimInfo(int moimId) {
		return mapper.getMoimOneInfo(moimId);
	}

	// 소모임 게시글 댓글 삭제
	@Override
	public int moimCommentDelete(int commId) {
		System.out.println("serviceimpl" + commId);
		return mapper.moimCommentDelete(commId);
	}

	// 소모임 가입 회원 수 조회하는 로직
	@Override
	public int moimMemberCount(int moimId) {
		return mapper.moimMemberCount(moimId);
	}

	// N빵 전체 리스트 조회
	@Override
	public List<MoimDutchVO> getAllDuchList(int moimId) {
		return mapper.getAllDuchList(moimId);
	}

	// 소모임 전체 멤버 리스트 조회
	@Override
	public List<MoimMemberVO> getAllMemberList(int moimId) {
		return mapper.getAllMemberList(moimId);
	}

	// 소모임 멤버 검색 조회
	@Override
	public List<MoimMemberVO> getSearchMember(String memberId, int moimId) {
		return mapper.getSearchMember(memberId, moimId);
	}

	// 소모임 투표 디테일 리스트
	@Override
	public List<MoimVoteListVO> moimVoteAllList(int moimId) {
		return mapper.moimVoteAllList(moimId);
	}
		   
	//소모임 투표 아이템 리스트
	@Override
	public List<MoimVoteListVO> moimVoteItemlist(int moimId) {
        return mapper.moimVoteItemlist(moimId);
	}
	
	@Override
	public List<MoimVoteListVO> voteItemSelect(int moimId, String memberId) {
		return mapper.voteItemSelect(moimId, memberId);
	}
	
	@Override
	public List<MoimVoteItemVO> votereulst(int voteId) {
		return mapper.votereulst(voteId);
	}
	
	@Override
	public List<MoimVoteListVO> vote(int voteId, String memberId, int itemSelect, int moimId) {
		return mapper.vote(voteId, memberId, itemSelect, moimId);
	}
	
	@Override
	public int UserSelectvote(MoimVoteListVO vo) {
		return mapper.UserSelectvote(vo);
	}

	@Override
	public int voteCheck(MoimVoteListVO vo) {
		return mapper.voteCheck(vo);
	}

	@Override
	public List<MoimVoteListVO> selectCheck(int moimId, String memberId) {
		return mapper.selectCheck(moimId, memberId );
	}

	//n빵 등록
	@Override
	public String dutchInsert(MoimDutchVO dutchVO) {
		return mapper.dutchInsert(dutchVO);
	}

	//n빵 참여자 등록
	@Override
	public String dutchMemberInsert(MoimDutchPtpVO dutptpVO) {
		
		List<String> memberIdList = dutptpVO.getMemberId();
		int sum = 0;
		
		for(String memberId : memberIdList) {
			MoimDutchPtpSoloVO vo = new MoimDutchPtpSoloVO();
			vo.setCalcCheck(dutptpVO.getCalcCheck());
			vo.setCalcPrice(dutptpVO.getCalcPrice());
			vo.setDutchId(dutptpVO.getDutchId());
			vo.setMemberId(memberId);
			vo.setMoimId(dutptpVO.getMoimId());
			
			sum += mapper.dutchMemberInsert(vo);
		}

		if (sum == memberIdList.size()) {
			return "success";
		}
		
		return "fail";
	}

	//소모임 n빵 디테일 조회
	@Override
	public List<MoimDutchVO> nbbangSelect(int moimId) {
		return mapper.nbbangSelect(moimId);
	}
	
	//소모임 투표 항목
	@Override
	public void insertVoteList(MoimVoteListVO vo) {
		
		List<MoimVoteItemVO> itemList = vo.getVotelist();
		
		MoimVoteListVO body = new MoimVoteListVO();
		body.setTopic(vo.getTopic());
		body.setEndDate(vo.getEndDate());
		body.setMoimId(vo.getMoimId());
		body.setMemberId(vo.getMemberId());
		mapper.insertVoteList(body);
		
		int voteId = body.getVoteId();
		
		
		for(int i=0; i<itemList.size(); i++) {
			if(itemList.get(i).getContent() != null) {
			body.setVoteId(voteId);
			body.setContent(itemList.get(i).getContent());
			}		
			mapper.insertVoteListFor(body);
		}
		
	}
	
	//소모임 일정 등록
	@Override
	public String scheduleInsert(MoimScheduleVO scheduleVO){
		return mapper.scheduleInsert(scheduleVO);
	}
	
	//소모임 일정 전체조회
	@Override
	public List<MoimScheduleVO> scheduleSelect(int moimId) {
		return mapper.scheduleSelect(moimId);
	}
}
