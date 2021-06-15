package com.testcreation.router.controller;

import java.beans.IntrospectionException;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.router.graphql.GraphQLService;
import com.testcreation.router.service.StudentRouterService;

import graphql.ExecutionResult;

//import com.testcreation.students.bean.Student;
//import com.testcreation.students.bean.Subscription;
//import com.testcreation.students.exception.StringValidators;
//import com.testcreation.students.exception.StudentException;
//import com.testcreation.students.exception.ValidationException;
//import com.testcreation.students.service.StudentService;
//import com.testcreation.trainer.bean.Trainer;

@RestController
@RequestMapping("/students")
public class StudentRouterController {
	
	@Autowired
	StudentRouterService service;
	
	@Autowired
	GraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLStudents(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	List<Object> getAllStudents() {
		return service.getAllStudents();
	}
	
	@GetMapping("/{id}")
	Object getStudentById(@PathVariable Integer id){
		return service.getStudentById(id);
	}

//
	@GetMapping("/subscription/{subscriptionId}")
	List<Object> getStudentBySubscriptionId(@PathVariable Integer subscriptionId){
  return service.getStudentBySubscriptionId(subscriptionId);
	}
//	
	
//	//Get a new subscription
//		@PutMapping("/{id}/subscription/{subscriptionId}")
//		void studentSubscription(@PathVariable Integer subscriptionId,@PathVariable Integer id) {
//			Student student = !service.getStudentById(id).isEmpty()?service.getStudentById(id).get():null;
//			student.setSubscription(new Subscription(subscriptionId));
//			service.updateStudent( student);
//		}
//	
	
	
	@PostMapping("/add")
	public ResponseEntity<String> addStudents(@RequestBody String theStudent, @PathVariable int subscriptionId) {
		return service.addStudent(theStudent);
	}

//	
//	@PutMapping("/update/{id}")
//	void updateStudent(@RequestBody Student theStudent,@PathVariable int id) throws IllegalArgumentException, IllegalAccessException, IntrospectionException, InvocationTargetException {
//		Optional<Student> dbStudent = service.getStudentById(id);
//		if(dbStudent.isEmpty()) {
//			throw new StudentException("No Student Present with provided ID !");
//		}
//		PropertyDescriptor pd;
//		Student existingStudent = dbStudent.get();
//		for(Field updatedField:theStudent.getClass().getDeclaredFields()) {
//			pd = new PropertyDescriptor(updatedField.getName(), Student.class);
//			Method getter = pd.getReadMethod();
//			Method setter = pd.getWriteMethod();
//			for(Field prevField:existingStudent.getClass().getDeclaredFields()) {
//				System.out.println(getter.invoke(theStudent)!=null && prevField.getName().equals(updatedField.getName()));
//				if(getter.invoke(theStudent)!=null && prevField.getName().equals(updatedField.getName())) {
//					switch(updatedField.getName()) {
//						case "name" : validator.validateName(theStudent.getName());break;
//						case "email": validator.validateEmail(theStudent.getEmail());break;
//						case "password": validator.validatePhone(theStudent.getPassword());break;
//						case "phone": validator.validatePhone(theStudent.getPhone());break;
//						case "id": throw new StudentException("id updation not allowed !");
//					}
//					setter.invoke(existingStudent, getter.invoke(theStudent));
//				}
//			}
//		}
//		service.updateStudent(existingStudent);
//	}
//	
//	@DeleteMapping("/delete/{id}")
//	void deleteStudent(@PathVariable Integer id) {
//		if(service.getStudentById(id).isEmpty()){
//			throw new StudentException("student id dosent exist");
//		}
//		service.deleteStudent(id);
	}	

