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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.testcreation.router.bean.Admin;
import com.testcreation.router.bean.Test;


@Service
public class QuestionRouterService {
	

	@Autowired
	RestTemplate restTemplate;
	//private Object questionId;
	
	@Autowired
	HttpHeaders headers;
	
	
	
	public List<Object> getAllQuestionsByTestId(Integer testId) {
		String url = "http://localhost:8082/questions/test/" +testId.toString();
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}


	public Object getQuestionById(Integer id) {
		String url = "http://localhost:8082/questions/"+id.toString();
		return restTemplate.getForObject(url, Object.class);
	}
	
	public ResponseEntity<String> addNewQuestion( String Question,  Integer testId) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(Question, headers);
		String url = "http://localhost:8082/questions/add/test/"+testId.toString();
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
	}
	
	


}
