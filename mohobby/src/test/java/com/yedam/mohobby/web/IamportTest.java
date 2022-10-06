package com.yedam.mohobby.web;

import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.mohobby.service.iamport.GetCertificationVO;
import com.yedam.mohobby.web.iamport.IamportController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class IamportTest {
	
	@Autowired
	IamportController iamport;
	
	@Test
	public void Test() throws MalformedURLException {
		GetCertificationVO certVO = new GetCertificationVO();
		certVO.setImp_uid("imp_995958977292");
		iamport.getToken(certVO);
	}
}
