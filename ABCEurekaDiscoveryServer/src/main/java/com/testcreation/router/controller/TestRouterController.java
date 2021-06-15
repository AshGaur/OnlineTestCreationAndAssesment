package com.testcreation.router.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcreation.router.service.TestRouterService;

@RestController
@RequestMapping("/tests")
public class TestRouterController {
		
	@Autowired
	TestRouterService service;
	
	//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	
	@GetMapping("/all")
	List<Object> getAllTests() {
		return service.getAllTests();
	}
	
	@GetMapping("/{testId}")
	Object getTestByTestId(@PathVariable Integer testId) {
		return service.getTestById(testId);
	}
	
	@GetMapping("/trainer/{trainerId}")
	List<Object> getTestsByTrainerId(@PathVariable Integer trainerId){
		return service.getTestsByTrainerId(trainerId);
	}
	
	@GetMapping("/category/{categoryName}")
	List<Object> getTestsByCategoryName(@PathVariable String categoryName){
		return service.getTestsByCategoryName(categoryName);
	}
	
	@PostMapping("/add/trainer/{trainerId}/category/{categoryName}")
	public ResponseEntity<String> addTest(@RequestBody String tempTest, @PathVariable Integer trainerId, @PathVariable String categoryName) {
		return service.addTest(tempTest, trainerId, categoryName);
	}
//	
//	@PostMapping("/add/trainer/{trainerId}/category/{categoryName}")
//	void addTest(@RequestBody Test tempTest,@PathVariable Integer trainerId,@PathVariable String categoryName) throws ParseException  {
//		tempTest.setTrainer(new Trainer(trainerId));
//		tempTest.setCategory(new Category(categoryName));
//		
//		Date fromDate = formatter.parse(tempTest.getFromDateString());
//		tempTest.setFromDate(fromDate);
//		
//		Date toDate   = formatter.parse(tempTest.getToDateString());
//		tempTest.setToDate(toDate);
//		
//		service.addTest(tempTest);
//	}
//
//	@PutMapping("/update/{testId}")
//	void updateTest(@RequestBody Test tempTest, @PathVariable int testId) {
//		if(service.getTestById(testId).isEmpty()) {
//			throw new TestException("No tests found with the entered ID !");
//		}
//		 service.updateTest(tempTest);
//	}
//			
//	@DeleteMapping("/delete/{testId}")
//	void deleteTest(@PathVariable int testId) {
//		if(service.getTestById(testId).isEmpty()) {
//			throw new TestException("No tests found with the entered ID !");
//		}
//		service.deleteTest(testId);
//	}
//	
//	@DeleteMapping("/delete/trainer/{trainerId}")
//	void deleteTestsByTrainerId(@PathVariable int trainerId) {
//		if(service.getTestsByTrainerId(trainerId).size()==0) {
//			throw new TestException("No tests found for this trainer !");
//		}
//		service.deleteTestsByTrainerId(trainerId);
//	}
}



