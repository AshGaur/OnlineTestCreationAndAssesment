package com.testcreation.router.service;

import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testcreation.router.bean.Student;
import com.testcreation.router.service.StudentRouterService;
@Service
public class StudentRouterService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	
	public List<Student> getAllStudents() {
		String url = "http://localhost:8081/students/all";
		return Arrays.asList(restTemplate.getForObject(url, Student[].class));
	}


	public Student getStudentById(Integer id) {
		String url = "http://localhost:8081/students/"+id.toString();
		return restTemplate.getForObject(url, Student.class);
	}
	
	public List<Student> getStudentsBySubscriptionId(Integer subscriptionId) {
		String url = "http://localhost:8081/students/subscription/"+subscriptionId.toString();
		return Arrays.asList(restTemplate.getForObject(url, Student[].class));
	}


	public ResponseEntity<String> addStudent(String student) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(student, headers);
		String url = "http://localhost:8080/students/add";
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
	}
}
	
	
	



