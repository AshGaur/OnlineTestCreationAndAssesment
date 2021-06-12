package com.testcreation.router.service;


import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class SubscriptionRouterService {
	
	@Autowired
	RestTemplate restTemplate;
	
	 public List<Object> getAllSubscription(){
		String url="http://localhost:8080/subscriptions/all";
		return Arrays.asList(url, Object[].class);
	}
	 public Object getSubscriptionById(Integer id){
		 String url="http://localhost:8080/subscriptions/"+id.toString();
		 return restTemplate.getForObject(url, Object.class);
	 }
}
