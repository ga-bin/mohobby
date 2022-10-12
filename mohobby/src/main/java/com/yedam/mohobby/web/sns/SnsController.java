package com.yedam.mohobby.web.sns;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@RestController
@RequestMapping("/sns")
@CrossOrigin
public class SnsController {
    
    @Autowired
    SnsService service;
    
    //게시물 등록 - 파일등록 처리중..
    @PostMapping(value = "/myfeed", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String insertFeed(@RequestPart MultipartFile file, @RequestPart SnsPostVO postvo) {
        try {
			//날짜별로 폴더를 생성해서 파일을 관리
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String dirName = dateFormat.format(date);
        	//저장할 폴더 경로
        	String uploadPath = "C:\\dev\\mohobby\\mohobby\\mohobby\\src\\assets\\image\\sns" + dirName;
        	
        	File folder = new File(uploadPath);
			if(!folder.exists()) {
				folder.mkdir(); //폴더가 존재하지 않을시 생성
			}
			//진짜 파일 이름
			String fileRealName = file.getOriginalFilename();
			
			//파일명 고유 랜덤 문자로 생성
			UUID uuid = UUID.randomUUID();
			String uuids = uuid.toString().replaceAll("-", "");
			
			//확장자를 추출
			String extension = fileRealName.substring(fileRealName.indexOf("."), fileRealName.length());
			
			System.out.println("저장할 폴더 경로: " + uploadPath);
			System.out.println("실제 파일명: " + fileRealName);
			System.out.println("폴더명: " + dirName);
			System.out.println("확장자: " + extension);
			System.out.println("고유랜덤문자: " + uuids);
			
			//고유번호로 변환된 파일이름
			String fileName = uuids + extension;
			System.out.println("변경되어 저장되는 파일명: " + fileName);
			
			//업로드한 파일을 서버 컴퓨터의 지정한 경로에 저장
			File saveFile = new File(uploadPath + "\\" + fileName);
			file.transferTo(saveFile);
			
			//DB insert
			SnsPostVO postVO = new SnsPostVO();
			postVO.setPostId(postvo.getPostId());
			postVO.setMemberId(postvo.getMemberId());
			postVO.setHashtag(postvo.getHashtag());
			postVO.setContent(postvo.getContent());
			postVO.setWriteDate(null);
			postVO.setThumbnail(fileName);
			postVO.setLikes(0);
			postVO.setCmts(0);
			service.insertFeed(postVO);
			
			SnsMediaVO mediaVO = new SnsMediaVO();
			mediaVO.setMediaId(0);
			mediaVO.setPostId(postvo.getPostId());
			mediaVO.setMediaType(0);
			mediaVO.setImgUrl(uploadPath);
			mediaVO.setFileName(fileName);
			mediaVO.setFileRealname(fileRealName);
			mediaVO.setDirName(dirName);
			service.insertMedia(mediaVO);
			
			
			System.out.println("글 등록 완료");
			return "success";
			
		} catch (Exception e) {
			System.out.println("업로드 실패: " + e.getMessage());
			return "fail";
		}
    }
   //게시물 수정 - 테스트완료
    @PutMapping("/myfeed/{postId}")
    public String updateFeed(@PathVariable int postId, @RequestBody SnsPostVO snsPostVO) {
        try {
            snsPostVO.setPostId(postId);
            service.updateFeed(snsPostVO);
            System.out.println("글 수정 완료");
            return "success";
        } catch (Exception e) {
            System.out.println("글 수정 실패: " + e.getMessage());
            return "fail";
        }
    }
   //피드 삭제 - 테스트완료
    @DeleteMapping("/myfeed/{postId}")
    public String deleteFeed(@PathVariable int postId){
        try {
            service.deleteFeed(postId);
            System.out.println("글 삭제 완료");
            return "success";
        } catch (Exception e) {
            System.out.println("글 삭제 실패: " + e.getMessage());
            return "fail";
        }
    }
   //인기강사피드조회 - 테스트완료
    @GetMapping("/main/top20LecturerFeeds")
    public List<SnsPostVO> hotLecturerList() {
        return service.hotLecturerList();
    }
   //전체피드조회 - 테스트완료
    @GetMapping("/main/allFeeds")
    public List<SnsPostVO> allList() {
        return service.allList();
    }
   //최신피드조회 - 테스트완료
    @GetMapping("/main/newFeeds")
    public List<SnsPostVO> newList() {
        return service.newList();
    }
   //인기피드조회 - 테스트완료(좀 느림)
    @GetMapping("/main/hotFeeds")
    public List<SnsPostVO> hotList() {
        return service.hotList();
    }
   //해시태그 top6
    @GetMapping("/main/hashtag")
    public List<HashtagVO> selectHashtagForMain(){
        return service.selectHashtagForMain();
    }
   //팔로잉피드조회 - 
    @GetMapping("/main/followingFeeds/{memberId}")
    public List<SnsPostVO> getFollowingFeeds(@PathVariable String memberId) {
        return service.getFollowingFeeds(memberId);
    }
  //프로필조회
    @GetMapping("/user/profile/{memberId}")
    public SnsProfileVO getProfile(@PathVariable("memberId") String memberId) {
        	return service.getProfile(memberId);//컬럼명과 컬럼값이 키와 값으로 매핑이 된다. ㄴ
    }
    //유저피드조회
    @GetMapping("/user/user_feeds/{memberId}")
    public List<SnsPostVO> getUserFeed(@PathVariable("memberId") String memberId) {
        return service.getUserFeed(memberId);
    }
   //피드상세조회 - 테스트완료
    @GetMapping("/user/feed_detail")
    public SnsFeedVO getFeedDetail(@RequestParam int postId) {
        	System.out.println("프로필조회 성공");
            return service.getFeedDetail(postId);
    }
    
    /*
     * 해시태그
     */
   //해시태그 업데이트 or 삽입 - 테스트완료
    @PutMapping("/updateHashtag/{postId}")
    public String searchHashtag(@PathVariable int postId) {
        try {
            service.updateHashtag(postId);
            System.out.println("해시태그 처리 완료");
            return "success";
        } catch (Exception e) {
            System.out.println("해시태그 처리 실패: " + e.getMessage());
            return "fail";
        }
    }
    
    /*
     * 팔로우
     */
   //팔로우 - 테스트완료
    @PostMapping("/follow")
    public String follow(@RequestBody SnsFollowVO followVO) {
        try {
            service.follow(followVO);
            System.out.println("팔로우 완료");
            return "success";
        } catch (Exception e) {
            System.out.println("팔로우 실패: " + e.getMessage());
            return "fail";        
        }
    }
   //언팔로우 - 테스트완료
    @DeleteMapping("/follow/{followerId}/{followingId}")
    public String unfollow(@PathVariable String followerId, @PathVariable String followingId) {
        try {
            service.unfollow(followerId,followingId);
            System.out.println("언팔로우 완료");
            return "success";
        } catch (Exception e) {
            System.out.println("언팔로우 실패: " + e.getMessage());
            return "fail";        
        }
    }
   //팔로잉 조회 - 테스트완료
    @GetMapping("/follow/search/following/{followerId}")
    public List<SnsFollowVO> getFollowingList(@PathVariable String followerId){
        return service.getFollowingList(followerId);
    }
    
   //팔로워 조회 - 테스트완료
    @GetMapping("/follow/search/follower/{followingId}")
    public List<SnsFollowVO> getFollowerList(@PathVariable String followingId){
        return service.getFollowerList(followingId);
    }
    
   //유저전체 - 테스트완료
    @GetMapping("/search/allUsers")
    public List<MemberVO> gerUsers() {
        return service.getUsers();
    }
   //유저 아이디, 닉네임검색 - 테스트완료
    @GetMapping("/search/user")
    public List<MemberVO> searchUser(@PathVariable String memberId, @PathVariable String nickname){
        return service.searchUser(memberId, nickname);
    }
   //유저닉네임검색 - 테스트완료
    @GetMapping("/search/nick/{nickname}")
    public List<MemberVO> getUsersByNick(@PathVariable String nickname){
        return service.getUsersByNick(nickname);
    }
   //해시태그검색 - 
    @GetMapping("/search/hashtag")
    public List<SnsPostVO> searchHashtag(@RequestParam String hashtag){
        return service.searchHashtag(hashtag);
    }
    
    /*
     * 좋아요
     */
  //좋아요 - 테스트완료
    @PostMapping("/like")
    public int addLike(@RequestBody JjimVO jjimVO) {
    	return service.addLike(jjimVO);
    }
  //좋아요취소 - 테스트완료
    @DeleteMapping("/like")
    public int deleteLike(@RequestParam int targetId,@RequestParam String memberId) {
    	return service.deleteLike(targetId, memberId);
    }
   //좋아요누적
    @PutMapping("/like")
    public int sumLikes(@RequestParam int targetId, @RequestParam int postId) {
        return service.sumLikes(targetId, postId);
        
    }
    
    /*
     * 댓글
     */
   //댓글입력 - 테스트완료
    @PostMapping("/cmt") 
    public String insertCmt(@RequestBody CommentsVO commentsVO) {
        service.inserCmt(commentsVO);
        System.out.println("댓글입력 완료");
        return "success";
    }
   //댓글수정
    @PutMapping("/cmt/{commId}")
    public String updateCmt(@PathVariable int commId, @RequestBody CommentsVO commentsVO) {
        try {
        	commentsVO.setCommId(commId);
            service.updateCmt(commentsVO);
            System.out.println("댓글수정 완료");
            return "success";
        } catch (Exception e) {
            System.out.println("댓글수정 실패");
            return "fail";
        }
    }
   //댓글삭제
    @DeleteMapping("/cmt/{commId}")
    public String deleteCmt(@PathVariable("cmtId") int commId){
        try {
            service.deleteFeed(commId);
            System.out.println("댓글삭제 완료");
            return "success";
        } catch (Exception e) {
            System.out.println("대댓글삭제 실패");
            return "fail";
        }
    }
   //댓글조회
    @GetMapping("/cmt/{postId}")
    public List<CommentsVO> getCmtLists(@PathVariable int postId) {
        return service.getCmtLists(postId);
    }
    
    /*
     * 대댓글
     */
    //대댓입력 - 테스트완료
    @PostMapping("/recmt")
    public String insertReCmt(@RequestBody CommentsVO commentsVO) {
        try {
            service.insertReCmt(commentsVO);
            return "success";
        } catch (Exception e) {
            System.out.println("댓글입력 실패: " + e.getMessage());
            return "fail";
        }
    }
    //대댓수정
    @PutMapping("/recmt/{commId}")
    public String updateReCmt(@PathVariable int commId, @RequestBody CommentsVO commentsVO) {
        try {
          commentsVO.setCommId(commId);
          service.updateReCmt(commentsVO);
          return "success";
        } catch (Exception e) {
            System.out.println("댓글수정 실패: " + e.getMessage());
            return "fail";
        }
    }
    //대댓삭제
    @DeleteMapping("/recmt/{commId}")
    public String deleteReCmt(@PathVariable int commId) {
         try {
             service.deleteReCmt(commId);
             System.out.println("대댓글삭제 완료");
             return "success";
        } catch (Exception e) {
            System.out.println("대댓글삭제 실패" + e.getMessage());
            return "fail";
        }
    }
    
    /*
     * 북마크
     */
    //컬렉션등록
    @PostMapping("/bookmark/collection") 
    public String createBookmarkCtg(@RequestBody SnsBookmarkCatgVO bmCtgVO) {
        try {
        	service.createBookmarkCtg(bmCtgVO);
        	System.out.println("컬렉션추가 완료");
        	return "success";
		} catch (Exception e) {
			System.out.println("컬렉션추가 실패: " + e.getMessage() );
			return "fail";
		}
    }
    //컬렉션이름수정
    @PutMapping("/collection/{catgId}")
    public String updateReCmt(@PathVariable int catgId, @RequestBody SnsBookmarkCatgVO bmCtgVO) {
        try {
          bmCtgVO.setCatgId(catgId);
          service.updateBookmarkCtgName(bmCtgVO);
          System.out.println("컬렉션수정 완료");
          return "success";
        } catch (Exception e) {
            System.out.println("컬렉션수정 실패: " + e.getMessage());
            return "fail";
        }
    }
    //컬렉션삭제(안의 게시물도 전부 삭제되도록)
    @DeleteMapping("/collection/{catgId}")
    public String deleteBookmarkCtg(@PathVariable int catgId) {
         try {
             service.deleteBookmarkCtg(catgId);
             System.out.println("컬렉션삭제 완료");
             return "success";
        } catch (Exception e) {
            System.out.println("컬렉션삭제 실패" + e.getMessage());
            return "fail";
        }
    }
    //북마크 등록
    @PostMapping("/collection/bookmark")
    public String addBookmark(@RequestBody SnsBookmarkVO bmVO) {
    	try {
			service.addBookmark(bmVO);
			System.out.println("북마크완료");
			return "success";
		} catch (Exception e) {
			System.out.println("북마크실패: " + e.getMessage());
			return "fail";
		}
    }
    //북마크 삭제
    @DeleteMapping("/collection/bookmark/{postId}")
    public String deleteBookmark(@PathVariable int postId) {
    	try {
			service.deleteBookmark(postId);
			System.out.println("북마크삭제 완료");
			return "success";
		} catch (Exception e) {
			System.out.println("북마크삭제 실패: " + e.getMessage());
			return "fail";
		}
    }
    //컬렉션별 북마크 조회
    @GetMapping("/collection/bookmark/{catgId}")
    public List<SnsBookmarkVO> getBookmarks(@PathVariable int catgId){
			System.out.println("북마크조회 완료");
			return service.getBookmarks(catgId);
    }
}

