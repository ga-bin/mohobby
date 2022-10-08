package com.yedam.mohobby.web.sns;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.mohobby.service.communal.JjimVO;
import com.yedam.mohobby.service.sns.SnsMediaVO;
import com.yedam.mohobby.service.sns.SnsPostVO;
import com.yedam.mohobby.service.sns.SnsService;


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
    
    
    //게시물 등록
    @PostMapping("/sns/myfeed")
    public String insertFeed(MultipartFile file, @RequestBody SnsPostVO postvo, @RequestBody SnsMediaVO mediavo) {
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
			mediaVO.setMediaId(mediavo.getMediaId());
			mediaVO.setPostId(postvo.getPostId());
			mediaVO.setMediaType(0);
			mediaVO.setImgUrl(uploadPath);
			mediaVO.setFileName(fileName);
			mediavo.setFileRealname(fileRealName);
			mediavo.setDirName(dirName);
			
			System.out.println("글 등록 완료");
			return "success";
			
		} catch (Exception e) {
			System.out.println("업로드 실패: " + e.getMessage());
			return "fail";
		}
    }
    
   //게시물 수정
    @PutMapping("/sns/myfeed")
    public SnsPostVO updateFeed(@RequestBody SnsPostVO snsPostVO) {
            service.updateFeed(snsPostVO);
            System.out.println("글 수정 완료");
        return snsPostVO;
    }
   //피드 삭제
    @DeleteMapping("/sns/myfeed/{postId}")
    public int deleteFeed(@PathVariable("postId") int postId){
        SnsPostVO snsPostVO = new SnsPostVO();
        snsPostVO.setPostId(postId);
        service.deleteFeed(postId);
        System.out.println("글 삭제 완료");
        return postId;
    }

   //인기강사피드
    @GetMapping("/sns/main/top20LecturerList")
    public List<SnsPostVO> hotLecturerList() {
           return service.hotLecturerList();
    }
   //전체피드
    @PostMapping("/sns/main/allList")
    public List<SnsPostVO> allList() {
        return service.allList();
    }
   //최신피드
    @GetMapping("/sns/main/newList")
    public List<SnsPostVO> newList() {
        return service.newList();
    }
   //인기피드
    @GetMapping("/sns/main/hotList")
    public List<SnsPostVO> hotList() {
        return service.hotList();
    }
   //유저피드목록
    
    
   //해시태그 업데이트 or 삽입
    @PostMapping("/sns/updateHashtag")
    public List<SnsPostVO> searchHashtag() {
        return service.searchHashtag(null);
    }
    /*
     * 팔로우
     */
   //팔로우
   //언팔로우
   //팔로잉 조회
   //팔로워 조회
   //유저전체
   //유저단건검색
   //해시태그검색
   //유저닉네임검색   
    
    /*
     * 좋아요
     */
   //좋아요
   //좋아요취소
   //좋아요누적
   

//    @PostMapping("/sns/addCmt/")
//    public CommentVO inserCmt(@RequestBody)
    
    
    
    
  //좋아요
    @PostMapping("/sns/like")
    public JjimVO addLike(@RequestBody JjimVO jjimVO) {
        service.addLike(jjimVO);
        return jjimVO;
    }
  //좋아요취소
    @DeleteMapping("/sns/like")
    public JjimVO deleteLike(@RequestBody JjimVO jjimVO) {
        service.deleteLike(jjimVO);
        return jjimVO;
    }
   //좋아요누적
    @PutMapping("/sns/like")
    public SnsPostVO sumLikes(@RequestBody SnsPostVO snsPostVO) {
        service.sumLikes(snsPostVO);
        return snsPostVO;
    }
    
    /*
     * 댓글
     */
   //댓글입력
   //댓글수정
   //댓글삭제
   //댓글조회
    
    /*
     * 대댓글
     */
   //대댓입력
}