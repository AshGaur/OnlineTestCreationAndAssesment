package com.testcreation.admin.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
	 
	 public List<Subscription> getSubscriptionsByRole(String role){
		 if(role.toLowerCase().equals("admin")) {
			 return StreamSupport.stream(this.getAllSubscriptions().spliterator(), false)
					    .collect(Collectors.toList());
		 }
		 return subscriptionRepo.findByRole(role);
	 }
}
