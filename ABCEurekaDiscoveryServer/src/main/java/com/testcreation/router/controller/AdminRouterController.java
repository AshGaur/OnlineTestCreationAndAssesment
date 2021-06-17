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

import com.testcreation.router.graphql.AdminGraphQLService;
import com.testcreation.router.service.AdminRouterService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/admins")
public class AdminRouterController {
	@Autowired
	AdminRouterService service;
	
	@Autowired
	AdminGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addAdmin(@RequestBody String theAdmin) {
		return service.addAdmin(theAdmin);
	}
	
	@PutMapping("/update/{id}")
	void updateAdmin(@PathVariable Integer id) {
			service.updateAdmin(id);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteAdmin(@PathVariable Integer id) {
			service.deleteAdmin(id);
    }
}


