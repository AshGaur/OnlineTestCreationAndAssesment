package com.testcreation.router.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.testcreation.router.bean.Admin;

@Service
public class QuestionRouterService {
	

	@Autowired
	RestTemplate restTemplate;
	//private Object questionId;
	
	
	public List<Object> getAllQuestionsByTestId(Integer testId) {
		String url = "http://localhost:8082/questions/test/" +testId.toString();
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}


	public Object getQuestionById(Integer id) {
		String url = "http://localhost:8082/questions/"+id.toString();
		return restTemplate.getForObject(url, Object.class);
	}
	
	


}
