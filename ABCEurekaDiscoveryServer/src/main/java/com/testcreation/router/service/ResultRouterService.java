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

import com.testcreation.router.bean.Result;



@Service
public class ResultRouterService {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	public List<Object> getAllResults(){
		String url="http://localhost:8081/results/all";
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
		}
	public Object getResultById(Integer id) {
		String url = "http://localhost:8081/results/"+id.toString();
		return restTemplate.getForObject(url, Result.class);
	}
	public Object getResultByStudentId(Integer studentId) {
		String url = "http://localhost:8081/results/"+studentId.toString();
		return Arrays.asList(restTemplate.getForObject(url, Result.class));
	}
	public ResponseEntity<String> addResult(String result,Integer testId,Integer studentId) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(result, headers);
		String url = "http://localhost:8081/results/add/student/{studentId}/test/{testId}";
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
	}
	public ResponseEntity<String> deleteResultById(String result,Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("", headers);
		String url = "http://localhost:8081/results/delete"+id.toString();
		return restTemplate.exchange(url,HttpMethod.DELETE ,request, String.class);
	}
	public ResponseEntity<String> updateResultById(String result,Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(result, headers);
		String url = "http://localhost:8081/results/update/"+id.toString();
		return restTemplate.exchange(url,HttpMethod.PUT ,request, String.class);
	}
	
}
