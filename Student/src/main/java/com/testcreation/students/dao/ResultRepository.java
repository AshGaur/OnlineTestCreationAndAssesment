package com.testcreation.students.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.testcreation.students.bean.Result;

public interface ResultRepository extends CrudRepository<Result, Integer> {

	List<Result> findByStudentId(Integer studentId);
}