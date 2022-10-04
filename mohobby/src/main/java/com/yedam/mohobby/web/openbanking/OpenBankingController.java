
package com.yedam.mohobby.web.openbanking;
  
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.PostMapping; 
import  org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RestController;
  
import com.yedam.mohobby.feign.openbanking.OpenBankingFeign; 
import  com.yedam.mohobby.service.openbanking.TokenRequestVO; 
import com.yedam.mohobby.service.openbanking.TokenResponseVO;
  
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST}) 
public class OpenBankingController {
  
	  @Autowired(required=false)
	  private OpenBankingFeign openBankingFeign;
	  
	  @Value("${client_secret}") 
	  private String client_secret;
  
	  @PostMapping("/requestToken") 
	  public TokenResponseVO requestToken(@RequestBody TokenRequestVO tokenRequest) {
		  tokenRequest.setClient_secret(client_secret); 
		  TokenResponseVO tokenResponse = openBankingFeign.requestToken(
				  tokenRequest.getCode(),
				  tokenRequest.getClient_id(), 
				  tokenRequest.getClient_secret(),
				  tokenRequest.getRedirect_uri(), 
				  tokenRequest.getGrant_type()
				  ); 
		  return tokenResponse; 
	  }
}
  
//  @PostMapping("/realname") public RealNameVO getRealName()

