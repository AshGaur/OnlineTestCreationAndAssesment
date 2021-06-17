package com.testcreation.trainer.exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testcreation.trainer.bean.Subscription;
import com.testcreation.trainer.bean.Test;
import com.testcreation.trainer.bean.Trainer;
import com.testcreation.trainer.service.TrainerService;

@Component
public class SubscriptionValidation {

	@Autowired
	TrainerService trainerService;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	
	public void validateTrainerSubscription(Test test,Integer trainerId) {
		Trainer trainer = trainerService.getTrainerById(trainerId).isPresent()?trainerService.getTrainerById(trainerId).get():null;
		if(trainer==null) {
			throw new TestException("Trainer ID not found !");
		}

		Subscription subscription = trainer.getSubscription();
		
		if(!subscription.getRole().toLowerCase().equals("trainer") && ! subscription.getRole().toLowerCase().equals("all")) {
			throw new TestException("Subscription not valid for trainers !");
		}
		
		Date fromDate;
		Date toDate;
		try {
			fromDate = formatter.parse(test.getFromDateString());
			toDate = formatter.parse(test.getToDateString());
		} catch (ParseException e) {
			throw new TestException("Date required in proper format 'dd-mm-yyyy hh:mm:ss'");
		} 
		
		if(fromDate.before(new Date()) || toDate.before(fromDate)) {
			throw new TestException("Invalid Dates entered !");
		}
		
		long diffInMillies = Math.abs(toDate.getTime() - fromDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		if(diff>subscription.getTestAvailability()) {
			throw new TestException("Subscription doesn't allow the given availability !");
		}
		
		
		//Validating remaining tests
		if(trainer.getTestsLeft()<1) {
			throw new TestException("Please take a new subscription to create more tests !");
		}
		
		Date endServiceDate;
		try {
			endServiceDate = formatter.parse(trainer.getEndServiceDate());
		} catch (ParseException e) {
			throw new TestException("Dates allowed only in 'dd-mm-yyyy hh:mm:ss' format !");
		}
		
		//Service usage check
		if(endServiceDate.before(new Date())) {
			throw new TrainerException("Subscription expired ! please take a new one");
		}
	}
	
}
