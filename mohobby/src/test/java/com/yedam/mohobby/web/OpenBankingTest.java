package com.yedam.mohobby.web;

import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.mohobby.web.openbanking.OpenBankingController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class OpenBankingTest {

	@Autowired
	private OpenBankingController controller;
	
	@Test
	public void accountNumberTest() throws MalformedURLException {
		controller.accountNumber("011", "3020000006955");
	}
}
