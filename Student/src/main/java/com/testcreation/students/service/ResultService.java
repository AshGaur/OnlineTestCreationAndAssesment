package com.testcreation.students.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testcreation.students.bean.Result;
import com.testcreation.students.dao.ResultRepository;

@Service
public class ResultService {
	@Autowired
	ResultRepository repo;
	
	public Iterable<Result> getAllResult() {
		return repo.findAll();                          
	}
	
	public Optional<Result> getResultById(Integer id) {
		return repo.findById(id);
	}

	public void addResult(Result theResult) {
		repo.save(theResult);
	}

	public void updateResult(Result theResult) {
		repo.save(theResult);	
	}
	
	public void updateResultById(Result result) {
		repo.save(result);	
	}

	public void deleteResultById(Integer id) {
		repo.deleteById(id);	
	}

	//Get Results By Student ID after mapping student
	public List<Result> getResultsByStudentId(Integer studentId) {
		return repo.findByStudentId(studentId);
	}

	
}


