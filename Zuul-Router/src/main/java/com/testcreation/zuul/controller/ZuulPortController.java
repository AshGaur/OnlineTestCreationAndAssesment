package com.testcreation.zuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.zuul.bean.AuthRequest;
import com.testcreation.zuul.bean.AuthResponse;
import com.testcreation.zuul.security.MyUserDetails;
import com.testcreation.zuul.security.MyUserDetailsService;
import com.testcreation.zuul.security.jwt.JwtUtil;
import com.testcreation.zuul.service.UserService;

@RestController
@RequestMapping("/login")
public class ZuulPortController {

	@Autowired
	UserService service;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyUserDetailsService userService;
	
	@Autowired
	JwtUtil jwtTokenUtil;
	
	@PostMapping
	public ResponseEntity<?> getAdminAuthToken(@RequestBody AuthRequest authRequest){
		
		//throws BadCredentialsException handled in ZuulExceptionController
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
		);
		
		final UserDetails userDetails = userService
				.loadUserByUsername(authRequest.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthResponse(jwt));
	}
	
//	@PostMapping("/trainer/login")
//	public ResponseEntity<?> getTrainerAuthToken(@RequestBody AuthRequest authRequest){
//		//throws BadCredentialsException handled in ZuulExceptionController
//		authenticationManager.authenticate(
//			new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//		);
//		
//		final MyUserDetails userDetails = (MyUserDetails) userService
//				.loadUserByUsernameAndRole(authRequest.getUsername(),"trainer");
//		
//		final String jwt = jwtTokenUtil.generateToken(userDetails);
//		
//		return ResponseEntity.ok(new AuthResponse(jwt));
//	}
//	
//	@PostMapping("/student/login")
//	public ResponseEntity<?> getStudentAuthToken(@RequestBody AuthRequest authRequest){
//		System.out.println("STUDENT LOGIN REACHED !");
//		
//		//throws BadCredentialsException handled in ZuulExceptionController
//		authenticationManager.authenticate(
//			new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//		);
//		
//		final MyUserDetails userDetails = (MyUserDetails) userService
//				.loadUserByUsernameAndRole(authRequest.getUsername(),"student");
//		
//		final String jwt = jwtTokenUtil.generateToken(userDetails);
//		
//		return ResponseEntity.ok(new AuthResponse(jwt));
//	}
	
}
