package com.testcreation.router.controller;

import java.util.List;
import java.util.Optional;

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

import com.testcreation.router.bean.Trainer;
import com.testcreation.router.service.TrainerRouterService;

@RestController
@RequestMapping("/trainers")
public class TrainerRouterController {
	
	@Autowired
	TrainerRouterService service;
	
//	@Autowired
//	StringValidators validator;
	
	// Get all trainers 
	@GetMapping("/all")
	List<Object> getAllTrainers() {
		return service.getAllTrainers();
	}
	
	// Get trainer by trainer id
	@GetMapping("/{id}")
	Object getTrainerById(@PathVariable Integer id) {
		return service.getTrainerById(id);
	}
	
	// Get trainers by subscription id
	@GetMapping("/subscription/{subscriptionId}")
	List<Object> getTrainerBySubscriptionId(@PathVariable Integer subscriptionId) {
		return service.getTrainerBySubscriptionId(subscriptionId);
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
	
	

