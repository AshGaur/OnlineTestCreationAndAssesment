package com.testcreation.students.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testcreation.students.bean.Result;
import com.testcreation.students.bean.Subscription;
import com.testcreation.students.dao.ResultRepository;

@Service
public class ResultService {
	@Autowired
	ResultRepository repo;
	
	@Autowired
	RestTemplate restTemplate;
	
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

	//Get Results By Student ID
	public List<Result> getResultsByStudentId(Integer studentId) {
		return repo.findByStudentId(studentId);
	}

	//Get Results By StudentId and TestId
	public Result getResultByStudentIdAndTestId(Integer studentId,Integer testId){
		if(repo.findByStudentIdAndTestId(studentId,testId).size()>0) {
			return repo.findByStudentIdAndTestId(studentId,testId).get(0);
		}else {
			return null;
		}
	}

	public List<Result> getResultsByTestId(Integer testId) {
		return repo.findByTestId(testId);
	}

//	public Subscription getSubscriptionById(Integer subscriptionId) {
//		String url = "http://localhost:8080/";
//		return restTemplate.getForObject(url, Subscription.class);
//	}
	
}


