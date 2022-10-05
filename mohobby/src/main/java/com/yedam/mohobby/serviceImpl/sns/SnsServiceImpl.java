package com.yedam.mohobby.serviceImpl.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.sns.SnsMapper;
import com.yedam.mohobby.service.communal.HashtagVO;
import com.yedam.mohobby.service.communal.JjimVO;
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
    /*
     * 좋아요 클릭
     * 찜 테이블에서 postId % memberId가 존재하는지 조회
     * 좋아요가 취소되어야 하는지 확인
     * if 좋아요 존재 -> jjim테이블에서 postId와 memberId 삭제
     *               sns_post테이블에서 likes업데이트
     *               
     * delete from jjim where target_id = #{targetId} and member_id = '#{memberId}'
     * update sns_post set likes = (select count(*) from jjim where target_id = #{targetId})
     * 
     * 
     * else 좋아요 안했을때-> 
     * insert into jjim(target_id, member_id) values(#{targetId}, #{memberId})
     * update sns_post set likes = (select count(*) from jjim where target_id = #{targetId})
     */
    
    //좋아요클릭 - insert(jjim)
    @Override
    public int addLike(JjimVO jjimVO) {
        return mapper.addLike(jjimVO);
    }
    //좋아요 - update(jjim)
    @Override
    public int updateLike(JjimVO jjimVO) {
        return mapper.updateLike(jjimVO);
    }
    //좋아요누적 - insert(snspost)
    @Override
    public int sumLikes(SnsPostVO snsPostVO) {
        return mapper.sumLikes(snsPostVO);
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

}