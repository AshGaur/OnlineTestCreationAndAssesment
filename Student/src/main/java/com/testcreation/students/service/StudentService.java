package com.testcreation.students.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testcreation.bean.Student;
import com.testcreation.students.dao.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repo;
	
	public Iterable<Student> getAllStudents() {
		return repo.findAll();
	}
	
	public Optional<Student> getStudentById(Integer id) {
		return repo.findById(id);
	}

	public void addStudent(Student theStudent) {
		repo.save(theStudent);
	}

	public void updateStudent(Student theStudent) {
		repo.save(theStudent);	
	}
	
	public Optional<Student> updateStudentById(Integer id) {
		return repo.save(id);	
	}

	public void deleteStudent(Integer id) {
		repo.deleteById(id);	
	}
}
