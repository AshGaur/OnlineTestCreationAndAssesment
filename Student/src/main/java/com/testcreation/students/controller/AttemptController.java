package com.testcreation.students.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.students.bean.Attempt;
import com.testcreation.students.bean.Question;
import com.testcreation.students.dto.AttemptDto;
import com.testcreation.students.service.AttemptService;

@RestController
@RequestMapping("/attempts")
public class AttemptController {

	@Autowired
	AttemptService service;
	
//	@PostMapping("/add")
//	public void addAttempt(@RequestBody AttemptDto attemptDto) {
//		
//		service.addAttempt(new Attempt(attemptDto.getResultId(),attemptDto.getQuestionId()));
//	}
	
	@GetMapping("/question/{questionId}")
	public Object getQuestionById(@PathVariable Integer questionId) {
		return service.getQuestionById(questionId);
	}
	
}
