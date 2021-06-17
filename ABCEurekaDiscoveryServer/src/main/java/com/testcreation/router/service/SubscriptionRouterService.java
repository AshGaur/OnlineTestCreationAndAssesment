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
import com.testcreation.router.bean.Subscription;


@Service
public class SubscriptionRouterService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	 public Object getSubscriptionById(Integer id){
		 String url="http://localhost:8080/subscriptions/"+id.toString();
		 return restTemplate.getForObject(url, Object.class);
	 }
	 
	 public ResponseEntity<String> addSubscription(String Subscription) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(Subscription, headers);
		String url = "http://localhost:8080/subscriptions/add";
		return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
	}
	 
	 @HystrixCommand(fallbackMethod="saveSubscription")
	 public List<Subscription> getAllSubcription() {
		String url = "http://localhost:8080/subscriptions/all";
		return Arrays.asList(restTemplate.getForObject(url, Subscription[].class));
	 }
	 
	 public List<Subscription> saveSubscription(){
		 return Arrays.asList(new Subscription(-1,"Subscription Service will return in sometime","Service Unavailable"));
	 }
	 
	 public ResponseEntity<String> deleteSubscription(Integer id) {
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>("", headers);
			String url = "http://localhost:8080/subscriptions/delete/"+id.toString();
			return restTemplate.exchange(url,HttpMethod.DELETE ,request, String.class);
		}
	 
	 //update method for subscription 
	 public ResponseEntity<String> updateSubscription(String theSubscription,Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(theSubscription, headers);
		String url = "http://localhost:8080/subscriptions/update/"+id.toString();
		return restTemplate.exchange(url,HttpMethod.PUT ,request, String.class);
	 }
}
