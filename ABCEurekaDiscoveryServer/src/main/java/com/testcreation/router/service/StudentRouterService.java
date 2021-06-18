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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.testcreation.router.bean.Attempt;
import com.testcreation.router.bean.Student;
import com.testcreation.router.service.StudentRouterService;
@Service
public class StudentRouterService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	@HystrixCommand(fallbackMethod="saveStudents")
	public List<Student> getAllStudents() {
		String url = "http://localhost:8081/students/all";
		return Arrays.asList(restTemplate.getForObject(url, Student[].class));
	}
	
	public List<Student> saveStudents(){
		return Arrays.asList(new Student(-1,null, null, "Student Service will return in sometime", null, null, null, null));
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
		String url = "http://localhost:8081/students/add";
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
	}
	
	public ResponseEntity<String> updateStudentBySubscriptionId(Integer subscriptionId,Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("", headers);
		String url = "http://localhost:8081/students/"+id.toString()+"/subscription/"+subscriptionId.toString();
		return restTemplate.exchange(url,HttpMethod.PUT ,request, String.class);
	}
	
	public ResponseEntity<String> updateStudentById(String theStudent,Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(theStudent, headers);
		String url = "http://localhost:8081/students/update/"+id.toString();
		return restTemplate.exchange(url,HttpMethod.PUT ,request, String.class);
	}

	public ResponseEntity<String> deleteStudent(Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("", headers);
		String url = "http://localhost:8081/students/delete/"+id.toString();
		return restTemplate.exchange(url,HttpMethod.DELETE ,request, String.class);
	}
}




	
	
	




	



