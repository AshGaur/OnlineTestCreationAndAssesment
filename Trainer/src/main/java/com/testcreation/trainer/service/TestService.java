package com.testcreation.trainer.service;

import java.util.Arrays;
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
	
	public Iterable<Test> getAllTests() {
		return testRepo.findAll();
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
		String url = "http://localhost:8081/results/test/"+testId;
		return Arrays.asList(restTemplate.getForObject(url, Result[].class)).size();
	}
}
