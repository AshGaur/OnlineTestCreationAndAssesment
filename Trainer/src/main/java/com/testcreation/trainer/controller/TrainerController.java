package com.testcreation.trainer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.trainer.bean.Subscription;
import com.testcreation.trainer.bean.Trainer;
import com.testcreation.trainer.exception.StringValidators;
import com.testcreation.trainer.exception.TrainerException;
import com.testcreation.trainer.service.TrainerService;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
	
	@Autowired
	TrainerService service;
	
	@Autowired
	StringValidators validator;
	
	// Get all trainers
	@GetMapping("/all")
	Iterable<Trainer> getAllTrainers() {
		return service.getAllTrainers();
	}
	
	// Get trainer by trainer id
	@GetMapping("/{id}")
	Optional<Trainer> getTrainerById(@PathVariable Integer id) {
		if(service.getTrainerById(id).isEmpty())
			throw new TrainerException("Trainer ID doesn't exist !");
		return service.getTrainerById(id);
	}
	
	// Get trainers by subscription id
	@GetMapping("/subscription/{subscriptionId}")
	List<Trainer> getTrainerBySubscriptionId(@PathVariable Integer subscriptionId) {
		if(service.getTrainerBySubscriptionId(subscriptionId).isEmpty())
			throw new TrainerException("subscription ID doesn't exist or no trainer found related to this subscription");
		return service.getTrainerBySubscriptionId(subscriptionId);
	}
	
	//Get a new subscription (update subscription id for trainer)
	@PutMapping("/{id}/subscription/{subscriptionId}")
	void trainerSubscription(@PathVariable Integer subscriptionId,@PathVariable Integer id) {
		Trainer trainer = service.getTrainerById(id).isPresent()?service.getTrainerById(id).get():null;
		if(trainer == null) {
			throw new TrainerException("Unknown Trainer Id !");
		}
		trainer.setSubscription(new Subscription(subscriptionId));
		service.updateTrainer(trainer);
	}
	
	// Add trainer to a subscription id
	@PostMapping("/add/subscription/{subscriptionId}")
	void addTrainer(@RequestBody Trainer theTrainer , @PathVariable Integer subscriptionId){//, @PathVariable int subscriptionId){
		boolean isRequired = true;
		if(theTrainer.getEmail()!=null) {
			validator.validateEmail(theTrainer.getEmail());
			isRequired = false;
		}
		if(theTrainer.getPhone()!=null) {
			validator.validatePhone(theTrainer.getPhone());
			isRequired = false;
		}
		if(isRequired) {
			throw new TrainerException("Atleast one among phone or email is required !");
		}
		validator.validateName(theTrainer.getName());
		validator.validatePassword(theTrainer.getPassword());
		boolean paymentSuccessful = true;
		if(subscriptionId!=1 && paymentSuccessful) {
			theTrainer.setSubscription(new Subscription(subscriptionId));
		}else {
			theTrainer.setSubscription(new Subscription(1));
		}
		service.addTrainer(theTrainer);
	}
	
	@PutMapping("/update/{id}")
	void updateTrainer(@RequestBody Trainer theTrainer, @PathVariable Integer id) {
		if(service.getTrainerById(id).isEmpty())
			throw new TrainerException("Trainer ID doesn't exist !");
		service.updateTrainer(theTrainer);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteTrainer(@PathVariable Integer id) {
		if(service.getTrainerById(id).isEmpty())
			throw new TrainerException("Trainer ID doesn't exist !");
		service.deleteTrainer(id);
	}
}
	
	

