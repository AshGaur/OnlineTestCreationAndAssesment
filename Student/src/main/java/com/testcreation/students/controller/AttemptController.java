package com.testcreation.students.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.testcreation.students.bean.Attempt;
import com.testcreation.students.bean.Result;
import com.testcreation.students.dto.AttemptDto;
import com.testcreation.students.dto.QuestionDto;
import com.testcreation.students.exception.AttemptException;
import com.testcreation.students.graphql.AttemptGraphQLService;
import com.testcreation.students.service.AttemptService;
import com.testcreation.students.service.ResultService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/attempts")
public class AttemptController {

	@Autowired
	AttemptService service;
	
	@Autowired
	ResultService resultService;
	
	@Autowired
	AttemptGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public void addAttempt(@RequestBody AttemptDto attemptDto) throws JsonMappingException, JsonProcessingException {
		if(service.getQuestionById(attemptDto.getQuestionId())==null) {
			throw new AttemptException("questionId doesn't exist !");
		}
		
		Result result = service.getResultById(attemptDto.getResultId()).isPresent()?service.getResultById(attemptDto.getResultId()).get():null; 
		
		if(result==null) {
			throw new AttemptException("resultId doesn't exist !");
		}

		if(result.getCompleted()) {
			throw new AttemptException("Test Ended no more attempts allowed !");
		}
		
		//To update if attempt already exists for a question Id
		Integer attemptId = -1;
		if(service.getAttemptByResultIdAndQuestionId(attemptDto.getResultId(), attemptDto.getQuestionId())!=null) {
			attemptId = service.getAttemptByResultIdAndQuestionId(attemptDto.getResultId(), attemptDto.getQuestionId()).getId();
		}
		
		QuestionDto question = service.getQuestionById(attemptDto.getQuestionId());
		Boolean correct = question.getAnswerString().equals(attemptDto.getAttemptString());
		Attempt attempt =new Attempt(attemptDto.getResultId(),attemptDto.getQuestionId(),correct,attemptDto.getAttemptString());
		
		//If attempt already exists get id for previous entry and add it to current entry
		if(attemptId>-1) {
			attempt.setId(attemptId);
		}
		service.addAttempt(attempt);
	}
	
	@GetMapping("/all")
	public Iterable<Attempt> getAllAttempts(){
		return service.getAllAttempts();
	}
	
	@GetMapping("/result/{resultId}/question/{questionId}")
	public Attempt getAttemptByResultIdAndQuestionId(@PathVariable Integer resultId,@PathVariable Integer questionId){
		return service.getAttemptByResultIdAndQuestionId(resultId,questionId);
	}
	
	@GetMapping("/result/{resultId}")
	public List<Attempt> getAttemptsByResultId(@PathVariable Integer resultId){
		return service.getAttemptsByResultId(resultId);
	}
	
	@GetMapping("/question/{questionId}")
	public QuestionDto getQuestionById(@PathVariable Integer questionId) throws JsonMappingException, JsonProcessingException {
		return service.getQuestionById(questionId);
	}
	
}
