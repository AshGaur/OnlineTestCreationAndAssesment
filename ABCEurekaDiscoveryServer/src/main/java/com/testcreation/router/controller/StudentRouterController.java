package com.testcreation.router.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.router.graphql.StudentGraphQLService;
import com.testcreation.router.service.StudentRouterService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/students")
public class StudentRouterController {
	
	@Autowired
	StudentRouterService service;
	
	@Autowired
	StudentGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLStudents(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addStudents(@RequestBody String theStudent) {
		return service.addStudent(theStudent);
	}
	
	//Get a new subscription
	@PutMapping("/{id}/subscription/{subscriptionId}")
	public  ResponseEntity<String> updateStudent(@PathVariable Integer subscriptionId,@PathVariable Integer id) {
		return service.updateStudentBySubscriptionId(subscriptionId,id);
	}
	
	@PutMapping("/update/{id}")
	public  ResponseEntity<String> updateStudent(@RequestBody String theStudent,@PathVariable int id) {
		return service.updateStudentById(theStudent,id);
	}

	@DeleteMapping("/delete/{id}")
	public  void deleteStudent(@PathVariable Integer id) {
		service.deleteStudent(id);
	}	
}
