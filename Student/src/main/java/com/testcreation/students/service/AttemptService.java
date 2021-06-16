package com.testcreation.students.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcreation.students.bean.Attempt;
import com.testcreation.students.bean.Question;
import com.testcreation.students.bean.Result;
import com.testcreation.students.dao.AttemptRepository;
import com.testcreation.students.dao.ResultRepository;
import com.testcreation.students.dto.QuestionDto;

@Service
public class AttemptService {

	@Autowired
	AttemptRepository repo;
	
	@Autowired
	ResultRepository resultRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public Iterable<Attempt> getAllAttempts(){
		return repo.findAll();
	}
	
	public List<Attempt> getAttemptsByResultIdAndQuestionId(Integer resultId,Integer questionId){
		return repo.findByResultListIdAndQuestionListId(resultId,questionId);
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
	
	public QuestionDto getQuestionById(Integer questionId) throws JsonMappingException, JsonProcessingException {
		String respString = restTemplate.getForObject("http://localhost:8082/questions/"+questionId.toString(), String.class);
		if(respString.equals("Unknown Question ID !")) {
			return null;
		}
		return objectMapper.readValue(respString, QuestionDto.class);
	}
	
	public Optional<Result> getResultById(Integer resultId){
		return resultRepo.findById(resultId);
	}
}
