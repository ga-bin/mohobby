package com.yedam.mohobby.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.mohobby.web.classes.ClassController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class ClassTest {
	
	@Autowired
	ClassController classController;
	
	@Test
	public void saveClassInfo() {
		classController.saveClassInfo("<h1>Hello, World!</h1>", "3");
	}

}
