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

//import com.testcreation.admin.bean.Category;
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
    public ResponseEntity<String> addCategory(@RequestBody String theCategory) {
		return service.addCategory(theCategory);
	}
	
	@PutMapping("/update/{testId}")
	public ResponseEntity<String> updateCategory(@RequestBody String theCategory,@PathVariable String categoryName) {
    return service.updateCategory(theCategory,categoryName);
	}
	
	@DeleteMapping("/delete/{categoryName}")
	void deleteByCategoryName(@PathVariable String categoryName) {
	service.deleteByCategoryName(categoryName);
	}

	
	
}