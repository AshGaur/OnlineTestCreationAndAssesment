package com.testcreation.trainer.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

import com.testcreation.trainer.bean.Subscription;
import com.testcreation.trainer.bean.Trainer;
import com.testcreation.trainer.bean.User;
import com.testcreation.trainer.exception.StringValidators;
import com.testcreation.trainer.exception.TrainerException;
import com.testcreation.trainer.graphql.TrainerGraphQLService;
import com.testcreation.trainer.service.TrainerService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
	
	@Autowired
	TrainerService service;
	
	@Autowired
	StringValidators validator;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	Calendar calendar = Calendar.getInstance();
	
	@Autowired
	TrainerGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
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
//		if(service.getTrainersBySubscriptionId(subscriptionId).size()==0)
//			throw new TrainerException("subscription ID doesn't exist or no trainer found related to this subscription");
		return service.getTrainersBySubscriptionId(subscriptionId);
	}
	
	@GetMapping("/getsub/{subscriptionId}")
	public Subscription getSubscriptionById(@PathVariable Integer subscriptionId) {
		return service.getSubscriptionById(subscriptionId);
	}
	
	//Get a new subscription (update subscription id for trainer)
	@PutMapping("/update/{id}/subscription/{subscriptionId}")
	void trainerSubscription(@PathVariable Integer subscriptionId,@PathVariable Integer id) {
		Trainer trainer = service.getTrainerById(id).isPresent()?service.getTrainerById(id).get():null;
		if(trainer == null) {
			throw new TrainerException("Unknown Trainer Id !");
		}
		trainer.setSubscription(new Subscription(subscriptionId));
		service.updateTrainer(trainer);
	}
	
	// Add trainer to a subscription id
	@PostMapping("/add/subscription/{subscriptionId}/email/{email}")
	void addTrainer(@RequestBody Trainer theTrainer , @PathVariable Integer subscriptionId,@PathVariable String email){
		validator.validateName(theTrainer.getName());
		
		Subscription subscription = service.getSubscriptionById(subscriptionId);
		
		if(subscription==null) {
			throw new TrainerException("Unknown Subscription !");
		}
		boolean paymentSuccessful = true;
		if(subscriptionId!=1 && paymentSuccessful) {
			theTrainer.setSubscription(new Subscription(subscriptionId));
		}else {
			subscriptionId = 1;
			theTrainer.setSubscription(new Subscription(1));
		}
		calendar.setTime(new Date());
		//Subscription values setting for Trainer	
		
		if(subscription.getRole().toLowerCase().equals("student")){
			throw new TrainerException("Subscription not for trainers !");
		}
		
		//Set endService Date
		calendar.add(Calendar.DATE, subscription.getServiceUsageLimit());
		theTrainer.setEndServiceDate(formatter.format(calendar.getTime()));
		
		//Set tests to be created
		theTrainer.setTestsLeft(subscription.getTestNumberLimit());
		
		theTrainer.setUser(new User(email));
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
	
	

