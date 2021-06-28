package com.testcreation.trainer.controller;

import java.text.ParseException;
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

import com.testcreation.trainer.bean.Category;
import com.testcreation.trainer.bean.Test;
import com.testcreation.trainer.bean.Trainer;
import com.testcreation.trainer.exception.SubscriptionValidation;
import com.testcreation.trainer.exception.TestException;
import com.testcreation.trainer.graphql.QuestionGraphQLService;
import com.testcreation.trainer.graphql.TestGraphQLService;
import com.testcreation.trainer.service.QuestionService;
import com.testcreation.trainer.service.TestService;
import com.testcreation.trainer.service.TrainerService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/tests")
public class TestController {
		
	@Autowired
	TestService service;
	
	@Autowired
	TrainerService trainerService;
	
	@Autowired
	SubscriptionValidation subValidator;
	
	@Autowired
	TestGraphQLService graphQLService;
	
	@Autowired
	QuestionService questionService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	Iterable<Test> getAllTests() {
		return service.getAllTests();
	}
	
	@GetMapping("/{testId}")
	Optional<Test> getTestByTestId(@PathVariable Integer testId) {
		if(service.getTestById(testId).isEmpty()) {
			throw new TestException("Unknown Test ID !");
		}
		return service.getTestById(testId);
	}
	
	@GetMapping("/trainer/{trainerId}")
	List<Test> getTestsByTrainerId(@PathVariable Integer trainerId){
		return service.getTestsByTrainerId(trainerId);
	}
	
	@GetMapping("/category/{categoryName}")
	List<Test> getTestsByCategoryName(@PathVariable String categoryName){
		return service.getTestsByCategoryName(categoryName);
	}
	
	@GetMapping("/studentCount/test/testId")
	public Integer getStudentCountByTestId(@PathVariable Integer testId) {
		return service.getStudentCountByTestId(testId);
	}
	
	@PostMapping("/add/trainer/{trainerId}/category/{categoryName}")
	void addTest(@RequestBody Test tempTest,@PathVariable Integer trainerId,@PathVariable String categoryName) throws ParseException  {
		
		subValidator.validateTrainerSubscription(tempTest, trainerId);
		
		tempTest.setTrainer(new Trainer(trainerId));
		tempTest.setCategory(new Category(categoryName));

		Trainer trainer = trainerService.getTrainerById(trainerId).get();
		trainer.setTestsLeft(trainer.getTestsLeft()-1);
		
		service.addTest(tempTest);
	}

	@PutMapping("/update/{testId}")
	void updateTest(@RequestBody Test tempTest, @PathVariable int testId) {
		if(service.getTestById(testId).isEmpty()) {
			throw new TestException("No tests found with the entered ID !");
		}
		
		subValidator.validateTrainerSubscription(tempTest, tempTest.getTrainer().getId());
		
		service.updateTest(tempTest);
	}
			
	@DeleteMapping("/delete/{testId}")
	void deleteTest(@PathVariable int testId) {
		if(service.getTestById(testId).isEmpty()) {
			throw new TestException("No tests found with the entered ID !");
		}
		questionService.deleteAllQuestionsByTestId(testId);
		service.deleteTest(testId);
	}
	
	@DeleteMapping("/delete/trainer/{trainerId}")
	void deleteTestsByTrainerId(@PathVariable int trainerId) {
		if(service.getTestsByTrainerId(trainerId).size()==0) {
			throw new TestException("No tests found for this trainer !");
		}
		service.deleteTestsByTrainerId(trainerId);
	}
}



