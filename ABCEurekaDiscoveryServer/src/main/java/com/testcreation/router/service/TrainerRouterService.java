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

@Service
public class TrainerRouterService {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	
	public List<Object> getAllTrainers() {
		String url = "http://localhost:8082/trainers/all";
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}


	public Object getTrainerById(Integer id) {
		String url = "http://localhost:8082/trainers/"+id.toString();
		return restTemplate.getForObject(url, Object.class);
	}
	
	public List<Object> getTrainerBySubscriptionId(Integer subscriptionId) {
		String url = "http://localhost:8082/trainers/subscription/"+subscriptionId.toString();
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}


	public ResponseEntity<String> addTrainer(String Trainer) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(Trainer, headers);
		String url = "http://localhost:8082/trainers/add";
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
	}
}
	
