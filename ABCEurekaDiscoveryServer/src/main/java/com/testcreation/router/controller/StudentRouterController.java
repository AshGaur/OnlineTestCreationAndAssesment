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

import com.testcreation.router.bean.Student;
import com.testcreation.router.bean.Subscription;
import com.testcreation.router.graphql.StudentGraphQLService;
import com.testcreation.router.service.StudentRouterService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/students")
public class StudentRouterController {
	
	@Autowired
	StudentRouterService service;
	
	@Autowired
	StudentGraphQLService graphQLService;

	//private String theStudent;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLStudents(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addStudents(@RequestBody String theStudent) {
		return service.addStudent(theStudent);
	}
	
//	//Get a new subscription
	

	
	
		@PutMapping("/{id}/subscription/{subscriptionId}")
		public  ResponseEntity<String> updateStudent(@PathVariable Integer subscriptionId,@PathVariable Integer id) {
	//	Student student = !service.getStudentById(id).isEmpty()?service.getStudentById(id).get():null;
	//student.setSubscription(new Subscription(subscriptionId));
			return service.updateStudent(id,subscriptionId);
		}
	

	@PutMapping("/update/{id}")
	public  ResponseEntity<String> updateStudent(@RequestBody String theStudent,@PathVariable int id) {
		return service.updateStudent(theStudent,id);
	}
	
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

//	
	@DeleteMapping("/delete/{id}")
	public  void deleteStudent(@PathVariable Integer id) {
		service.deleteStudent(id);
	}	
}
