package com.testcreation.students.controller;

import java.beans.IntrospectionException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.students.bean.Student;
import com.testcreation.students.bean.Subscription;
import com.testcreation.students.exception.StringValidators;
import com.testcreation.students.exception.StudentException;
import com.testcreation.students.exception.ValidationException;
import com.testcreation.students.service.StudentService;
//import com.testcreation.trainer.bean.Trainer;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	StudentService service;
	
	@Autowired
	StringValidators validator;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	Calendar calendar = Calendar.getInstance();
	
	@GetMapping("/all")
	Iterable<Student> getAllStudents() {
		return service.getAllStudents();
	}
	
	@GetMapping("/{id}")
	Student getStudentById(@PathVariable Integer id){
		if(service.getStudentById(id).isEmpty()){
			throw new StudentException("student id doesn't exist");
		}
		return service.getStudentById(id).get();
	}
	

	@GetMapping("/subscription/{subscriptionId}")
	List<Student> getStudentBySubscriptionId(@PathVariable Integer subscriptionId){
		if(service.getStudentBySubscriptionId(subscriptionId).isEmpty()){
			throw new StudentException("student id dosent exist");
		}
		return service.getStudentBySubscriptionId(subscriptionId);
	}
	
	//Get a new subscription
	@PutMapping("/{id}/subscription/{subscriptionId}")
	void studentSubscription(@PathVariable Integer subscriptionId,@PathVariable Integer id) {
		Student student = !service.getStudentById(id).isEmpty()?service.getStudentById(id).get():null;
		if(student==null) {
			throw new StudentException("Unknown Student ID !");
		}
		student.setSubscription(new Subscription(subscriptionId));
		service.updateStudent(student);
	}
	
	@PostMapping("/add/subscription/{subscriptionId}")
	void addStudent(@RequestBody Student theStudent,@PathVariable Integer subscriptionId) {
		boolean isRequired = true;
		if(theStudent.getEmail()!=null) {
			validator.validateEmail(theStudent.getEmail());
			isRequired = false;
		}
		if(theStudent.getPhone()!=null) {
			validator.validatePhone(theStudent.getPhone());
			isRequired = false;
		}
		if(isRequired) {
			throw new StudentException("Atleast one among phone or email is required !");
		}
		validator.validateName(theStudent.getName());
		validator.validatePassword(theStudent.getPassword());
		boolean paymentSuccessful = true;
		if(subscriptionId!=1 && paymentSuccessful) {
			theStudent.setSubscription(new Subscription(subscriptionId));
		}else {
			theStudent.setSubscription(new Subscription(1));
		}
		
		calendar.setTime(new Date());
		//Subscription values setting for Trainer
		Subscription subscription = service.getSubscriptionById(subscriptionId);
		
		//Set endService Date
		calendar.add(Calendar.DATE, subscription.getServiceUsageLimit());
		theStudent.setEndServiceDate(formatter.format(calendar.getTime()));
		
		//Set tests to be created
		theStudent.setTestsLeft(subscription.getTestNumberLimit());
		
		service.addStudent(theStudent);
	}
	
	@PutMapping("/update/{id}")
	void updateStudent(@RequestBody Student theStudent,@PathVariable int id) throws IllegalArgumentException, IllegalAccessException, IntrospectionException, InvocationTargetException {
		Optional<Student> dbStudent = service.getStudentById(id);
		if(dbStudent.isEmpty()) {
			throw new StudentException("No Student Present with provided ID !");
		}
		PropertyDescriptor pd;
		Student existingStudent = dbStudent.get();
		for(Field updatedField:theStudent.getClass().getDeclaredFields()) {
			pd = new PropertyDescriptor(updatedField.getName(), Student.class);
			Method getter = pd.getReadMethod();
			Method setter = pd.getWriteMethod();
			for(Field prevField:existingStudent.getClass().getDeclaredFields()) {
				System.out.println(getter.invoke(theStudent)!=null && prevField.getName().equals(updatedField.getName()));
				if(getter.invoke(theStudent)!=null && prevField.getName().equals(updatedField.getName())) {
					switch(updatedField.getName()) {
						case "name" : validator.validateName(theStudent.getName());break;
						case "email": validator.validateEmail(theStudent.getEmail());break;
						case "password": validator.validatePhone(theStudent.getPassword());break;
						case "phone": validator.validatePhone(theStudent.getPhone());break;
						case "id": throw new StudentException("id updation not allowed !");
					}
					setter.invoke(existingStudent, getter.invoke(theStudent));
				}
			}
		}
		service.updateStudent(existingStudent);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteStudent(@PathVariable Integer id) {
		if(service.getStudentById(id).isEmpty()){
			throw new StudentException("student id dosent exist");
		}
		service.deleteStudent(id);
	}	
}
