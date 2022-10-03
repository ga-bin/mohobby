package com.yedam.mohobby.mapper.sns;

import java.util.List;

import com.yedam.mohobby.service.sns.SnsPostVO;

public interface SnsMapper {
	//전체피드
	public List<SnsPostVO> allFeedsList();
	
	//최신피드
	public List<SnsPostVO> newFeedsList();
	
	//인기피드
    public List<SnsPostVO> hotFeedsList();
    
	//해시태그 검색
	public List<SnsPostVO> searchHashTag(SnsPostVO snsPostVo);
	
	//팔로잉 조회
	public List<SnsPostVO> getFollowingList(SnsPostVO snsPostVo);
	
	//팔로워 조회
	public List<SnsPostVO> getfollowerList(SnsPostVO snsPostVo);
	
	//닉네임 단어포함 조회
    public List<SnsPostVO> GetUsersByNick(SnsPostVO snsPostVo);
	
    //아이디 단건조회
    public SnsPostVO getUserProfile();
	
	//게시물 등록
	public int insertFeed(SnsPostVO snsPostVo);
	
	//게시물 이미지
	
	//게시물 수정
	public int updateFeed(SnsPostVO snsPostVo);
	
	//피드 삭제
	public int deleteFeed(int postId);
}
