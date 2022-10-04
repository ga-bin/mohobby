package com.yedam.mohobby.web.openbanking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.yedam.mohobby.service.openbanking.BankRealNameRequestVO;
import com.yedam.mohobby.service.openbanking.RequestHeaderVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class OpenBankingController {

	@GetMapping("/bankRealName")
	@ApiOperation(value = "계좌번호 실명 조회", notes="해당 계좌번호와 예금주명이 일치하는지 확인합니다.")
	public String accountNumber(
			@ApiParam(value = "은행 코드", required = true) @RequestParam String Bncd,
			@ApiParam(value = "계좌 번호", required = true) @RequestParam String Acno) 
			throws MalformedURLException 
	{
		String response = "";
		
		URL url;
		StringBuilder sb = new StringBuilder();
		BufferedReader br;
		BufferedWriter bw;
		
		
		//현재 날짜 구하기
		LocalDateTime date = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		
		//헤더 추가 정보
		RequestHeaderVO header = new RequestHeaderVO();
		if(Bncd == "011") {
			url = new URL("https://developers.nonghyup.com/InquireDepositorAccountNumber.nh");
			header.setApiNm("InquireDepositorAccountNumber");
		} else {
			url = new URL("https://developers.nonghyup.com/InquireDepositorOtherBank.nh");
			header.setApiNm("InquireDepositorOtherBank");
		}
		header.setTsymd(date.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		header.setIsTuno(date.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
		
		//리퀘스트 바디
		BankRealNameRequestVO requestBody = new BankRealNameRequestVO();
		requestBody.setHeader(header);
		requestBody.setAcno(Acno);
		requestBody.setBncd(Bncd);
		
		//리퀘스트 바디 -> json
		Gson gson = new Gson();
		String bodyJson = gson.toJson(requestBody);
		
		
		try { 
		
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
		  
			//Request Header 정의 
			con.setRequestProperty("Content-Type", "applicaton/json");
		  
			//전송방식 
			con.setRequestMethod("POST");
		  
			con.setConnectTimeout(5000); // 연결 타임아웃 설정(5초) 
			con.setReadTimeout(5000); //읽기 타임아웃 설정(5초) 
			con.setDoOutput(true); // URL 연결을 출력용으로 사용(true)
			
			bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
			bw.write(bodyJson);
			bw.flush();
			bw.close();
			
			System.out.println("getContentType():" + con.getContentType());
			System.out.println("getResponseCode():" + con.getResponseCode());
			System.out.println("getResponseMessage():" + con.getResponseMessage());
			
			
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8")); 
				String line; 
				while((line = br.readLine()) != null) { 
					sb.append(line).append("\n"); 
				}
				br.close();
				
				response = sb.toString();
		  }
		  
		  } catch(Exception e) {
			  e.printStackTrace();
		  }
		 
		System.out.println(response);
		return response;
	}
}
