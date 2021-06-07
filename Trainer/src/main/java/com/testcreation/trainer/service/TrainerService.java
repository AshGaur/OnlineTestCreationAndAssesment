package com.testcreation.trainer.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.testcreation.trainer.bean.Trainer;
import com.testcreation.trainer.dao.TrainerRepository;

@Service
public class TrainerService {
	
	@Autowired 
	TrainerRepository trainerRepo; 
			
	 public Iterable<Trainer> getAllTrainers() {
		return trainerRepo.findAll();
	 }
	
	 public Optional<Trainer> getTrainerById(Integer id) {
		 return trainerRepo.findById(id);
	 }
	 
	 public void addTrainer(Trainer theTrainer) {
		 trainerRepo.save(theTrainer);
	 }
	 
	 public void updateTrainer(Trainer theTrainer) {
		  trainerRepo.save(theTrainer);
	 }
	 
	 public void deleteTrainer(Integer id) {
		  trainerRepo.deleteById(id);
	 }
}
