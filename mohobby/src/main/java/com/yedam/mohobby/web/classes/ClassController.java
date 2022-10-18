
package com.yedam.mohobby.web.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.classes.ClassBoardVO;
import com.yedam.mohobby.service.classes.ClassChapterVO;
import com.yedam.mohobby.service.classes.ClassImageVO;
import com.yedam.mohobby.service.classes.ClassInfoRequestVO;
import com.yedam.mohobby.service.classes.ClassListRequestVO;
import com.yedam.mohobby.service.classes.ClassReviewVO;
import com.yedam.mohobby.service.classes.ClassService;
import com.yedam.mohobby.service.classes.ClassesVO;
import com.yedam.mohobby.service.communal.JjimVO;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ClassController {
	@Autowired
	ClassService classService;
	
	//에디터 이미지 저장
	@PostMapping("/uploadClassImage")
	public void uploadClassImage(@RequestBody ClassImageVO req) {
	    classService.uploadClassImage(req);
	}
	
	//html 파일 생성
	@PostMapping("/saveClassInfo")
	public void saveClassInfo(@RequestBody ClassInfoRequestVO req) {
		classService.saveClassInfo(req);
	}
	
	//html 파일 경로 가져오기
	@GetMapping("/readClassInfo")
	public String readClassInfo(@RequestParam int classId) {
	    return classService.readClassInfo(classId);
	}
	
	
	
	//QR코드 생성
	@GetMapping(
	        value = "/AttdQR",
	        produces = MediaType.IMAGE_PNG_VALUE
	        )
	public @ResponseBody String createCodeImg(
	        @RequestParam String link
	){
	    
	    return classService.createCodeImg(link);
	       
	    
	}
	
	
	//강의 전체 조회
	/**
	 * 
	 * @param catg
	 * @param memberId
	 * @return
	 */
	@GetMapping("/class/{catg}")
	public @ResponseBody List<ClassesVO> listAll(
	        @PathVariable String catg,
	        @RequestParam String memberId
	) {
	    ClassListRequestVO req = new ClassListRequestVO();
	    req.setCatg(catg);
	    req.setMemberId(memberId);
	    return classService.listAll(req);
	}
	
	
	//강의 단건 조회
	/**
	 * 
	 * @param classId
	 * @return
	 */
	@GetMapping("/class/detail/{classId}")
	public @ResponseBody ClassesVO listOne(@PathVariable int classId,  @RequestParam String memberId) {
	    ClassListRequestVO req = new ClassListRequestVO();
	    req.setClassId(classId);
	    req.setMemberId(memberId);
	    return classService.listOne(req);
	}
	
	
	//후기평가조회
	@GetMapping("/class/review")
	public @ResponseBody ClassReviewVO getClassReview(@RequestParam int classId) {
		return classService.getClassReview(classId);
	}
	
	//강의후기리스트
	@GetMapping("/class/board")
	public @ResponseBody List<ClassBoardVO> getClassReviewList(@RequestParam int classId, @RequestParam int boardType) {
		return classService.getClassBoardList(classId, boardType);
	}
	
	//강의qna리스트
	@GetMapping("/class/qna/{classId}")
	public @ResponseBody List<ClassBoardVO> getClassQnaList(@PathVariable int classId) {
		return classService.getClassBoardList(classId, 1);
	}
	
	//강의게시글등록
	@PostMapping("/class/board")
	public ClassBoardVO addClassBoard(@RequestBody ClassBoardVO board) {
	    return classService.addClassBoard(board);
	}
	
	//강의게시글삭제
	@DeleteMapping("/class/board")
	public void deleteClassBoard(@RequestParam int boardId) {
	    classService.deleteClassBoard(boardId);
	}
	
	//강의게시글수정
	@PutMapping("/class/board")
	public void updateClassBoard(@RequestBody ClassBoardVO board) {
	    classService.updateClassBoard(board);
	}
	
	//찜 등록
	@PostMapping("/class/jjim")
	public void addJjim(@RequestBody JjimVO jjim) {
	    classService.addJjim(jjim);
	}
	
	//찜 등록 취소
	@DeleteMapping("/class/jjim")
	public void delJjim(@RequestBody JjimVO jjim) {
	    classService.deleteJjim(jjim);
	}
	
	// 강의챕터조회
	@GetMapping("/class/chapterList")
	 public List<ClassChapterVO> getChapterList(@RequestParam int classId) {
        return classService.getChapterList(classId);
    }

}

