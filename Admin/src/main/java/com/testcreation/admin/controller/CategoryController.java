package com.testcreation.admin.controller;

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

import com.testcreation.admin.bean.Category;
import com.testcreation.admin.exception.CategoryException;
import com.testcreation.admin.graphql.CategoryGraphQLService;
import com.testcreation.admin.service.CategoryService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	CategoryService service;

	@Autowired
	CategoryGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	Iterable<Category> getAllCategories() {
		return service.getAllCategories();
	}

	
	@PostMapping("/add")
	void addCategory(@RequestBody Category theCategory) {
		service.addCategory(theCategory);
	}
	
	@PutMapping("/update/{categoryName}")
	void updateCategory(@RequestBody Category theCategory,@PathVariable String categoryName) {
		if(service.getCategoryByName(categoryName).isEmpty())
			throw new CategoryException("Category not found !");
		service.updateCategory(theCategory);
	}
	
	@DeleteMapping("/delete/{categoryName}")
	void deleteCategory(@PathVariable String categoryName) {
		if(service.getCategoryByName(categoryName).isEmpty())
			throw new CategoryException("Category not found !");
		service.deleteCategoryByName(categoryName);
	}
}
