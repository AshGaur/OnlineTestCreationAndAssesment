package com.testcreation.students.controller;

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

import com.testcreation.students.bean.Result;
import com.testcreation.students.bean.Student;
import com.testcreation.students.bean.Test;
import com.testcreation.students.dto.ResultDto;
import com.testcreation.students.exception.StudentException;
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
		if(service.getResultById(id).isEmpty())
		throw new StudentException("Provided result id is not exists...!");
		return service.getResultById(id);
	}
	
	@GetMapping("/student/{studentId}")
	 public List<Result> getResultsByStudentId(@PathVariable Integer studentId){
		if(service.getResultsByStudentId(studentId).isEmpty())
			throw new StudentException("Result doesn't exist with ID provied...!");
		return service.getResultsByStudentId(studentId);
	}
	
	@PostMapping("/add")
	void addResult(@RequestBody ResultDto resultDto) {
//		theResult.setStudent(new Student(studentId));
//		theResult.setTest(new Test(testId));
		service.addResult(new Result(resultDto.getScore(),resultDto.getNumberOfAttempts(),resultDto.getStudentId(),resultDto.getTestId()));	
	}
	
//	@PostMapping("/add/studentId/{studentId}/testId/{testId}")
//	void addResult(@RequestBody Result theResult,@PathVariable Integer studentId,@PathVariable Integer testId) {
//		theResult.setStudent(new Student(studentId));
//		theResult.setTest(new Test(testId));
//		service.addResult(theResult);	
//	}
	
	@PutMapping("/update/{id}")
	void updateResult(@RequestBody Result theResult,@PathVariable Integer id) {
		if(service.getResultById(id).isEmpty())
			throw new StudentException("Result doesn't exist with ID provied...!");
		service.updateResultById(theResult);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteResult(@PathVariable Integer id) {
		if(service.getResultById(id).isEmpty())
			throw new StudentException("Result doesn't exist with ID provied...!");
		service.deleteResultById(id);
	}	
	
}
