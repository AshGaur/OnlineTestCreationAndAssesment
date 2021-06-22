package com.testcreation.students.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testcreation.students.bean.Student;
import com.testcreation.students.bean.Subscription;
import com.testcreation.students.bean.Test;
import com.testcreation.students.dao.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repo;
	
	@Autowired
	RestTemplate restTemplate;
	
	public Iterable<Student> getAllStudents() {
		return repo.findAll();
	}
	
	public Optional<Student> getStudentById(Integer id) {
		return repo.findById(id);
	}
	
	
	public List<Student> getStudentsBySubscriptionId(Integer subscriptionId) {
		return repo.findBySubscriptionId(subscriptionId);
	}

	public void addStudent(Student theStudent) {
		repo.save(theStudent);
	}

	public void updateStudent(Student theStudent) {
		repo.save(theStudent);	
	}

	public void deleteStudent(Integer id) {
		repo.deleteById(id);	
	}

	
	public Subscription getSubscriptionById(Integer subscriptionId) {
		String url = "http://ADMIN-MICROSERVICE/subscriptions/"+subscriptionId;
		return restTemplate.getForObject(url, Subscription.class);
	}
	
	public Test getTestById(Integer testId) {
		String url = "http://TRAINER-MICROSERVICE/tests/"+testId;
		return restTemplate.getForObject(url, Test.class);
	}

	
}
