package com.testcreation.zuul.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testcreation.zuul.bean.Admin;
import com.testcreation.zuul.bean.Student;
import com.testcreation.zuul.bean.Trainer;
import com.testcreation.zuul.bean.User;

@Service
public class UserService {

	@Autowired
	RestTemplate restTemplate;
	
	public Optional<User> getAdminByEmail(String username){
		Optional<User> user = Optional.ofNullable(
				restTemplate.getForObject(
						"http://ADMIN-MICROSERVICE/admins/byEmail/"+username, 
						Admin.class
				)
		);
		System.out.println("####################################");
		System.out.println("ADMIN :"+user);
		System.out.println("####################################");
		return user;
	}
	
	public Optional<User> getStudentByEmailOrPhone(String username) {
		Optional<User> user = Optional.ofNullable(
				restTemplate.getForObject(
						"http://STUDENT-MICROSERVICE/students/byEmailOrPhone/"+username, 
						Student.class
				)
		);
		System.out.println("####################################");
		System.out.println("STUDENT :"+user);
		System.out.println("####################################");
		return user;
	}
	
	public Optional<User> getTrainerByEmailOrPhone(String username) {
		Optional<User> user = Optional.ofNullable(restTemplate.getForObject(
				"http://TRAINER-MICROSERVICE/trainers/byEmailOrPhone/"+username, 
				Trainer.class)
		);
		System.out.println("####################################");
		System.out.println("TRAINER :"+user);
		System.out.println("####################################");
		return user;
	}
}
