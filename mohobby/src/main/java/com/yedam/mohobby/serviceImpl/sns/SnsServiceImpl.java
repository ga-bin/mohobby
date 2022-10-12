package com.yedam.mohobby.serviceImpl.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.mohobby.mapper.sns.SnsMapper;
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
import com.yedam.mohobby.service.sns.SnsService;
import com.yedam.mohobby.service.user.MemberVO;
/**
 * @create 22/10/08
 * @author sunjin
 * @title sns controller
 */
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
	//미디어 등록
	@Override
	public int insertMedia(SnsMediaVO snsMediaVO) {
		return mapper.insertMedia(snsMediaVO);
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
	//인기강사피드조회
	@Override
	public List<SnsPostVO> hotLecturerList() {
		return mapper.hotLecturerList();
	}
	//전체피드조회
	@Override
	public List<SnsPostVO> allList() {
		return mapper.allList();
	}
	//최신피드조회
	@Override
	public List<SnsPostVO> newList() {
		return mapper.newList();
	}
	//인기피드조회
	@Override
	public List<SnsPostVO> hotList() {
		return mapper.hotList();
	}
    //팔로잉피드조회
    public List<SnsPostVO> getFollowingFeeds(String memberId){
        return mapper.getFollowingFeeds(memberId);
    }

	//프로필조회
    @Override
    public SnsProfileVO getProfile(String memberId) {
        return mapper.getProfile(memberId);
    }
    //유저피드조회
    @Override
    public List<SnsPostVO> getUserFeed(String memberId) {
        return mapper.getUserFeed(memberId);
    }
	//피드상세조회
	@Override
	public SnsFeedVO getFeedDetail(int postId, String memberId){
	     return mapper.getFeedDetail(postId, memberId);
	}
	
    /*
     * 해시태그
     */
	//해시태그 업데이트 or 삽입
	@Override
	public int updateHashtag(int postId) {
		return mapper.updateHashtag(postId);
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
	public int unfollow(String followerId, String followingId) {
		return mapper.unfollow(followerId, followingId);
	}
	//팔로잉 조회
	@Override
	public List<SnsFollowVO> getFollowingList(String followerId) {
		return mapper.getFollowingList(followerId);
	}
	//팔로워 조회
	@Override
	public List<SnsFollowVO> getFollowerList(String followingId) {
		return mapper.getFollowerList(followingId);
	}
	//유저전체
	@Override
	public List<MemberVO> getUsers() {
		return mapper.getUsers();
	}
	//유저검색
	@Override
	public List<MemberVO> searchUser(String memberId, String nickname) {
		return mapper.searchUser(memberId, nickname);
	}
	//유저닉네임검색
    @Override
    public List<MemberVO> getUsersByNick(String nickname) {
        return mapper.getUsersByNick(nickname);
    }
	//해시태그검색
	@Override
	public List<SnsPostVO> searchHashtag(String hashtag) {
		return mapper.searchHashtag(hashtag);
	}
	//인기해시태그
    @Override
    public List<HashtagVO> selectHashtagForMain() {
        return mapper.selectHashtagForMain();
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
	public int deleteLike(int targetId, int targetType, String memberId) {
		return mapper.deleteLike(targetId, targetType, memberId);
	}
	//좋아요누적
	@Override
	public int sumLikes(JjimVO jjimVO) {
		return mapper.sumLikes(jjimVO);
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
	public int deleteCmt(int commId) {
		return mapper.deleteCmt(commId);
	}
	//댓글조회
	@Override
	public List<CommentsVO> getCmtLists(int postId) {
		return mapper.getCmtLists(postId);
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
	public int deleteReCmt(int commId) {
		return mapper.deleteReCmt(commId);
	}
	
    /*
     * 북마크
     */
    //컬렉션 등록
    @Override
    public int createBookmarkCtg(SnsBookmarkCatgVO bmkCtgVO) {
        return mapper.createBookmarkCtg(bmkCtgVO);
    }
    //컬렉션 이름수정
    @Override
    public int updateBookmarkCtgName(SnsBookmarkCatgVO bmkCtgVO) {
        return mapper.updateBookmarkCtgName(bmkCtgVO);
    }
    //컬렉션 삭제(안의 게시물도 전부 삭제되도록)
    @Override
    public int deleteBookmarkCtg(int catgId) {
        return mapper.deleteBookmarkCtg(catgId);
    }
    //컬렉션 목록
    @Override
    public List<SnsBookmarkCatgVO> getBookmarkCtgs(int catgId) {
        return mapper.getBookmarkCtgs(catgId);
    }
    //북마크 등록
    @Override
    public int addBookmark(SnsBookmarkVO bmkVO) {
        return mapper.addBookmark(bmkVO);
    }
    //북마크 삭제
    @Override
    public int deleteBookmark(int postId) {
        return mapper.deleteBookmark(postId);
    }
    //북마크 조회
    @Override
    public List<SnsBookmarkVO> getBookmarks(int catgId) {
        return mapper.getBookmarks(catgId);
    }
    //전체 북마크
    public List<SnsBookmarkVO> getAllBookmarks(){
    	return mapper.getAllBookmarks();
    }

}