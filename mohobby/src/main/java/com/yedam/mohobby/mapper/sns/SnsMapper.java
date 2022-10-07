package com.yedam.mohobby.mapper.sns;

import java.util.List;

import com.yedam.mohobby.service.communal.CommentsVO;
import com.yedam.mohobby.service.communal.JjimVO;
import com.yedam.mohobby.service.sns.SnsFollowVO;
import com.yedam.mohobby.service.sns.SnsPostVO;
import com.yedam.mohobby.service.user.MemberVO;

public interface SnsMapper {
    /*
     * 게시물
     */
    //게시물 등록
    public int insertFeed(SnsPostVO snsPostVO);
    //게시물 수정
    public int updateFeed(SnsPostVO snsPostVO);
    //피드 삭제
    public int deleteFeed(int postId);
    //인기강사피드
    public List<SnsPostVO> hotLecturerList();
    //전체피드
    public List<SnsPostVO> allList();
    //최신피드
    public List<SnsPostVO> newList();
    //인기피드
    public List<SnsPostVO> hotList();
    //유저피드목록
    public List<SnsPostVO> getUserFeed();
    //피드프로필
    
    //해시태그 업데이트 or 삽입
    public int updateHashtag(int postId, String hashtag);
    
    //피드상세
//    public SnsPostVO feedDetail(int postId);
    
    /*
     * 팔로우
     */
    //팔로우
    public int follow(SnsFollowVO followVO);
    //언팔로우
    public int unfollow(SnsFollowVO followVO);
    //팔로잉 조회inserCmt
    public List<SnsPostVO> getfollowingList(SnsPostVO snsPostVO);
    //팔로워 조회
    public List<SnsPostVO> getfollowerList(SnsPostVO snsPostVO);
    
    //유저전체
    public List<MemberVO> getUsers(MemberVO memberVO);
    //유저단건검색
    public List<MemberVO> getOneUser(MemberVO memberVO);
    //해시태그검색
    public List<SnsPostVO> searchHashtag(SnsPostVO snsPostVO);
    //유저닉네임검색
    public List<SnsPostVO> getUsersByNick(SnsPostVO snsPostVO);

    /*
     * 좋아요
     */
    public int addLike(JjimVO jjimVO);
    //좋아요취소
    public int deleteLike(JjimVO jjimVO);
    //좋아요누적
    public int sumLikes(SnsPostVO snsPostVO);
    
    /*
     * 댓글
     */
    //댓글입력
    public int inserCmt(CommentsVO commentsVO);
    //댓글수정
    public int updateCmt(CommentsVO commentsVO);
    //댓글삭제
    public int deleteCmt(int cmtId);
    //댓글조회
    public List<CommentsVO> getCmtList(CommentsVO commentsVO);
    
    /*
     * 대댓글
     */
    //대댓입력
    public int insertReCmt(CommentsVO commentsVO);
    //대댓수정
    public int updateReCmt(CommentsVO commentsVO);
    //대댓삭제
    public int deleteReCmt(CommentsVO commentsVO);
    
}	
