package com.yedam.mohobby.sns;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.mohobby.mapper.sns.SnsMapper;
import com.yedam.mohobby.service.sns.SnsPostVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class SnsMapperTests {
	@Autowired
	SnsMapper mapper;
	
	@Test
	public void testFeedUpdate() {
		SnsPostVO vo = new SnsPostVO();
		vo.setPostId(1);
		vo.setContent("수정테스트");
		vo.setHashtag("수정해시태그");
		
		mapper.updateFeed(vo);
	}
}
