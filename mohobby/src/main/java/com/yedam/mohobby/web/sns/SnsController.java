package com.yedam.mohobby.web.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.communal.JjimVO;
import com.yedam.mohobby.service.sns.SnsPostVO;
import com.yedam.mohobby.service.sns.SnsService;

@RestController
@CrossOrigin
public class SnsController {
    
    @Autowired
    SnsService service;
    
    //게시물 등록

	//게시물 수정

	//피드 삭제

	//인기강사피드
    @GetMapping("/sns/main/hotLecturerList")
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
    @GetMapping("/searchHashtag")
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