package com.testcreation.students.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.testcreation.students.bean.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

	Optional<Student> findById(Integer id);
	
	List<Student> findBySubscriptionId(Integer subscriptionId);
}