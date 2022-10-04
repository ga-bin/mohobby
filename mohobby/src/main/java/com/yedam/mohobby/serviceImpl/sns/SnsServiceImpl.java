package com.yedam.mohobby.serviceImpl.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.sns.SnsMapper;
import com.yedam.mohobby.service.communal.HashtagVO;
import com.yedam.mohobby.service.sns.SnsPostVO;
import com.yedam.mohobby.service.sns.SnsService;

@Service
public class SnsServiceImpl implements SnsService{

    @Autowired
    SnsMapper mapper;

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
        // TODO Auto-generated method stub
        return mapper.hotList();
    }
    //인기강사피드
    @Override
    public List<SnsPostVO> hotLecturerList() {
        return mapper.hotLecturerList();
    }
    //피드상세
    @Override
    public SnsPostVO feedDetail(int postId) {
        return mapper.feedDetail(postId);
    }
    
    //해시태그 등록
    @Override
    public int insertHashtag(HashtagVO hashtagVO) {
        return mapper.insertHashtag(hashtagVO);
    }
    //해시태그 검색
    @Override
    public List<SnsPostVO> searchHashtag(SnsPostVO snsPostVo) {
        // TODO Auto-generated method stub
        return mapper.searchHashtag(snsPostVo);
    }
    //해시태그 조회 업댓
    @Override
    public int updateHashtagCnt(HashtagVO hashtagVO) {
        return mapper.updateHashtagCnt(hashtagVO);
    }
    //팔로잉 조회
    @Override
    public List<SnsPostVO> getFollowingList(SnsPostVO snsPostVo) {
        return mapper.getFollowingList(snsPostVo);
    }
    //팔로워 조회
    @Override
    public List<SnsPostVO> getfollowerList(SnsPostVO snsPostVo) {
        return mapper.getfollowerList(snsPostVo);
    }
    //닉네임 단어포함 조회
    @Override
    public List<SnsPostVO> GetUsersByNick(SnsPostVO snsPostVo) {
        return mapper.GetUsersByNick(snsPostVo);
    }
    //아이디 단건조회
    @Override
    public SnsPostVO getUserProfile() {
        // TODO Auto-generated method stub
        return null;
    }
    //게시물 등록
    @Override
    public int insertFeed(SnsPostVO snsPostVo) {
        return mapper.insertFeed(snsPostVo);
    }
    //게시물 수정
    @Override
    public int updateFeed(SnsPostVO snsPostVo) {
        // TODO Auto-generated method stub
        return 0;
    }
    //피드 삭제
    @Override
    public int deleteFeed(int postId) {
        return mapper.deleteFeed(postId);
    }  
    //좋아요 조회
    @Override
    public SnsPostVO isLike(int postId, String memberId) {
        return mapper.isLike(postId, memberId);
    }

}
