package com.testcreation.zuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.zuul.bean.User;
import com.testcreation.zuul.bean.UserDto;
import com.testcreation.zuul.exception.StringValidators;
import com.testcreation.zuul.exception.ValidationException;
import com.testcreation.zuul.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
	StringValidators validator;
	
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
	
}
