package com.testcreation.admin.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.admin.bean.Subscription;
import com.testcreation.admin.exception.SubscriptionException;
import com.testcreation.admin.graphql.CategoryGraphQLService;
import com.testcreation.admin.graphql.SubscriptionGraphQLService;
import com.testcreation.admin.service.SubscriptionService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {


	@Autowired
	SubscriptionService service;
	
	@Autowired
	SubscriptionGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	Iterable<Subscription> getAllSubscriptions() {
		return service.getAllSubscriptions();
	}
	
	@GetMapping("/{id}")
	Optional<Subscription> getSubscriptionById(@PathVariable Integer id) {
		if(service.getSubscriptionById(id).isEmpty())
			throw new SubscriptionException("Subscription ID doesn't exist !");
		return service.getSubscriptionById(id);
	}
	
	@PostMapping("/add")
	void addSubscription(@RequestBody Subscription theSubscription){
		service.addSubscription(theSubscription);
	}
	
	@PutMapping("/update/{id}")
	void updateSubscription(@RequestBody Subscription theSubscription, @PathVariable Integer id) {
		if(service.getSubscriptionById(id).isEmpty())
			throw new SubscriptionException("Subscription ID doesn't exist !");
		service.updateSubscription(theSubscription);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteSubscription(@PathVariable Integer id) {
		if(service.getSubscriptionById(id).isEmpty())
			throw new SubscriptionException("Subscription ID doesn't exist !");
		service.deleteSubscription(id);
	}
}
