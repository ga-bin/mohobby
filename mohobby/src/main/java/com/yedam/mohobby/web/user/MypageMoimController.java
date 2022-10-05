package com.yedam.mohobby.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.mohobby.service.user.MypageMoimService;

@RestController
public class MypageMoimController {

@Autowired
MypageMoimService service;
}
