package com.testcreation.router.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.router.bean.Subscription;
import com.testcreation.router.service.SubscriptionRouterService;


@RestController
@RequestMapping("/subscriptions")
public class SubscriptionRouterController {

	@Autowired
	SubscriptionRouterService service;
	
	@GetMapping("/all")
	public List<Subscription> getAllSubscription() {
		return service.getAllSubcription();
	}
	
	@GetMapping("/{id}")
	public Object getSubscriptionById(@PathVariable Integer id) {
		return service.getSubscriptionById(id);
	}

	@PostMapping("/add")
	void addSubscription(@RequestBody String theSubscription){
		service.addSubscription(theSubscription);
	}

	@PutMapping("/update/{id}")
	public  ResponseEntity<String> updateSubscription(@RequestBody String theSubscription,@PathVariable int id) {
		return service.updateSubscription(theSubscription,id);
	}

	@DeleteMapping("/delete/{id}")
	void deleteSubscription(@PathVariable Integer id) {
		service.deleteSubscription(id);
	}
}
