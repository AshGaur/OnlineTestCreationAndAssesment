package com.testcreation.router.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.testcreation.router.bean.Subscription;
@Service
public class SubscriptionRouterService {
	
	@Autowired
	RestTemplate restTemplate;
	
	 public Iterable<Subscription> getAllSubscription(){
		String url="http://localhost:8080/subscriptions/all";
		return restTemplate.getForObject(url, null);
	}
	 public Optional<Subscription> getSubscriptionById(Integer id){
		 String url="http://localhost:8080/subscriptions/"+id.toString();
		 return restTemplate.getForObject(url, null);
	 }
}
