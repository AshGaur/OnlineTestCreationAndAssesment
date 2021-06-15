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

import com.testcreation.router.bean.Test;

@Service
public class TestRouterService {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	public List<Test> getAllTests() {
		String url = "http://localhost:8082/tests/all";
		return Arrays.asList(restTemplate.getForObject(url, Test[].class));
	}

	public Test getTestById(Integer id) {
		String url = "http://localhost:8082/tests/"+id.toString();
		return restTemplate.getForObject(url, Test.class);
	}
	
	public List<Test> getTestsByTrainerId(Integer trainerId) {
		String url = "http://localhost:8082/tests/trainer/"+trainerId.toString();
		return Arrays.asList(restTemplate.getForObject(url, Test[].class));
	}
	
	public List<Test> getTestsByCategoryName(String categoryName){
		String url = "http://localhost:8082/tests/category/"+categoryName;
		return  Arrays.asList(restTemplate.getForObject(url, Test[].class));
	}
	
	public ResponseEntity<String> addTest(String tempTest, Integer trainerId, String categoryName) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(tempTest, headers);
		String url = "http://localhost:8082/tests/add/trainer/"+trainerId.toString()+"/category/"+categoryName;
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
	}

}
