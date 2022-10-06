package com.yedam.mohobby.web.iamport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yedam.mohobby.service.iamport.GetCertificationVO;
import com.yedam.mohobby.service.iamport.GetTokenRequestVO;
import com.yedam.mohobby.service.iamport.GetTokenResponseVO;

@RestController
@RequestMapping("/iamport")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class IamportController {
	
	@GetMapping("/token")
	public GetTokenResponseVO getToken(
			@RequestBody GetCertificationVO certVO
			) throws MalformedURLException {
		
		
		// 토큰 발급받기
		String response = "";
		String token = "";
		GetTokenResponseVO respVO = new GetTokenResponseVO();
		
		URL url;
		StringBuilder sb = new StringBuilder();
		OutputStream os;
		BufferedReader br;
		Gson gson = new Gson();
		
	
		
		//헤더 추가 정보
		url = new URL("https://api.iamport.kr/users/getToken");
		
		
		//리퀘스트 바디
		GetTokenRequestVO requestBody = new GetTokenRequestVO();
		
		//리퀘스트 바디 -> json
		String bodyJson = gson.toJson(requestBody);
		System.out.println(bodyJson);
		
		
		try { 
		
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
		  
			//Request Header 정의 
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
		  
			//전송방식 
			con.setRequestMethod("POST");
		  
			con.setConnectTimeout(5000); // 연결 타임아웃 설정(5초) 
			con.setReadTimeout(5000); //읽기 타임아웃 설정(5초) 
			con.setDoOutput(true); // URL 연결을 출력용으로 사용(true)
			
			
			os = con.getOutputStream();
			os.write(bodyJson.toString().getBytes("utf-8"));
			os.close();
			
			
			System.out.println("getContentType():" + con.getContentType());
			System.out.println("getResponseCode():" + con.getResponseCode());
			System.out.println("getResponseMessage():" + con.getResponseMessage());
			
			
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8")); 
				String line = null; 
				while((line = br.readLine()) != null) { 
					sb.append(line + "\n"); 
				}
				br.close();
				
				response = ""+sb.toString();
				System.out.println(response);
				
				//json 객체
				@SuppressWarnings("deprecation")
				JsonObject respJson = new JsonParser().parse(response).getAsJsonObject();
				
				//response 객체
				if(respJson.has("response")) {
					respJson = (JsonObject) respJson.get("response");
					token = respJson.get("access_token").getAsString();
					System.out.println("token : " + token);
				}
				
				
		    }
		  
		  } catch(Exception e) {
			  e.printStackTrace();
		  }
		
		
		// 인증정보 조회하기
		url = new URL("https://api.iamport.kr/certifications" + certVO.getImp_uid());
		
		try {
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Authorization", token);
			
			con.setRequestMethod("GET");
			  
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setDoOutput(true);
			
			con.connect();
			
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
			
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				sb = new StringBuilder();
				String line = null; 
				while((line = br.readLine()) != null) { 
					sb.append(line + "\n"); 
				}
				br.close();
				response = ""+sb.toString();
				System.out.println("response : " + response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(respVO);
		return respVO;
	}
}
