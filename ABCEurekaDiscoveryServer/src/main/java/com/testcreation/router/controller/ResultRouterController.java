package com.testcreation.router.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.router.bean.Result;
import com.testcreation.router.bean.Student;
import com.testcreation.router.bean.Test;
import com.testcreation.router.service.ResultRouterService;


@RestController
@RequestMapping("/results")
public class ResultRouterController {
	
	@Autowired
	ResultRouterService service;
	
	@GetMapping("/all")
	public List<Object> getAllResults() {
		return service.getAllResults();
	}
	@GetMapping("/{id}")
	public Object getResultById(@PathVariable Integer id){
		return service.getResultById(id);
    }
	@GetMapping("/results/{StudentId}")
	public Object getResultsByStudentId(Integer studentId) {
		return service.getResultByStudentId(studentId);
	}
	@PostMapping("/add/student/{studentId}/test/{testId}")
	void addResult(@RequestBody String result,@PathVariable Integer studentId,@PathVariable Integer testId){
			service.addResult(result, testId, studentId);
	}
	@DeleteMapping("/delete/{id}")
	void deleteResultById(@RequestBody String result,@PathVariable Integer id) { 
		service.deleteResultById(result, id);
	}
	
	@PutMapping("/update/{id}")
	void updateResultById(@RequestBody String result,@PathVariable Integer id) {
		 service.updateResultById(result, id);
	}
	
}
