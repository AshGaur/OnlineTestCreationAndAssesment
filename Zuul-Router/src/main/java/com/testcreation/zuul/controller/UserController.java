package com.testcreation.zuul.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.zuul.bean.Admin;
import com.testcreation.zuul.bean.AuthRequest;
import com.testcreation.zuul.bean.AuthResponse;
import com.testcreation.zuul.bean.Student;
import com.testcreation.zuul.bean.Trainer;
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
		
		//to send some user details in response
		User user = service.getUserByEmail(userDetails.getUsername()).get();
		
		Integer userId = null;
		String name = null;
		String role = null;
		
		switch(user.getRoles()) {
			case "ROLE_ADMIN": {
				Admin admin = service.getAdminByEmail(userDetails.getUsername()).get();
				userId = admin.getId();
				name = admin.getName();
				role = "admin";
				break;
			}
			case "ROLE_TRAINER": {
				Trainer trainer = service.getTrainerByEmail(userDetails.getUsername()).get();
				userId = trainer.getId();
				name = trainer.getName();
				role = "trainer";
				break;
			}
			case "ROLE_STUDENT": {
				Student student = service.getStudentByEmail(userDetails.getUsername()).get();
				userId = student.getId();
				name = student.getName();
				role = "student";
				break;
			}
		}
		
		return ResponseEntity.ok(new AuthResponse(userId,jwt,name,role,authRequest.getUsername()));
	}
	
	@GetMapping("/admin/email/{email}")
	public Optional<Admin> getAdminByEmail(@PathVariable String email){
		return service.getAdminByEmail(email);
	}
	
	@GetMapping("/trainer/email/{email}")
	public Optional<Trainer> getTrainerByEmail(@PathVariable String email){
		return service.getTrainerByEmail(email);
	}
	
	@GetMapping("/student/email/{email}")
	public Optional<Student> getStudentByEmail(@PathVariable String email){
		return service.getStudentByEmail(email);
	}
	
}
