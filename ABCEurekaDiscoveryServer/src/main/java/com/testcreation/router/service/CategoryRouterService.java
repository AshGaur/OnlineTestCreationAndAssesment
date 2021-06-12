package com.testcreation.router.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testcreation.router.bean.Category;

@Service
public class CategoryRouterService {

	@Autowired
	RestTemplate restTemplate;
	
	
	public List<Object> getAllCategory(){
		String url="http://localhost:8080/Category/all";
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}
     public Object getCategoryById(Integer id) {
		String url="http://localhost:8080/Category/"+id.toString();
		return restTemplate.getForObject(url,Object.class);
	}
}
