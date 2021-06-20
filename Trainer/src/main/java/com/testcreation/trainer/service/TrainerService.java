package com.testcreation.trainer.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testcreation.trainer.bean.Subscription;
import com.testcreation.trainer.bean.Trainer;
import com.testcreation.trainer.dao.TrainerRepository;

@Service
public class TrainerService {
	
	@Autowired 
	TrainerRepository trainerRepo; 
	
	@Autowired
	RestTemplate restTemplate;
	
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

	public List<Trainer> getTrainersBySubscriptionId(Integer id) {
		 return trainerRepo.findBySubscriptionId(id);
	}

	public Optional<Trainer> getByEmailOrPhone(String username) {
		// TODO Auto-generated method stub
		return trainerRepo.findByEmailOrPhone(username,username);
	}
	
	public Subscription getSubscriptionById(Integer subscriptionId) {
		String url = "http://ADMIN-MICROSERVICE/subscriptions/"+subscriptionId;
		return restTemplate.getForObject(url, Subscription.class);
	}

}
