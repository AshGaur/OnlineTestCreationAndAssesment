package com.testcreation.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testcreation.admin.bean.Subscription;
import com.testcreation.admin.dao.SubscriptionRepository;

@Service
public class SubscriptionService {
	@Autowired 
	SubscriptionRepository subscriptionRepo; 
			
	 public Iterable<Subscription> getAllSubscriptions() {
		return subscriptionRepo.findAll();
	 }
	
	 public Optional<Subscription> getSubscriptionById(Integer id) {
		 return subscriptionRepo.findById(id);
	 }
	 
	 public void addSubscription(Subscription theSubscription) {
		 subscriptionRepo.save(theSubscription);
	 }
	 
	 public void updateSubscription(Subscription theSubscription) {
		 subscriptionRepo.save(theSubscription);
	 }
	 
	 public void deleteSubscription(Integer id) {
		 subscriptionRepo.deleteById(id);
	 }
}
