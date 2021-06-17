package com.testcreation.router.controller;

import java.util.List;

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

import com.testcreation.router.bean.Question;
import com.testcreation.router.bean.Trainer;
import com.testcreation.router.graphql.QuestionGraphQLService;
import com.testcreation.router.service.QuestionRouterService;
import com.testcreation.router.service.TestRouterService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/questions")
public class QuestionRouterController {
			
	@Autowired
	QuestionRouterService questionService;

	@Autowired
	QuestionGraphQLService graphQLService;
	
	@Autowired
	QuestionRouterService service;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLStudents(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	List<Question> getAllQuestions() {
		return service.getAllQuestions();
	}
	
	@GetMapping("test/{testId}")
	Object getQuestionsByTestId(@PathVariable Integer testId) {
		return service.getQuestionsByTestId(testId);
	}
	
	@GetMapping("{testId}")
	Object getQuestionById(@PathVariable Integer id) {
		return service.getQuestionById(id);
	}
	@PostMapping("/add/test/{testId}")
	public ResponseEntity<String>addNewQuestion(@RequestBody Question tempQuestion, @PathVariable Integer testId) {
		return questionService.addNewQuestion(tempQuestion,testId);
	}
	
	
	
//	//Update a question by id
         @PutMapping("/update/{questionId}")
          void updateQuestion(@RequestBody  Question tempQuestion , @PathVariable Integer questionId) {
//		 if(questionService.getQuestionById(questionId).isEmpty())
//			throw new TrainerException("Question ID not found !"); 
		        questionService.updateQuestion(tempQuestion,questionId);
	}

//	//Delete Question by questionId
	@DeleteMapping("/delete/{questionId}")
	void deleteQuestionById(@PathVariable Integer questionId) {
//		if(questionService.getQuestionById(questionId).isEmpty())
//			throw new TrainerException("Question ID not found !");
		questionService.deleteQuestionById(questionId);
	}

//	//Delete All Questions by Id
	@DeleteMapping("/delete/test/{testId}")
	void deleteAllQuestionsFromTestId(@PathVariable Integer testId) {
//		if(questionService.getAllQuestionsByTestId(testId).size()==0)
//			throw new TrainerException("No Questions Present across testId to delete !");
		questionService.deleteAllQuestionsFromTestId(testId);
	}
}
