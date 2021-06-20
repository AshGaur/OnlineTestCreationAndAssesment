package com.testcreation.zuul.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.zuul.bean.Admin;
import com.testcreation.zuul.bean.Student;
import com.testcreation.zuul.bean.Trainer;
import com.testcreation.zuul.bean.User;
import com.testcreation.zuul.service.UserService;

@RestController
public class CheckController {

	@Autowired
	UserService service;
	
	@GetMapping("/admin/{email}")
	public Optional<User> getAdminbyEmail(@PathVariable String email){
		return service.getAdminByEmail(email);
	}
	
	@GetMapping("/student/{username}")
	public Optional<User> getStudentByUsername(@PathVariable String username){
		return service.getStudentByEmailOrPhone(username);
	}
	
	@GetMapping("/trainer/{username}")
	public Optional<User> getTrainerByUsername(@PathVariable String username){

		return service.getTrainerByEmailOrPhone(username);
	}
	
}
