package com.testcreation.students.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.testcreation.students.bean.Subscription;
import com.testcreation.students.bean.Test;
import com.testcreation.students.dto.TestDto;
import com.testcreation.students.exception.StudentException;
import com.testcreation.students.exception.SubscriptionValidation;
import com.testcreation.students.graphql.ResultGraphQLService;
import com.testcreation.students.service.AttemptService;
import com.testcreation.students.service.ResultService;
import com.testcreation.students.service.StudentService;

import graphql.ExecutionResult;


@RestController
@RequestMapping("/results")
public class ResultController {
	
	@Autowired
	ResultService service;
	
	@Autowired
	AttemptService attemptService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	SubscriptionValidation subValidator;
	
	Calendar calendar = Calendar.getInstance();
	
//	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	ResultGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Result> getAllResults() {
		return service.getAllResults();
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
	
	@GetMapping("/test/{testId}")
	public List<Result> getResultsByTestId(@PathVariable Integer testId){
		return service.getResultsByTestId(testId);
	}
	
	@GetMapping("/student/{studentId}/test/{testId}")
	public Result getResultByStudentIdAndTestId(@PathVariable Integer studentId,@PathVariable Integer testId){
		return service.getResultByStudentIdAndTestId(studentId,testId);
	}
	
	@GetMapping("/subscription/{subscriptionId}")
	public Subscription getSubscriptionById(@PathVariable Integer subscriptionId) {
		return studentService.getSubscriptionById(subscriptionId);
	}
	
	@GetMapping("/getTest/{testId}")
	public TestDto getTestById(@PathVariable Integer testId) {
		return studentService.getTestById(testId);
	}
	
	@PostMapping("/add/student/{studentId}/test/{testId}")
	void addResult(@RequestBody Result result,@PathVariable Integer studentId,@PathVariable Integer testId) {
		result.setStudent(new Student(studentId));
		result.setTest(new Test(testId));
		if(service.getResultByStudentIdAndTestId(studentId, testId)!=null) {
			throw new StudentException("Student already has started this test !");
		}
		subValidator.validateStudentSubscription(result, studentId);
		
		System.out.println("Subscription Validated !");
		
		calendar.setTime(new Date());
		
		TestDto testDto = studentService.getTestById(testId);
		
		calendar.add(Calendar.MINUTE, testDto.getDuration());
		result.setEndString(formatter.format(calendar.getTime()));
		
		Student student = studentService.getStudentById(studentId).get();
		student.setTestsLeft(student.getTestsLeft()-1);
		service.addResult(result);
	}
	
	@PutMapping("/update/{id}")
	void updateResult(@RequestBody Result theResult,@PathVariable Integer id) {
		if(service.getResultById(id).isEmpty())
			throw new StudentException("Result doesn't exist with ID provied...!");
		
		subValidator.validateStudentSubscription(theResult, theResult.getStudent().getId());
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
			Double penalty = attempt.getAttemptString().length()>0?question.getNegativeMarking():0;
			score+= attempt.getCorrect()?reward:-penalty;
		}
		result.setScore(score);
		service.updateResult(result);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteResult(@PathVariable Integer id) {
		if(service.getResultById(id).isEmpty())
			throw new StudentException("Result doesn't exist with ID provied...!");
		attemptService.deleteByResultId(id);
		service.deleteResultById(id);
	}	
	
}
