package com.testcreation.trainer.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testcreation.trainer.bean.Result;
import com.testcreation.trainer.bean.Test;
import com.testcreation.trainer.dao.TestRepository;

@Service
public class TestService {
	@Autowired
	TestRepository testRepo;

	@Autowired
	RestTemplate restTemplate;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	
	public Iterable<Test> getAllTests() {
		return testRepo.findAll();
	}
	
	public List<Test> getAvailableTests(){
		List<Test>  available = new ArrayList<>();
		testRepo.findAll().forEach(test->{
			Date fromDate = null;
			Date toDate = null;
			try {
				fromDate = formatter.parse(test.getFromDateString());
				toDate = formatter.parse(test.getToDateString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date currentDate = new Date();
			if(currentDate.after(fromDate) && currentDate.before(toDate)) {
				available.add(test);
			}
		});
		return available;
	}
	 
	public Optional<Test> getTestById(int id) {
		return testRepo.findById(id);
	}
	
	public List<Test> getTestsByTrainerId(Integer trainerId){
		return testRepo.findByTrainerId(trainerId);
	}
	
	public List<Test> getTestsByCategoryName(String categoryName){
		return testRepo.findByCategoryCategoryName(categoryName);
	}
	
	public void addTest(Test theTest) {
		testRepo.save(theTest);
	}
	
	public void updateTest(Test theTest) {
		testRepo.save(theTest);
	}
	
	public void deleteTest(int id) {
		testRepo.deleteById(id);
	}
	
	public void deleteTestsByTrainerId(Integer trainerId) {
		testRepo.deleteByTrainerId(trainerId);
	}

	public Integer getStudentCountByTestId(Integer testId) {
		String url = "http://STUDENT-MICROSERVICE/results/test/"+testId;
		return Arrays.asList(restTemplate.getForObject(url, Result[].class)).size();
	}
}
