package com.testcreation.zuul.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testcreation.zuul.bean.Admin;
import com.testcreation.zuul.bean.Student;
import com.testcreation.zuul.bean.Trainer;
import com.testcreation.zuul.bean.User;
import com.testcreation.zuul.bean.UserDto;
import com.testcreation.zuul.dao.UserRepository;
import com.testcreation.zuul.exception.ValidationException;

@Service
public class UserService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	@Autowired
	UserRepository repo;
	
	public Iterable<User> getAllAdmins() {
		return repo.findAll();
	}
	
	public void addUser(UserDto user) {
		
		repo.save(new User(
				user.getEmail(),
				user.getPhone(),
				user.getPassword(),
				user.getRole(),
				true
		));
		
		String url = "";
		HttpEntity<Object> request = null;
		headers.setContentType(MediaType.APPLICATION_JSON);
		switch(user.getRole()) {
			case "ROLE_ADMIN": {
				url = "http://ADMIN-MICROSERVICE/admins/add/email/"+user.getEmail();
				request = new HttpEntity<>(new Admin(user.getName()),headers);
				break;
			}
			case "ROLE_TRAINER": {
				url = "http://TRAINER-MICROSERVICE/trainers/add/subscription/"+user.getSubscriptionId()+"/email/"+user.getEmail();
				request = new HttpEntity<>(new Trainer(user.getName()),headers);
				break;
			}
			case "ROLE_STUDENT":{
				url = "http://STUDENT-MICROSERVICE/students/add/subscription/"+user.getSubscriptionId()+"/email/"+user.getEmail();
				request = new HttpEntity<>(new Student(user.getName()),headers);
			}
		}
		
		ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
		
		if(response.getStatusCode()!=HttpStatus.OK) {
			repo.deleteByEmail(user.getEmail());
			throw new ValidationException(response.getBody());
		}
	}
	
	
	public Optional<User> getUserByEmail(String email) {
		return repo.findByEmail(email);
	}
	
//	public Optional<User> getAdminByEmail(String username,String role){
//		Optional<User> user = Optional.ofNullable(
//				restTemplate.getForObject(
//						"http://ADMIN-MICROSERVICE/admins/byEmail/"+username+"/role/"+role, 
//						Admin.class
//				)
//		);
//		return user;
//	}
//	
//	public Optional<User> getStudentByEmailOrPhone(String username,String role) {
//		Optional<User> user = Optional.ofNullable(
//				restTemplate.getForObject(
//						"http://STUDENT-MICROSERVICE/students/byEmailOrPhone/"+username+"/role/"+role, 
//						Student.class
//				)
//		);
//		return user;
//	}
//	
//	public Optional<User> getTrainerByEmailOrPhone(String username,String role) {
//		Optional<User> user = Optional.ofNullable(restTemplate.getForObject(
//				"http://TRAINER-MICROSERVICE/trainers/byEmailOrPhone/"+username+"/role/"+role, 
//				Trainer.class)
//		);
//		return user;
//	}
}
