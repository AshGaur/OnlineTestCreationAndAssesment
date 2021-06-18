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
import org.springframework.web.client.RestTemplate;

import com.testcreation.students.bean.Attempt;
import com.testcreation.students.bean.Question;
import com.testcreation.students.bean.Result;
import com.testcreation.students.bean.Student;
import com.testcreation.students.bean.Subscription;
import com.testcreation.students.bean.Test;
import com.testcreation.students.dto.ResultDto;
import com.testcreation.students.exception.StudentException;
import com.testcreation.students.exception.SubscriptionValidation;
import com.testcreation.students.service.AttemptService;
import com.testcreation.students.service.ResultService;
import com.testcreation.students.service.StudentService;


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
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
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
	public Result getResultByStudentIdAndTestId(@PathVariable Integer studentId,@PathVariable Integer testId){
		return service.getResultByStudentIdAndTestId(studentId,testId);
	}
	
	@GetMapping("/subscription/{subscriptionId}")
	public Subscription getSubscriptionById(@PathVariable Integer subscriptionId) {
		return studentService.getSubscriptionById(subscriptionId);
	}
	
	@GetMapping("/getTest/{testId}")
	public Test getTestById(@PathVariable Integer testId) {
		return studentService.getTestById(testId);
	}
	
	@PostMapping("/add/student/{studentId}/test/{testId}")
	void addResult(@RequestBody Result result,@PathVariable Integer studentId,@PathVariable Integer testId) {
		result.setStudent(new Student(studentId));
		result.setTest(new Test(testId));
		if(service.getResultByStudentIdAndTestId(studentId, testId)!=null) {
			throw new StudentException("Student already has given this test !");
		}
		subValidator.validateStudentSubscription(result, studentId);
		
		calendar.setTime(new Date());
		
		Test test = studentService.getTestById(testId);
		
		calendar.add(Calendar.MINUTE, test.getDuration());
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
