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
import com.testcreation.router.bean.Category;
import com.testcreation.router.bean.Test;

@Service
public class CategoryRouterService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;


	
	public ResponseEntity<String> addCategory(String category) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(category, headers);
		String url = "http://localhost:8080/categories/add";
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
	}
	
	@HystrixCommand(fallbackMethod="saveCategories")
	public List<Category> getAllCategories() {
		String url = "http://localhost:8080/categories/all";
		return Arrays.asList(restTemplate.getForObject(url, Category[].class));
	}

	public List<Category> saveCategories(){
		return Arrays.asList(new Category("Category Service will return in sometime"));
	}

	public ResponseEntity<String> updateCategory(String theCategory,String categoryName) {             
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(theCategory, headers);
		String url = "http://localhost:8080/categories/update/"+categoryName;
		return restTemplate.exchange(url,HttpMethod.PUT ,request, String.class);
	}

	public ResponseEntity<String> deleteByCategoryName(String categoryName) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("", headers);
		String url = "http://localhost:8080/categories/delete/"+categoryName;
		return restTemplate.exchange(url,HttpMethod.DELETE ,request, String.class);
	}
	 

	
}
