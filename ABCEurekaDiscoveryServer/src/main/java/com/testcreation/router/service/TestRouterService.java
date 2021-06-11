package com.testcreation.router.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TestRouterService {
	@Autowired
	RestTemplate restTemplate;
	
	
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
}
