package com.testcreation.router.service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;



@Service
public class TestRouterService {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	public List<Object> getAllTests() {
		String url = "http://localhost:8082/tests/all";
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}

	public Object getTestById(Integer id) {
		String url = "http://localhost:8082/tests/"+id.toString();
		return restTemplate.getForObject(url, Object.class);
	}
	
	public List<Object> getTestsByTrainerId(Integer trainerId) {
		String url = "http://localhost:8082/tests/trainers/"+trainerId.toString();
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}
	
	public List<Object> getTestsByCategoryName(String categoryName){
		String url = "http://localhost:8082/tests/category/"+categoryName;
		return  Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}
	
	public ResponseEntity<String> addTest(String tempTest, Integer trainerId, String categoryName) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(tempTest, headers);
		String url = "http://localhost:8082/tests/add/trainer/"+trainerId.toString()+"/category/"+categoryName;
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
	}

}
