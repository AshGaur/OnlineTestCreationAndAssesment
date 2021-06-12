package com.testcreation.students.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testcreation.students.bean.Attempt;
import com.testcreation.students.bean.Question;
import com.testcreation.students.dao.AttemptRepository;

@Service
public class AttemptService {

	@Autowired
	AttemptRepository repo;
	
	@Autowired
	RestTemplate restTemplate;
	
	public Iterable<Attempt> getAllAttempts(){
		return repo.findAll();
	}
	
	public List<Attempt> getAttemptsByResultId(Integer resultId){
		return repo.findByResultListId(resultId);
	}
	
	public Optional<Attempt> getAttemptById(Integer attemptId){
		return repo.findById(attemptId);
	}
	
	public void addAttempt(Attempt attempt) {
		repo.save(attempt);
	}
	
	public Object getQuestionById(Integer questionId) {
		return restTemplate.getForObject("http://localhost:8082/questions/"+questionId.toString(), Object.class);
//		System.out.println("isEmpty:"+questionOpt.isEmpty());
//		System.out.println("isPresent:"+questionOpt.isPresent());
//		return questionOpt.get();
	}
}
