package com.yedam.mohobby.web.classes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.yedam.mohobby.service.classes.ClassInfoRequestVO;
import com.yedam.mohobby.service.classes.ClassListRequestVO;
import com.yedam.mohobby.service.classes.ClassService;
import com.yedam.mohobby.service.classes.ClassesVO;
import com.yedam.mohobby.service.communal.JjimVO;
import com.yedam.mohobby.service.sns.SnsService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class ClassController {
	@Autowired
	ClassService classService;
	
	//html 파일 생성
	@PostMapping("/saveClassInfo")
	public void saveClassInfo(@RequestBody ClassInfoRequestVO req) {
		System.out.println(req.getContent());
		System.out.println(req.getFilename());
		String path = ClassController.class.getResource("/").getPath();
		path = path.substring(0, path.lastIndexOf("mohobby"));
		path = path.substring(0, path.lastIndexOf("mohobby")+"mohobby".length());
		
		System.out.println(path);
		
		path += "/mohobby/src/main/webapp/resources/html/classInfo/";
		path += req.getFilename();
		path += ".html";
		System.out.println(path);
		
		File file = new File(path);
		
		try {
			FileOutputStream fos = new FileOutputStream(file, false);
			fos.write(req.getContent().getBytes());
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//QR코드 생성
	@GetMapping(
	        value = "/AttdQR",
	        produces = MediaType.IMAGE_PNG_VALUE
	        )
	public @ResponseBody String createCodeImg(
	        @RequestParam String link
	        ) throws IOException, WriterException {
	    
	    int width = 400;
	    int height = 400;
	    BitMatrix matrix = new MultiFormatWriter().encode(link, BarcodeFormat.QR_CODE, width, height);
	    
	    try(ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	        
	        MatrixToImageWriter.writeToStream(matrix, "PNG", out);
	        
	       String base64 = new String(Base64.getEncoder().encode(out.toByteArray()));
	       return "data:image/png;base64," + base64;
	       
	    }
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
}
