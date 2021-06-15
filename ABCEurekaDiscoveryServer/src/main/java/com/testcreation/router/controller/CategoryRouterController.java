package com.testcreation.router.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.router.graphql.CategoryGraphQLService;
import com.testcreation.router.service.CategoryRouterService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/categories")
public class CategoryRouterController {

	@Autowired
	CategoryRouterService service;
	
	@Autowired
	CategoryGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@PostMapping("/add")
    public void addCategory(@RequestBody String category) {
		 service.addCategory(category);
	}
	
}