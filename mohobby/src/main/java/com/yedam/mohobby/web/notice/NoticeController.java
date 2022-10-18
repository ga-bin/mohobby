package com.yedam.mohobby.web.notice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.notice.NoticeService;
import com.yedam.mohobby.service.notice.NoticeVO;

@RestController
@CrossOrigin(origins = "*")
public class NoticeController {

	@Autowired
	NoticeService nService;

	@GetMapping("/getAllNotice")
	public @ResponseBody List<NoticeVO> AllNotice(@RequestParam String memberId) {
		return nService.getAllNotice(memberId);
	}

}
