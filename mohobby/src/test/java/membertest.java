import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.mohobby.mapper.user.MemberMapper;
import com.yedam.mohobby.service.admin.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:/src/main/wepbapp/WEB-INF/spring/**/*-context.xml'}")
public class membertest {

	@Autowired
	MemberMapper mapper;
	
	@Test
	public MemberVO getMemberTest() {
		String memberId = "gabin";
		return mapper.getMember(memberId);
	};
}
