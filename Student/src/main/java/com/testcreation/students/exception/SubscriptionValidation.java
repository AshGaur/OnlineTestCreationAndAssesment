package com.testcreation.students.exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testcreation.students.bean.Result;
import com.testcreation.students.bean.Student;
import com.testcreation.students.bean.Subscription;
import com.testcreation.students.service.StudentService;

@Component
public class SubscriptionValidation {

	@Autowired
	StudentService studentService;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	
	public void validateStudentSubscription(Result result,Integer studentId) {
		Student student = studentService.getStudentById(studentId).isPresent()?studentService.getStudentById(studentId).get():null;
		if(student==null) {
			throw new StudentException("Trainer ID not found !");
		}
		
		//Validating remaining tests
		if(student.getTestsLeft()<1) {
			throw new StudentException("Please take a new subscription to give more tests !");
		}
		
		Date endServiceDate;
		try {
			endServiceDate = formatter.parse(student.getEndServiceDate());
		} catch (ParseException e) {
			throw new StudentException("Dates allowed only in 'dd-mm-yyyy hh:mm:ss' format !");
		}
		
		Subscription subscription = student.getSubscription();
		
		if(!subscription.getRole().toLowerCase().equals("student") && ! subscription.getRole().toLowerCase().equals("all")) {
			throw new StudentException("Subscription not valid for students !");
		}
		
		//Service usage check
		if(endServiceDate.before(new Date())) {
			throw new StudentException("Subscription expired ! please take a new one");
		}
	}
	
}
