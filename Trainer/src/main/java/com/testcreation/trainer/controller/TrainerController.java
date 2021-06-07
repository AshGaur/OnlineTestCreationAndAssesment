package com.testcreation.trainer.controller;

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
	
	@GetMapping("/all")
	Iterable<Trainer> getAllTrainers() {
		return service.getAllTrainers();
	}
	
	@GetMapping("/{id}")
	Optional<Trainer> getTrainerById(@PathVariable Integer id) {
		if(service.getTrainerById(id).isEmpty())
			throw new TrainerException("Trainer ID doesn't exist !");
		return service.getTrainerById(id);
	}
	
	@PostMapping("/add")
	void addTrainer(@RequestBody Trainer theTrainer){
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
