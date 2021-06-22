package com.testcreation.students.controller;

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

import com.testcreation.students.bean.Student;
import com.testcreation.students.bean.Subscription;
import com.testcreation.students.bean.User;
import com.testcreation.students.exception.StringValidators;
import com.testcreation.students.exception.StudentException;
import com.testcreation.students.graphql.StudentGraphQLService;
import com.testcreation.students.service.StudentService;
//import com.testcreation.trainer.bean.Trainer;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	StudentService service;
	
	@Autowired
	StringValidators validator;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	Calendar calendar = Calendar.getInstance();
	
	@Autowired
	StudentGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	Iterable<Student> getAllStudents() {
		return service.getAllStudents();
	}
	
	@GetMapping("/{id}")
	Student getStudentById(@PathVariable Integer id){
		if(service.getStudentById(id).isEmpty()){
			throw new StudentException("student id doesn't exist");
		}
		return service.getStudentById(id).get();
	}

	@GetMapping("/subscription/{subscriptionId}")
	List<Student> getStudentsBySubscriptionId(@PathVariable Integer subscriptionId){
		return service.getStudentsBySubscriptionId(subscriptionId);
	}
	
	//Get a new subscription
	@PutMapping("/update/{id}/subscription/{subscriptionId}")
	void studentSubscription(@PathVariable Integer subscriptionId,@PathVariable Integer id) {
		Student student = !service.getStudentById(id).isEmpty()?service.getStudentById(id).get():null;
		if(student==null) {
			throw new StudentException("Unknown Student ID !");
		}
		student.setSubscription(new Subscription(subscriptionId));
		service.updateStudent(student);
	}
	
	@PostMapping("/add/subscription/{subscriptionId}/email/{email}")
	void addStudent(@RequestBody Student theStudent,@PathVariable Integer subscriptionId,@PathVariable String email) {
		validator.validateName(theStudent.getName());
		boolean paymentSuccessful = true;
		if(subscriptionId!=1 && paymentSuccessful) {
			theStudent.setSubscription(new Subscription(subscriptionId));
		}else {
			theStudent.setSubscription(new Subscription(1));
		}
		
		calendar.setTime(new Date());
		//Subscription values setting for Trainer
		Subscription subscription = service.getSubscriptionById(subscriptionId);
		
		if(subscription.getRole().toLowerCase().equals("trainer")) {
			throw new StudentException("Subscription not for students !");
		}
		
		//Set endService Date
		calendar.add(Calendar.DATE, subscription.getServiceUsageLimit());
		theStudent.setEndServiceDate(formatter.format(calendar.getTime()));
		
		//Set tests to be created
		theStudent.setTestsLeft(subscription.getTestNumberLimit());
		
		theStudent.setUser(new User(email));
		
		service.addStudent(theStudent);
	}
	
	@PutMapping("/update/{id}")
	void updateStudent(@RequestBody Student theStudent,@PathVariable int id){
		Optional<Student> dbStudent = service.getStudentById(id);
		if(dbStudent.isEmpty()) {
			throw new StudentException("No Student Present with provided ID !");
		}
		service.updateStudent(theStudent);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteStudent(@PathVariable Integer id) {
		if(service.getStudentById(id).isEmpty()){
			throw new StudentException("student id dosent exist");
		}
		service.deleteStudent(id);
	}	
}
