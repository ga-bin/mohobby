package com.yedam.mohobby.serviceImpl.moim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.mohobby.mapper.moim.MoimMapper;
import com.yedam.mohobby.service.communal.CommentsVO;
import com.yedam.mohobby.service.moim.MoimBoardVO;
import com.yedam.mohobby.service.moim.MoimCommentVO;
import com.yedam.mohobby.service.moim.MoimDutchPtpSoloVO;
import com.yedam.mohobby.service.moim.MoimDutchPtpVO;
import com.yedam.mohobby.service.moim.MoimDutchVO;
import com.yedam.mohobby.service.moim.MoimImageVO;
import com.yedam.mohobby.service.moim.MoimInfoRequestVO;
import com.yedam.mohobby.service.moim.MoimMemberVO;
import com.yedam.mohobby.service.moim.MoimScheduleVO;
import com.yedam.mohobby.service.moim.MoimService;
import com.yedam.mohobby.service.moim.MoimVO;
import com.yedam.mohobby.service.moim.MoimVoteItemVO;
import com.yedam.mohobby.service.moim.MoimVoteListVO;
import com.yedam.mohobby.service.user.MemberVO;
import com.yedam.mohobby.web.classes.ClassController;
import com.yedam.mohobby.web.moim.MoimController;

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
      try {
         moimVO.setMoimImg(moimVO.getMoimName()+".jpg");
      }catch (NullPointerException e) {
         moimVO.setMoimImg("comfuck.jpg");
      }
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
	
    //소모임 프로필 수정
	@Override
	public String updateProfile(MoimVO moimVO, List<MultipartFile> files) {
		try {
	         //저장할 경로
	         String path = this.getClass().getResource("/").getPath();
	         System.out.println( path);
	         path = path.substring(0, path.lastIndexOf("mohobby"));
	         path = path.substring(0, path.lastIndexOf("mohobby")+"mohobby".length());
	         path += "/mohobby/mohobby/src/assets/image/moim/";
	         
	         //진짜 진짜 파일 이름
	         path += moimVO.getMoimName();
	         path += ".jpg";
	         
	         //새로 path만든 값은 save에 저장
	         for(int i = 0; i < files.size(); i++) {
	              
	             MultipartFile file = files.get(i);
	             File save = new File(path);
	              
	             file.transferTo(save);
	          }
	         
	         //소모임 정보 수정
	         mapper.updateProfile(moimVO);
	         
	         return "success";
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("업로드 실패: " + e.getMessage());
	      } 
		return "fail";
	}
	
	//게시글 삭제
	@Override
	public String deleteBoard(int boardId, int boardType) {
		return mapper.deleteBoard(boardId, boardType);
	}
	
	//게시글 수정
	@Override
	public void updateBoard(MoimBoardVO vo) {
		mapper.updateBoard(vo);	
	}

	//소모임 권한 확인
	@Override
	public List<MoimMemberVO> moimRight(String memberId) {
		return mapper.moimRight(memberId);
	}

	//소모임 가입 체크
	@Override
	public List<MoimMemberVO> joincheck(String memberId, int moimId) {
		 return mapper.joincheck(memberId, moimId);
	}

	//가입자수 증가
	@Override
	public int updatecnt(MoimVO vo) {
		return mapper.updatecnt(vo);
	}
   
}