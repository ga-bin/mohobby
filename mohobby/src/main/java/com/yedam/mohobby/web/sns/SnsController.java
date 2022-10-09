package com.yedam.mohobby.web.sns;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.mohobby.service.communal.CommentsVO;
import com.yedam.mohobby.service.communal.JjimVO;
import com.yedam.mohobby.service.sns.SnsFollowVO;
import com.yedam.mohobby.service.sns.SnsMediaVO;
import com.yedam.mohobby.service.sns.SnsPostVO;
import com.yedam.mohobby.service.sns.SnsService;
import com.yedam.mohobby.service.user.MemberVO;


/**
 * @create 22/10/08
 * @author sunjin
 * @title sns controller
 */
@RestController
@CrossOrigin
public class SnsController {
    
    @Autowired
    SnsService service;
    
    SnsPostVO snspostVO = new SnsPostVO();
    
    
    //게시물 등록 - 파일등록 처리중..
    @PostMapping(value = "/sns/myfeed", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
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
    
   //게시물 수정
    @PutMapping("/sns/myfeed")
    public String updateFeed(@RequestBody SnsPostVO snsPostVO) {
        try {
            service.updateFeed(snsPostVO);
            System.out.println("글 수정 완료");
            return "success";
        } catch (Exception e) {
            System.out.println("글 수정 실패: " + e.getMessage());
            return "fail";
        }
    }

    /*
     * @PutMapping("/sns/myfeed/{memberId}")
     * public String updateFeed(@PathVariable("postId") int memberId, @RequestBody SnsPostVO snsPostVO) {
     * try {
     * service.updateFeed(memberId);
     * System.out.println("글 수정 완료");
     * return "success";
     * } catch (Exception e) {
     * System.out.println("글 수정 실패: " + e.getMessage());
     * return "fail";
     * }
     * }
     */
   //피드 삭제 - 테스트완료
    @DeleteMapping("/sns/myfeed/{postId}")
    public String deleteFeed(@PathVariable("postId") int postId){
        try {
            snspostVO.setPostId(postId);
            service.deleteFeed(postId);
            System.out.println("글 삭제 완료");
            return "success";
        } catch (Exception e) {
            System.out.println("글 삭제 실패: " + e.getMessage());
            return "fail";
        }
    }

   //인기강사피드조회 - 테스트완료
    @GetMapping("/sns/main/top20LecturerFeeds")
    public List<SnsPostVO> hotLecturerList() {
        return service.hotLecturerList();
    }
   //전체피드조회 - 테스트완료
    @GetMapping("/sns/main/allFeeds")
    public List<SnsPostVO> allList() {
        return service.allList();
    }
   //최신피드조회 - 테스트완료
    @GetMapping("/sns/main/newFeeds")
    public List<SnsPostVO> newList() {
        return service.newList();
    }
   //인기피드조회 - 테스트완료(좀 느림)
    @GetMapping("/sns/main/hotFeeds")
    public List<SnsPostVO> hotList() {
        return service.hotList();
    }
   //팔로잉피드조회
    @GetMapping("/sns/main/followingFeeds/{memberId}")
    public List<SnsPostVO> getFollowingFeeds(@PathVariable("memberId") String memberId) {
        return service.getFollowingFeeds(memberId);
    }
   //유저피드목록
    @GetMapping("sns/user/feed/{memberId}")
    public List<SnsPostVO> getUserFeed(@PathVariable("memberId") String memberId) {
        return service.getUserFeed(memberId);
    }
  //프로필조회
    @GetMapping("sns/user/profile/{memberId}")
    public String getProfile(@PathVariable("memberId") String memberId, Model model) {
        try {
            model.addAttribute("feed", service.getProfile(memberId));
            return "success"; //컬럼명과 컬럼값이 키와 값으로 매핑이 된다. ㄴ
        } catch (Exception e) {
            System.out.println("프로필조회 실패: " + e.getMessage());
            return "fail";
        }
    }
    
    
    /*
     * 해시태그
     */
   //해시태그 업데이트 or 삽입 - 테스트완료
    @PutMapping("/sns/updateHashtag/{postId}")
    public String searchHashtag(@PathVariable("postId") int postId) {
        try {
            snspostVO.setPostId(postId);
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
    @PostMapping("/sns/follow")
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
    @DeleteMapping("/sns/follow/{followerId}/{followingId}")
    public String unfollow(@PathVariable("followerId") String followerId, @PathVariable("followingId") String followingId) {
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
    @GetMapping("/sns/follow/search/following/{followerId}")
    public List<SnsFollowVO> getFollowingList(@PathVariable("followerId") String followerId){
        return service.getFollowingList(followerId);
    }
    
   //팔로워 조회 - 테스트완료
    @GetMapping("/sns/follow/search/follower/{followingId}")
    public List<SnsFollowVO> getFollowerList(@PathVariable("followingId") String followingId){
        return service.getFollowerList(followingId);
    }
    
   //유저전체
    @GetMapping("/sns/search/allUsers")
    public List<MemberVO> gerUsers() {
        return service.getUsers();
    }
   //유저단건검색
    @GetMapping("/sns/search/user/{memberId}")
    public List<MemberVO> searchUser(@PathVariable("memberId") String memberId){
        return service.searchUser(memberId);
    }
   //유저닉네임검색
    @GetMapping("/sns/search/nickname/{nickname}")
    public List<MemberVO> getUsersByNick(@PathVariable("nickname") String nickname){
        return service.searchUser(nickname);
    }
   //해시태그검색
    @GetMapping("/sns/search/hashtag/{hashtag}")
    public List<MemberVO> searchHashtag(@PathVariable("hashtag") String hashtag){
        return service.searchUser(hashtag);
    }
    
    /*
     * 좋아요
     */
    
  //좋아요
    @PostMapping("/sns/like")
    public String addLike(@RequestBody JjimVO jjimVO) {
        service.addLike(jjimVO);
        return "success";
    }
  //좋아요취소
    @DeleteMapping("/sns/like")
    public String deleteLike(@RequestBody JjimVO jjimVO) {
        service.deleteLike(jjimVO);
        return "success";
    }
   //좋아요누적
    @PutMapping("/sns/like")
    public String sumLikes(@RequestBody SnsPostVO snsPostVO) {
        service.sumLikes(snsPostVO);
        return "success";
    }
    
    /*
     * 댓글
     */
   //댓글입력
    @PostMapping("/sns/cmt")
    public String insertCmt(@RequestBody CommentsVO commentsVO) {
        service.inserCmt(commentsVO);
        System.out.println("댓글입력 완료");
        return "success";
    }
   //댓글수정
    @PutMapping("/sns/cmt")
    public String updateCmt(@RequestBody CommentsVO commentsVO) {
        service.updateCmt(commentsVO);
        System.out.println("댓글수정 완료");
        return "success";
    }
   //댓글삭제
    @DeleteMapping("/sns/cmt/{commId}")
    public String deleteCmt(@PathVariable("commId") int commId){
        try {
            snspostVO.setPostId(commId);
            service.deleteFeed(commId);
            System.out.println("댓글삭제 완료");
            return "success";
        } catch (Exception e) {
            System.out.println("대댓글삭제 실패");
            return "fail";
        }
    }
   //댓글조회
    @GetMapping("/sns/cmt/{postId}")
    public List<CommentsVO> getCmtList(@PathVariable("postId") int postId) {
        return service.getCmtList(postId);
    } 
    /*
     * 대댓글
     */
    //대댓입력
    @PostMapping("/sns/recmt")
    public String insertReCmt(@RequestBody CommentsVO ommentsVO) {
        try {
            service.insertReCmt(ommentsVO);
            return "success";
        } catch (Exception e) {
            System.out.println("댓글입력 실패: " + e.getMessage());
            return "fail";
        }
    }
    //대댓수정
    @PutMapping("/sns/recmt/{commId}")
    public String updateReCmt(@PathVariable("commId") int commId, @RequestBody CommentsVO commentsVO) {
        try {
          return "success";
        } catch (Exception e) {
            System.out.println("댓글수정 실패: " + e.getMessage());
            return "fail";
        }

    }
    //대댓삭제
    @DeleteMapping("/sns/recmt/{commId}")
    public String deleteReCmt(@PathVariable("commId") int commId) {
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
    //북마크등록
    
    //북마크 이름수정
    
    //북마크 삭제(안의 게시물도 전부 삭제되도록)
}