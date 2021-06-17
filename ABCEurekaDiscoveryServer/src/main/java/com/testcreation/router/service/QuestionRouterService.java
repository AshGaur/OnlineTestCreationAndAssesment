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

import com.testcreation.router.bean.Question;


@Service
public class QuestionRouterService {
	

	@Autowired
	RestTemplate restTemplate;
	//private Object questionId;
	
	@Autowired
	HttpHeaders headers;
	
	public List<Question> getAllQuestions(){
		String url = "http://localhost:8082/questions/all";
		return Arrays.asList(restTemplate.getForObject(url, Question[].class));
	}
	
	public List<Question> getQuestionsByTestId(Integer testId) {
		String url = "http://localhost:8082/questions/test/"+testId.toString();
		return Arrays.asList(restTemplate.getForObject(url, Question[].class));
	}

	public Question getQuestionById(Integer id) {
		String url = "http://localhost:8082/questions/"+id.toString();
		return restTemplate.getForObject(url, Question.class);
	}
	
	public ResponseEntity<String> addNewQuestion(@RequestBody Question tempQuestion,  Integer testId) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("", headers);
		String url = "http://localhost:8082/questions/add/test/"+testId.toString();
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class); }
	
	public ResponseEntity<String> updateQuestion(@RequestBody Question tempQuestion, @PathVariable Integer questionId) {     /////////doubt   temp questio
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("", headers);
		String url = "http://localhost:8082/questions/update/"+questionId.toString();
		return restTemplate.exchange(url,HttpMethod.PUT ,request, String.class);
	}
	
	public ResponseEntity<String> deleteQuestionById(@PathVariable Integer questionId) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("", headers);
		String url = "http://localhost:8082/questions/delete/"+questionId.toString();
		return restTemplate.exchange(url,HttpMethod.DELETE ,request, String.class);
	}
	
	public ResponseEntity<String>deleteAllQuestionsFromTestId(@PathVariable Integer testId) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("", headers);
		String url = "http://localhost:8082/questions/delete/test/"+testId.toString();
		return restTemplate.exchange(url,HttpMethod.DELETE ,request, String.class);
	}
	
}
	

