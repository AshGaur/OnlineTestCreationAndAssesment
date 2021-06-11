package com.testcreation.router.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrainerRouterService {
	@Autowired
	RestTemplate restTemplate;
	
	
	public List<Object> getAllTrainers() {
		String url = "http://localhost:8082/trainers/all";
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}


	public Object getTrainerById(Integer id) {
		String url = "http://localhost:8082/trainers/"+id.toString();
		return restTemplate.getForObject(url, Object.class);
	}
	
	public List<Object> getTrainerBySubscriptionId(Integer subscriptionId) {
		String url = "http://localhost:8082/trainers/subscriptions"+subscriptionId.toString();
		return Arrays.asList(restTemplate.getForObject(url, Object[].class));
	}
}
