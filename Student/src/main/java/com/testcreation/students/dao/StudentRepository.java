package com.testcreation.students.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.testcreation.students.bean.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

	Optional<Student> findById(Integer id);

	Optional<Student> save(Integer id);
	
	List<Student> findBySubscriptionId(Integer subscriptionId);

	Optional<Student> findByEmailOrPhone(String email, String phone);
}