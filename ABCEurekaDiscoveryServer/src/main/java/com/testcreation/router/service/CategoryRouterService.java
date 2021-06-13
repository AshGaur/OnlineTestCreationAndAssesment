package com.testcreation.router.service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testcreation.router.bean.Category;

@Service
public class CategoryRouterService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	
	public List<Object> getAllCategory(){
		String url="http://localhost:8080/Category/all";
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}
//     public Object getCategoryById(Integer id) {
//		String url="http://localhost:8080/Category/"+id.toString();
//		return restTemplate.getForObject(url,Object.class);
//	}
	public ResponseEntity<String> addCategory(String category) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(category, headers);
		String url = "http://localhost:8080/categories/add";
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
	}
	
	
}
