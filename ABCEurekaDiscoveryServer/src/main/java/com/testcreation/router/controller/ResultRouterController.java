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

import com.testcreation.router.bean.Result;
import com.testcreation.router.bean.Student;
import com.testcreation.router.bean.Test;
import com.testcreation.router.graphql.AttemptGraphQLService;
import com.testcreation.router.graphql.ResultGraphQLService;
import com.testcreation.router.service.ResultRouterService;

import graphql.ExecutionResult;


@RestController
@RequestMapping("/results")
public class ResultRouterController {
	
	@Autowired
	ResultRouterService service;
	
	@Autowired
	ResultGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<Result> getAllResults() {
		return service.getAllResults();
	}
	@GetMapping("/{id}")
	public Result getResultById(@PathVariable Integer id){
		return service.getResultById(id);
    }
	@GetMapping("/results/{StudentId}")
	public List<Result> getResultsByStudentId(Integer studentId) {
		return service.getResultsByStudentId(studentId);
	}
	@PostMapping("/add/student/{studentId}/test/{testId}")
	void addResult(@RequestBody String result,@PathVariable Integer studentId,@PathVariable Integer testId){
			service.addResult(result, testId, studentId);
	}
	@DeleteMapping("/delete/{id}")
	void deleteResultById(@RequestBody String result,@PathVariable Integer id) { 
		service.deleteResultById(result, id);
	}
	
	@PutMapping("/update/{id}")
	void updateResultById(@RequestBody String result,@PathVariable Integer id) {
		 service.updateResultById(result, id);
	}
	
}
