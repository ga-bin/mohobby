package com.yedam.mohobby.web.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.classes.ClassInfoRequestVO;
import com.yedam.mohobby.service.classes.ClassService;

@RestController
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
	
}
