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
public class SubscriptionRouterService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders headers;
	
	 public List<Object> getAllSubscription(){
		String url="http://localhost:8080/subscriptions/all";
		return Arrays.asList(url, Object[].class);
	}
	 public Object getSubscriptionById(Integer id){
		 String url="http://localhost:8080/subscriptions/"+id.toString();
		 return restTemplate.getForObject(url, Object.class);
	 }
	 public ResponseEntity<String> addSubscription(String Subscription) {
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(Subscription, headers);
			String url = "http://localhost:8080/admins/add";
			return restTemplate.exchange(url,HttpMethod.POST ,request, String.class);
		}
}
