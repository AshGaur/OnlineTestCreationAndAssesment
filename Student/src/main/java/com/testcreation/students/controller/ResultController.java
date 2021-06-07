package com.testcreation.students.controller;

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

import com.testcreation.students.bean.Result;
import com.testcreation.students.service.ResultService;


@RestController
@RequestMapping("/results")
public class ResultController {
	
	@Autowired
	ResultService service;
	
	@GetMapping("/all")
	public Iterable<Result> getAllResult() {
		return service.getAllResult();
	}
	
	@GetMapping("/{id}")
	public Optional<Result> getResultById(@PathVariable Integer id){
		return service.getResultById(id);
	}
	
//	@GetMapping("/student/{studentId}")
//	public List<Result> getResultsByStudentId(@PathVariable Integer studentId){
//		return service.getResultsByStudentId(studentId);
//	}
	
	@PostMapping("/add")
	void addResult(@RequestBody Result theResult) {
		service.addResult(theResult);
	}
	
	@PutMapping("/update/{id}")
	void updateResult(@RequestBody Result theResult) {
		service.updateResultById(theResult);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteResult(@PathVariable Integer id) {
		service.deleteResultById(id);
	}	
	
}
