package com.biggBrains.Spring.BootAuthentication.jwt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biggBrains.Spring.BootAuthentication.jwt.Util.JwtUtil;
import com.biggBrains.Spring.BootAuthentication.jwt.model.JwtModel;
import com.biggBrains.Spring.BootAuthentication.jwt.model.JwtResponse;
import com.biggBrains.Spring.BootAuthentication.service.CustomUserDetailService;

@RestController
public class JwtController {
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@RequestMapping(value = "/token" , method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtModel jwtModel) throws Exception{
		
		System.out.println(jwtModel);
		
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtModel.getUsername(), jwtModel.getPassword()));
			
		}catch(UsernameNotFoundException e){
			
			e.printStackTrace();
			throw new Exception("Bed Credentials");
			
			
		}
		
		UserDetails userDetails=this.customUserDetailService.loadUserByUsername(jwtModel.getUsername());
		
		String token =this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT" +token);
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}

}
