package com.testcreation.students.dao;

import org.springframework.data.repository.CrudRepository;

import com.testcreation.students.bean.Result;

public interface ResultRepository extends CrudRepository<Result, Integer> {

//	Optional<Result> findStudentById(Integer id);
//
//	Optional<Result> save(Result theResult);
//
//	Optional<Result> findByStudentId(Integer id);

//Helpful method after mapping with student
//	List<Result> findByStudentId(Integer studentId);
}