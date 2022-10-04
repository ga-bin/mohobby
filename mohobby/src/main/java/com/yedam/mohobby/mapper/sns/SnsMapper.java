package com.yedam.mohobby.mapper.sns;

import java.util.List;
import com.yedam.mohobby.service.communal.HashtagVO;
import com.yedam.mohobby.service.sns.SnsPostVO;

public interface SnsMapper {
    //전체피드
    public List<SnsPostVO> allList();
    //최신피드
    public List<SnsPostVO> newList();
    //인기피드
    public List<SnsPostVO> hotList();
    //인기강사피드
    public List<SnsPostVO> hotLecturerList();
    
    //피드상세
    public SnsPostVO feedDetail(int postId);
    
    //해시태그 등록(새로운 해시태그일 경우만(조회했을 때 일치하는 hashtag없으면)
    public int insertHashtag(HashtagVO hashtagVO);
    //해시태그 검색
    public List<SnsPostVO> searchHashtag(SnsPostVO snsPostVO);
    //해시태그 조회업데이트
    public int updateHashtagCnt(HashtagVO hashtagVO);
    
    //팔로잉 조회
    public List<SnsPostVO> getFollowingList(SnsPostVO snsPostVO);
    //팔로워 조회
    public List<SnsPostVO> getfollowerList(SnsPostVO snsPostVO);
    //닉네임 단어포함 조회
    public List<SnsPostVO> GetUsersByNick(SnsPostVO snsPostVO);
    
    //아이디 단건조회
    //public SnsPostVO getUserProfile();
    
    //게시물 등록
    public int insertFeed(SnsPostVO snsPostVO);
    
    //게시물 수정
    public int updateFeed(SnsPostVO snsPostVO);
    //피드 삭제
    public int deleteFeed(int postId);
    //좋아요 조회
    public SnsPostVO isLike(int postId, String memberId);
    
}
