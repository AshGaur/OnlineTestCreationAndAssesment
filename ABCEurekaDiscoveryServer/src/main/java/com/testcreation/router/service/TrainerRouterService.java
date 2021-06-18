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
import com.testcreation.router.bean.Attempt;
import com.testcreation.router.bean.Trainer;

@Service
public class TrainerRouterService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	@HystrixCommand(fallbackMethod="saveTrainers")
	public List<Trainer> getAllTrainers() {
		String url = "http://localhost:8082/trainers/all";
		return Arrays.asList(restTemplate.getForObject(url, Trainer[].class));
	}
	
	public List<Trainer> saveTrainers(){
		return Arrays.asList(new Trainer(-1, "Trainer Service will return in sometime"));
	}

	public Trainer getTrainerById(Integer id) {
		String url = "http://localhost:8082/trainers/"+id.toString();
		return restTemplate.getForObject(url, Trainer.class);
	}
	
	public List<Trainer> getTrainersBySubscriptionId(Integer subscriptionId) {
		String url = "http://localhost:8082/trainers/subscription/"+subscriptionId.toString();
		return Arrays.asList(restTemplate.getForObject(url, Trainer[].class));
	}

	public ResponseEntity<String> addTrainer(String Trainer,Integer subscriptionId) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(Trainer, headers);
		String url = "http://localhost:8082/trainers/add/subscription/"+subscriptionId;
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
	}
	public ResponseEntity<String> deleteTrainer(Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("", headers);
		String url = "http://localhost:8082/trainers/delete/"+id.toString();
		return restTemplate.exchange(url,HttpMethod.DELETE ,request, String.class);
	}
	
	 public ResponseEntity<String> updateTrainer(String theTrainer,Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(theTrainer, headers);
		String url = "http://localhost:8082/trainers/update/"+id.toString();
		return restTemplate.exchange(url,HttpMethod.PUT ,request, String.class);
	}
	 
	 public ResponseEntity<String> updateTrainer(Integer subscriptionId,Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>("", headers);
		String url = "http://localhost:8082/trainers/"+id.toString()+"/subscription/"+subscriptionId.toString();
		return restTemplate.exchange(url,HttpMethod.PUT ,request, String.class);
	}
}
	
