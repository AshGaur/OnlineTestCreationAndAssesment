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

import com.testcreation.router.bean.Attempt;
import com.testcreation.router.bean.Student;
import com.testcreation.router.bean.Test;
//import com.testcreation.students.dto.QuestionDto;
//import com.testcreation.router.controller.QuestionDto;

@Service
public class AttemptRouterService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	public List<Attempt> getAllAttempts() {
		String url = "http://localhost:8081/attempts/all";
		return Arrays.asList(restTemplate.getForObject(url, Attempt[].class));
	}
	
	
	public List<Attempt> getAttemptsByResultIdAndQuestionId(Integer resultId,Integer questionId) {
		String url = "http://localhost:8081/attempts/result/"+resultId.toString()+"/question/"+questionId.toString();
		return Arrays.asList(restTemplate.getForObject(url, Attempt[].class));
	}
	public List<Attempt> getAttemptsByResultId(Integer resultId) {
		String url = "http://localhost:8081/attempts/result/"+resultId.toString();
		return Arrays.asList(restTemplate.getForObject(url, Attempt[].class));
	}


	
	
	
}



// public QuestionDto getQuestionById