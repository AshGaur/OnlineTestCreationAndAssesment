package com.testcreation.zuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.zuul.bean.AuthRequest;
import com.testcreation.zuul.bean.AuthResponse;
import com.testcreation.zuul.bean.User;
import com.testcreation.zuul.bean.UserDto;
import com.testcreation.zuul.exception.StringValidators;
import com.testcreation.zuul.exception.ValidationException;
import com.testcreation.zuul.security.MyUserDetailsService;
import com.testcreation.zuul.security.jwt.JwtUtil;
import com.testcreation.zuul.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
	StringValidators validator;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyUserDetailsService userService;
	
	@Autowired
	JwtUtil jwtTokenUtil;
	
	@GetMapping("/all")
	public Iterable<User> getAllUsers() {
		return service.getAllAdmins();
	}
	
	@PostMapping("/add")
	public void addUser(@RequestBody UserDto userDto) {
		String role = null;
		switch(userDto.getRole().toLowerCase()) {
		case "admin":role = "admin"; break;
		case "trainer":{
			role = "trainer";
			if(userDto.getSubscriptionId()==null) {
				throw new ValidationException("SubscriptionID required !");
			}
			break;
		}
		case "student":{
			role="student";
			if(userDto.getSubscriptionId()==null) {
				throw new ValidationException("SubscriptionID required !");
			}
			break;
		}
		default: throw new ValidationException("Unknown Role !");
		}
		userDto.setRole("ROLE_"+role.toUpperCase());
		if(userDto.getPhone()!=null) {
			validator.validatePhone(userDto.getPhone());
		}
		validator.validateEmail(userDto.getEmail());
		validator.validatePassword(userDto.getPassword());
		service.addUser(userDto);
		//adds to student entity also in service after this
	}
	
	@PostMapping("/login")
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
	
}
