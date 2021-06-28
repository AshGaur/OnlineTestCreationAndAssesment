package com.testcreation.students.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcreation.students.bean.Student;
import com.testcreation.students.bean.Subscription;
import com.testcreation.students.bean.Test;
import com.testcreation.students.dao.StudentRepository;
import com.testcreation.students.dto.QuestionDto;
import com.testcreation.students.dto.TestDto;
import com.testcreation.students.exception.StudentException;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public Iterable<Student> getAllStudents() {
		return repo.findAll();
	}
	
	public Optional<Student> getStudentById(Integer id) {
		return repo.findById(id);
	}
	
	public Student getStudentByEmail(String email){
		return repo.findByUserEmail(email);
	}
	
	public List<Student> getStudentsBySubscriptionId(Integer subscriptionId) {
		return repo.findBySubscriptionId(subscriptionId);
	}

	public void addStudent(Student theStudent) {
		repo.save(theStudent);
	}

	public void updateStudent(Student theStudent) {
		repo.save(theStudent);	
	}

	public void deleteStudent(Integer id) {
		repo.deleteById(id);	
	}

	public Subscription getSubscriptionById(Integer subscriptionId) {
		String url = "http://ADMIN-MICROSERVICE/subscriptions/"+subscriptionId;
		return restTemplate.getForObject(url, Subscription.class);
	}
	
	public TestDto getTestById(Integer testId) {
		String url = "http://TRAINER-MICROSERVICE/tests/"+testId;
		
		return restTemplate.getForObject(url, TestDto.class);
		
//		if(respString.equals("Unknown Test ID !")) {
//			return null;
//		}
//		try {
//			return objectMapper.readValue(respString, TestDto.class);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			throw new StudentException("Test Serialization Exception !");
//		}
	}

	
}
