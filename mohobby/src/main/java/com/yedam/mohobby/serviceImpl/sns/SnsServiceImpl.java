package com.yedam.mohobby.serviceImpl.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.sns.SnsMapper;
import com.yedam.mohobby.service.communal.CommentsVO;
import com.yedam.mohobby.service.communal.HashtagVO;
import com.yedam.mohobby.service.communal.JjimVO;
import com.yedam.mohobby.service.sns.SnsFollowVO;
import com.yedam.mohobby.service.sns.SnsPostVO;
import com.yedam.mohobby.service.sns.SnsService;
import com.yedam.mohobby.service.user.MemberVO;

@Service
public class SnsServiceImpl implements SnsService{

    @Autowired
    SnsMapper mapper;
	/*
     * 게시물
     */
    //게시물 등록
	@Override
	public int insertFeed(SnsPostVO snsPostVO) {
		return mapper.insertFeed(snsPostVO);
	}
	//게시물 수정
	@Override
	public int updateFeed(SnsPostVO snsPostVO) {
		return mapper.updateFeed(snsPostVO);
	}
	//피드 삭제
	@Override
	public int deleteFeed(int postId) {
		return mapper.deleteFeed(postId);
	}
	//인기강사피드
	@Override
	public List<SnsPostVO> hotLecturerList() {
		return mapper.hotLecturerList();
	}
	//전체피드
	@Override
	public List<SnsPostVO> allList() {
		return mapper.allList();
	}
	//최신피드
	@Override
	public List<SnsPostVO> newList() {
		return mapper.newList();
	}
	//인기피드
	@Override
	public List<SnsPostVO> hotList() {
		return mapper.hotList();
	}
	//유저피드목록
	@Override
	public List<SnsPostVO> getUserFeed() {
		return mapper.getUserFeed();
	}
	//해시태그 업데이트 or 삽입
	@Override
	public int updateHashtag(int postId, String hashtag) {
		return mapper.updateHashtag(postId, hashtag);
	}
	
    /*
     * 팔로우
     */
	//팔로우
	@Override
	public int follow(SnsFollowVO followVO) {
		return mapper.follow(followVO);
	}
	//언팔로우
	@Override
	public int unfollow(SnsFollowVO followVO) {
		return mapper.unfollow(followVO);
	}
	//팔로잉 조회
	@Override
	public List<SnsPostVO> getfollowingList(SnsPostVO snsPostVO) {
		return mapper.getfollowingList(snsPostVO);
	}
	//팔로워 조회
	@Override
	public List<SnsPostVO> getfollowerList(SnsPostVO snsPostVO) {
		return mapper.getfollowerList(snsPostVO);
	}
	//유저전체
	@Override
	public List<MemberVO> getUsers(MemberVO memberVO) {
		return mapper.getUsers(memberVO);
	}
	//유저단건검색
	@Override
	public List<MemberVO> getOneUser(MemberVO memberVO) {
		return mapper.getOneUser(memberVO);
	}
	//해시태그검색
	@Override
	public List<SnsPostVO> searchHashtag(SnsPostVO snsPostVO) {
		return mapper.searchHashtag(snsPostVO);
	}
	//유저닉네임검색
	@Override
	public List<SnsPostVO> getUsersByNick(SnsPostVO snsPostVO) {
		return mapper.getUsersByNick(snsPostVO);
	}
	
    /*
     * 좋아요
     */
	//좋아요
	@Override
	public int addLike(JjimVO jjimVO) {
		return mapper.addLike(jjimVO);
	}
	//좋아요취소
	@Override
	public int deleteLike(JjimVO jjimVO) {
		return mapper.deleteLike(jjimVO);
	}
	//좋아요누적
	@Override
	public int sumLikes(SnsPostVO snsPostVO) {
		return mapper.sumLikes(snsPostVO);
	}
	
    /*
     * 댓글
     */
	//댓글입력
	@Override
	public int inserCmt(CommentsVO commentsVO) {
		return mapper.inserCmt(commentsVO);
	}
	//댓글수정
	@Override
	public int updateCmt(CommentsVO commentsVO) {
		return mapper.updateCmt(commentsVO);
	}
	//댓글삭제
	@Override
	public int deleteCmt(int cmtId) {
		return mapper.deleteCmt(cmtId);
	}
	//댓글조회
	@Override
	public List<CommentsVO> getCmtList(CommentsVO commentsVO) {
		return mapper.getCmtList(commentsVO);
	}
    
    /*
     * 대댓글
     */
	//대댓입력
	@Override
	public int insertReCmt(CommentsVO commentsVO) {
		return mapper.insertReCmt(commentsVO);
	}
	//대댓수정
	@Override
	public int updateReCmt(CommentsVO commentsVO) {
		return mapper.updateReCmt(commentsVO);
	}
	//대댓삭제
	@Override
	public int deleteReCmt(CommentsVO commentsVO) {
		return mapper.deleteReCmt(commentsVO);
	}

}