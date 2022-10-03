package com.yedam.mohobby.serviceImpl.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.sns.SnsMapper;
import com.yedam.mohobby.service.sns.SnsPostVO;
import com.yedam.mohobby.service.sns.SnsService;

@Service
public class SnsServiceImpl implements SnsService{

    @Autowired
    SnsMapper snsmapper;

    //전체피드
    @Override
    public List<SnsPostVO> allFeedsList() {
        // TODO Auto-generated method stub
        return null;
    }
    //최신피드
    @Override
    public List<SnsPostVO> newFeedsList() {
        // TODO Auto-generated method stub
        return null;
    }
    //인기피드
    @Override
    public List<SnsPostVO> hotFeedsList() {
        // TODO Auto-generated method stub
        return null;
    }
    //해시태그 검색
    @Override
    public List<SnsPostVO> searchHashTag(SnsPostVO snsPostVo) {
        // TODO Auto-generated method stub
        return null;
    }
    //팔로잉 조회
    @Override
    public List<SnsPostVO> getFollowingList(SnsPostVO snsPostVo) {
        // TODO Auto-generated method stub
        return null;
    }
    //팔로워 조회
    @Override
    public List<SnsPostVO> getfollowerList(SnsPostVO snsPostVo) {
        // TODO Auto-generated method stub
        return null;
    }
    //닉네임 단어포함 조회
    @Override
    public List<SnsPostVO> GetUsersByNick(SnsPostVO snsPostVo) {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return 0;
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
        // TODO Auto-generated method stub
        return 0;
    }
    
    
    
}
