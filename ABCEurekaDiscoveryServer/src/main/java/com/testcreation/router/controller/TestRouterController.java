package com.testcreation.router.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.router.graphql.TestGraphQLService;
import com.testcreation.router.service.TestRouterService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/tests")
public class TestRouterController {
		
	@Autowired
	TestRouterService service;
	
	@Autowired
	TestGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@PostMapping("/add/trainer/{trainerId}/category/{categoryName}")
	public ResponseEntity<String> addTest(@RequestBody String tempTest, @PathVariable Integer trainerId, @PathVariable String categoryName) {
		return service.addTest(tempTest, trainerId, categoryName);
	}

//	@PutMapping("/update/{testId}")
//	void updateTest(@RequestBody Test tempTest, @PathVariable int testId) {
//		if(service.getTestById(testId).isEmpty()) {
//			throw new TestException("No tests found with the entered ID !");
//		}
//		 service.updateTest(tempTest);
//	}
//			
//	@DeleteMapping("/delete/{testId}")
//	void deleteTest(@PathVariable int testId) {
//		if(service.getTestById(testId).isEmpty()) {
//			throw new TestException("No tests found with the entered ID !");
//		}
//		service.deleteTest(testId);
//	}
//	
//	@DeleteMapping("/delete/trainer/{trainerId}")
//	void deleteTestsByTrainerId(@PathVariable int trainerId) {
//		if(service.getTestsByTrainerId(trainerId).size()==0) {
//			throw new TestException("No tests found for this trainer !");
//		}
//		service.deleteTestsByTrainerId(trainerId);
//	}
}



