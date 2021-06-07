package com.testcreation.students.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.testcreation.bean.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

	Optional<Student> findById(Integer id);

	Optional<Student> save(Integer id);
//	void save(Integer id);
}