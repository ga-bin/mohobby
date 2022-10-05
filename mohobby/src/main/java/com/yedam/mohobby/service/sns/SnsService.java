package com.yedam.mohobby.service.sns;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yedam.mohobby.service.communal.HashtagVO;
import com.yedam.mohobby.service.communal.JjimVO;

@Service
public interface SnsService {
    //전체피드
    public List<SnsPostVO> allList();
    //최신피드
    public List<SnsPostVO> newList();
    //인기피드
    public List<SnsPostVO> hotList();
    //인기강사피드
    public List<SnsPostVO> hotLecturerList();
    
    //피드 상세
    public SnsPostVO feedDetail(int postId);
    
    //해시태그 등록(새로운 해시태그일 경우만(조회했을 때 일치하는 hashtag없으면)
    public int insertHashtag(HashtagVO hashtagVO);
    //해시태그 검색
    public List<SnsPostVO> searchHashtag(SnsPostVO snsPostVO);
    //해시태그 조회업데이트
    public int updateHashtagCnt(HashtagVO hashtagVO);
    
    //팔로워조회
    public List<SnsPostVO> getFollowingList(SnsPostVO snsPostVO);
    //팔로잉 조회
    public List<SnsPostVO> getfollowerList(SnsPostVO snsPostVO);
    
    //닉네임 단어포함 조회
    public List<SnsPostVO> GetUsersByNick(SnsPostVO snsPostVO);
    
    //아이디 단건조회
    public SnsPostVO getUserProfile();
    
    //좋아요클릭-insert(jjim)
    public int addLike(JjimVO jjimVO);
    //좋아요상태 변경-update(jjim)
    public int updateLike(JjimVO jjimVO);
    //좋아요누적-update(snspost)
    public int sumLikes(SnsPostVO snsPostVO);
    
    //게시물 등록
    public int insertFeed(SnsPostVO snsPostVO);
    //게시물 썸네일 외 이미지 등록
    
    //게시물 수정
    public int updateFeed(SnsPostVO snsPostVO);
    
    //게시물 삭제
    public int deleteFeed(int postId);
}