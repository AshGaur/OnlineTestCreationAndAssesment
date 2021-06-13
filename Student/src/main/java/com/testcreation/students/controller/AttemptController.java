package com.testcreation.students.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.testcreation.students.bean.Attempt;
import com.testcreation.students.dto.AttemptDto;
import com.testcreation.students.dto.QuestionDto;
import com.testcreation.students.exception.AttemptException;
import com.testcreation.students.service.AttemptService;

@RestController
@RequestMapping("/attempts")
public class AttemptController {

	@Autowired
	AttemptService service;
	
	@PostMapping("/add")
	public void addAttempt(@RequestBody AttemptDto attemptDto) throws JsonMappingException, JsonProcessingException {
		if(service.getQuestionById(attemptDto.getQuestionId())==null) {
			throw new AttemptException("questionId doesn't exist !");
		}
		
		if(service.getResultById(attemptDto.getResultId()).isEmpty()) {
			throw new AttemptException("resultId doesn't exist !");
		}
		QuestionDto question = service.getQuestionById(attemptDto.getQuestionId());
		Boolean correct = question.getAnswerString().equals(attemptDto.getAttemptString());
		service.addAttempt(new Attempt(attemptDto.getResultId(),attemptDto.getQuestionId(),correct,attemptDto.getAttemptString()));
	}
	
	@GetMapping("/all")
	public Iterable<Attempt> getAllAttempts(){
		return service.getAllAttempts();
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
