package com.testcreation.router.service;

import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.testcreation.router.service.StudentRouterService;
@Service
public class StudentRouterService {
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public List<Object> getAllStudents() {
		String url = "http://localhost:8081/students/all";
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}


	public Object getStudentById(Integer id) {
		String url = "http://localhost:8081/students/"+id.toString();
		return restTemplate.getForObject(url, Object.class);
	}
	
	public Object getStudentBySubscriptionId(Integer subscriptionId) {
		String url = "http://localhost:8081/students/subscriptions"+subscriptionId.toString();
		return restTemplate.getForObject(url, Object.class);
	}
}


