package com.testcreation.router.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.router.bean.Attempt;
import com.testcreation.router.bean.Trainer;
import com.testcreation.router.service.AttemptRouterService;
//import com.testcreation.students.dto.AttemptDto;
//import com.testcreation.students.dto.QuestionDto;

@RestController
@RequestMapping("/attempts")
public class AttemptRouterController {

	@Autowired
	AttemptRouterService service;
	
		
	@GetMapping("/all")
	List<Attempt> getAllAttempts() {
		return service.getAllAttempts();
	}
	@GetMapping("/result/{resultId}/question/{questionId}")
	List<Attempt> getAttemptsByResultIdAndQuestionId(@PathVariable Integer resultId,Integer questionId ) {
		return service.getAttemptsByResultIdAndQuestionId(resultId,questionId);
	}
	@GetMapping("/result/{resultId}")
	List<Attempt>getAttemptsByResultId(@PathVariable Integer resultId) {
		return service.getAttemptsByResultId(resultId);
	}
//	@GetMapping("/question/{questionId}")
//	public QuestionDto getQuestionById(@PathVariable Integer questionId) {
//		return service.getQuestionById(questionId);
//	}
	
}
