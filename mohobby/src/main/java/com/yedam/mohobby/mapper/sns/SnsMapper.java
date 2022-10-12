package com.yedam.mohobby.mapper.sns;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.mohobby.service.communal.CommentsVO;
import com.yedam.mohobby.service.communal.HashtagVO;
import com.yedam.mohobby.service.communal.JjimVO;
import com.yedam.mohobby.service.sns.SnsBookmarkCatgVO;
import com.yedam.mohobby.service.sns.SnsBookmarkVO;
import com.yedam.mohobby.service.sns.SnsFeedVO;
import com.yedam.mohobby.service.sns.SnsFollowVO;
import com.yedam.mohobby.service.sns.SnsMediaVO;
import com.yedam.mohobby.service.sns.SnsPostVO;
import com.yedam.mohobby.service.sns.SnsProfileVO;
import com.yedam.mohobby.service.user.MemberVO;
/**
 * @create 22/10/08
 * @author sunjin
 * @title sns controller
 */
public interface SnsMapper {
    /*
     * 게시물
     */
    //게시물 등록
    public int insertFeed(SnsPostVO snsPostVO);
    //미디어 등록
    public int insertMedia(SnsMediaVO snsMediaVO);
    //게시물 수정
    public int updateFeed(SnsPostVO snsPostVO);
    //피드 삭제
    public int deleteFeed(int postId);
    
    //인기강사피드조회
    public List<SnsPostVO> hotLecturerList();
    //전체피드조회
    public List<SnsPostVO> allList();
    //최신피드조회
    public List<SnsPostVO> newList();
    //인기피드조회
    public List<SnsPostVO> hotList();
    //팔로잉피드조회
    public List<SnsPostVO> getFollowingFeeds(String memberId);
    //프로필조회
    public SnsProfileVO getProfile(String memberId);
    //유저피드조회
    public List<SnsPostVO> getUserFeed(String memberId);
    //인기해시태그
    public List<HashtagVO> selectHashtagForMain();
    //피드상세조회
    public SnsFeedVO getFeedDetail(int postId);
    
    /*
     * 해시태그
     */
    //해시태그 업데이트 or 삽입
    public int updateHashtag(int postId);
    
    /*
     * 팔로우
     */
    //팔로우
    public int follow(SnsFollowVO followVO);
    //언팔로우
    public int unfollow(@Param("followerId") String followerId, @Param("followingId") String followingId);
    //팔로잉 조회
    public List<SnsFollowVO> getFollowingList(String followerId);
    //팔로워 조회
    public List<SnsFollowVO> getFollowerList(String followingId);
    
    //유저전체
    public List<MemberVO> getUsers();
    //유저 아이디, 닉네임 검색
    public List<MemberVO> searchUser(@Param("")String memberId,@Param("") String nickname);
    //유저닉네임검색
    public List<MemberVO> getUsersByNick(String nickname);
    //해시태그검색
    public List<SnsPostVO> searchHashtag(String hashtag);

    /*
     * 좋아요
     */
    public int addLike(JjimVO jjimVO);
    //좋아요취소
	public int deleteLike(@Param("targetId") int targetId,@Param("memberId") String memberId);
    //좋아요누적
    public int sumLikes(@Param("targetId") int targetId, @Param("postId") int postId);
    
    /*
     * 댓글
     */
    //댓글입력
    public int inserCmt(CommentsVO commentsVO);
    //댓글수정
    public int updateCmt(CommentsVO commentsVO);
    //댓글삭제
    public int deleteCmt(int commId);
    //댓글조회
    public List<CommentsVO> getCmtLists(int postId);
    
    /*
     * 대댓글
     */
    //대댓입력
    public int insertReCmt(CommentsVO commentsVO);
    //대댓수정
    public int updateReCmt(CommentsVO commentsVO);
    //대댓삭제
    public int deleteReCmt(int commId);
    
    /*
     * 북마크
     */
    //컬렉션 등록
    public int createBookmarkCtg(SnsBookmarkCatgVO bmkCtgVO);
    //컬렉션 이름수정
    public int updateBookmarkCtgName(SnsBookmarkCatgVO bmkCtgVO);
    //컬렉션 삭제(안의 게시물도 전부 삭제되도록)
    public int deleteBookmarkCtg(int catgId);
    //컬렉션 목록
    public List<SnsBookmarkCatgVO> getBookmarkCtgs(int catgId);
    
    //북마크 등록
    public int addBookmark(SnsBookmarkVO bmkVO);
    //북마크 삭제
    public int deleteBookmark(int postId);
    //북마크 조회
    public List<SnsBookmarkVO> getBookmarks(int catgId);
    //북마크 전체조회
	public List<SnsBookmarkVO> getAllBookmarks();


}	
