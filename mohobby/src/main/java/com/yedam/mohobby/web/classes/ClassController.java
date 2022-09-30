package com.yedam.mohobby.web.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.classes.ClassService;

@RestController
public class ClassController {
	@Autowired
	ClassService classService;
	
	
}
