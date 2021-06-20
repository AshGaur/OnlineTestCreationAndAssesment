package com.testcreation.zuul.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testcreation.zuul.bean.Admin;
import com.testcreation.zuul.bean.Student;
import com.testcreation.zuul.bean.User;

@Service
public class UserService {

	@Autowired
	RestTemplate restTemplate;
	
	public Optional<User> getAdminByEmail(String username){
		return Optional.ofNullable(
				restTemplate.getForObject(
						"http://ADMIN-MICROSERVICE/admins/byEmail/"+username, 
						Admin.class
				)
		);
	}
	
	public Optional<User> getStudentByEmailOrPhone(String username) {
		return Optional.ofNullable(
				restTemplate.getForObject(
						"http://STUDENT-MICROSERVICE/students/byEmailOrPhone/"+username, 
						Student.class
				)
		);
	}
	
	public Optional<User> getTrainerByEmailOrPhone(String username) {
		return Optional.ofNullable(
				restTemplate.getForObject(
						"http://TRAINER-MICROSERVICE/students/byEmailOrPhone/"+username, 
						Student.class
				)
		);
	}
	
}
