package com.testcreation.router.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.router.bean.Trainer;
import com.testcreation.router.graphql.TrainerGraphQLService;
import com.testcreation.router.service.TrainerRouterService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/trainers")
public class TrainerRouterController {
	
	@Autowired
	TrainerRouterService service;
	
	@Autowired
	TrainerGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	// Get all trainers 
	@GetMapping("/all")
	List<Trainer> getAllTrainers() {
		return service.getAllTrainers();
	}
	
	// Get trainer by trainer id
	@GetMapping("/{id}")
	Object getTrainerById(@PathVariable Integer id) {
		return service.getTrainerById(id);
	}
	
	// Get trainers by subscription id
	@GetMapping("/subscription/{subscriptionId}")
	List<Trainer> getTrainersBySubscriptionId(@PathVariable Integer subscriptionId) {
		return service.getTrainersBySubscriptionId(subscriptionId);
	}
//	
//	//Get a new subscription
//	@PutMapping("/{id}/subscription/{subscriptionId}")
//	void trainerSubscription(@PathVariable Integer subscriptionId,@PathVariable Integer id) {
//		Trainer trainer = !service.getTrainerById(id).isEmpty()?service.getTrainerById(id).get():null;
//		trainer.setSubscription(new Subscription(subscriptionId));
//		service.updateTrainer(trainer);
//	}
//	
//	// Add trainer to a subscription id
	@PostMapping("/add")
	public ResponseEntity<String> addTrainer(@RequestBody String theTrainer , @PathVariable int subscriptionId){
		return service.addTrainer(theTrainer);
	}
//	
//	@PutMapping("/update/{id}")
//	void updateTrainer(@RequestBody Trainer theTrainer, @PathVariable Integer id) {
//		if(service.getTrainerById(id).isEmpty())
//			throw new TrainerException("Trainer ID doesn't exist !");
//		service.updateTrainer(theTrainer);
//	}
//	
//	@DeleteMapping("/delete/{id}")
//	void deleteTrainer(@PathVariable Integer id) {
//		if(service.getTrainerById(id).isEmpty())
//			throw new TrainerException("Trainer ID doesn't exist !");
//		service.deleteTrainer(id);
//	}
}
	
	

