package com.testcreation.students.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.testcreation.students.bean.Attempt;
import com.testcreation.students.bean.Question;
import com.testcreation.students.bean.Result;
import com.testcreation.students.bean.Student;
import com.testcreation.students.bean.Test;
import com.testcreation.students.dto.ResultDto;
import com.testcreation.students.exception.StudentException;
import com.testcreation.students.service.AttemptService;
import com.testcreation.students.service.ResultService;


@RestController
@RequestMapping("/results")
public class ResultController {
	
	@Autowired
	ResultService service;
	
	@Autowired
	AttemptService attemptService;
	
	Calendar calendar = Calendar.getInstance();
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	
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
	
	@GetMapping("/student/{studentId}/test/{testId}")
	public List<Result> getResultsByStudentIdAndTestId(@PathVariable Integer studentId,@PathVariable Integer testId){
		return service.getResultsByStudentIdAndTestId(studentId,testId);
	}
	
	@PostMapping("/add")
	void addResult(@RequestBody ResultDto resultDto) {
		if(service.getResultsByStudentIdAndTestId(resultDto.getStudentId(), resultDto.getTestId()).size()>0) {
			throw new StudentException("Student already has given this test !");
		}
		service.addResult(new Result(resultDto.getScore(),resultDto.getNumberOfAttempts(),resultDto.getStudentId(),resultDto.getTestId()));	
	}
	
	@PostMapping("/add/student/{studentId}/test/{testId}")
	void addResult(@RequestBody Result result,@PathVariable Integer studentId,@PathVariable Integer testId) {
		result.setStudent(new Student(studentId));
		result.setTest(new Test(testId));
		if(service.getResultsByStudentIdAndTestId(studentId, testId).size()>0) {
			throw new StudentException("Student already has given this test !");
		}
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, result.getTest().getDuration());
		result.setEndString(formatter.format(calendar.getTime()));
		service.addResult(result);
	}
	
	@PutMapping("/update/{id}")
	void updateResult(@RequestBody Result theResult,@PathVariable Integer id) {
		if(service.getResultById(id).isEmpty())
			throw new StudentException("Result doesn't exist with ID provied...!");
		service.updateResultById(theResult);
	}
	
	@PutMapping("/complete/{id}")
	void endTest(@PathVariable Integer id) {
		Result result = service.getResultById(id).isPresent()?service.getResultById(id).get():null; 
		if(result==null) {
			throw new StudentException("Result doesn't exist !");
		}
		result.setCompleted(true);
		Double score=0.0;
		for(Attempt attempt:attemptService.getAttemptsByResultId(id)) {
			Question question = attempt.getQuestionList().get(0);
			Double reward = question.getScore();
			Double penalty = question.getNegativeMarking();
			score+= attempt.getCorrect()?reward:-penalty;
		}
		result.setScore(score);
		service.updateResult(result);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteResult(@PathVariable Integer id) {
		if(service.getResultById(id).isEmpty())
			throw new StudentException("Result doesn't exist with ID provied...!");
		service.deleteResultById(id);
	}	
	
}
