package com.testcreation.admin.controller;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.admin.bean.Admin;
import com.testcreation.admin.exception.AdminException;
import com.testcreation.admin.exception.StringValidators;
import com.testcreation.admin.graphql.AdminGraphQLService;
import com.testcreation.admin.service.AdminService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/admins")
public class AdminController {
	@Autowired
	AdminService service;
	
	@Autowired
	StringValidators validator;
	
	@Autowired
	AdminGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	Iterable<Admin> getAllAdmins() {
		return service.getAllAdmins();
	}
	
	@GetMapping("/{id}")
	Optional<Admin> getAdminById(@PathVariable Integer id){
		if(service.getAdminById(id).isEmpty())
			throw new AdminException("Admin doesn't exist with ID provied !");
		return service.getAdminById(id);
	}
	
	@PostMapping("/add")
//	@RequestMapping(value="/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addAdmin(@RequestBody Admin theAdmin) {
		validator.validateEmail(theAdmin.getEmail());
		validator.validatePassword(theAdmin.getPassword());
		service.addAdmin(theAdmin);
		return "Admin added successfully !";
	}
	
	@PutMapping("/update/{id}")
	void updateAdmin(@RequestBody Admin theAdmin,@PathVariable Integer id) {
		if(service.getAdminById(id).isEmpty())
			throw new AdminException("Admin doesn't exist with ID provied !");
		service.updateAdmin(theAdmin);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteAdmin(@PathVariable Integer id) {
		if(service.getAdminById(id).isEmpty())
			throw new AdminException("Admin doesn't exist with ID provied !");
		service.deleteAdmin(id);
	}	
}



