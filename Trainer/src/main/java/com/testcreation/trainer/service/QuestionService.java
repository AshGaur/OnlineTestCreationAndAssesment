package com.testcreation.trainer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testcreation.trainer.bean.Question;
import com.testcreation.trainer.dao.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepo;
	
	public List<Question> getAllQuestionsByTestId(Integer testId) {
		return questionRepo.findByTestId(testId);
	}
	
	public Optional<Question> getQuestionById(Integer questionId) {
		return questionRepo.findById(questionId);
	}
	
	public void addNewQuestion(Question tempQuestion) {
		questionRepo.save(tempQuestion);
	}
	
	public void updateQuestion(Question tempQuestion) {
		questionRepo.save(tempQuestion);
	}
	
	public void deleteQuestionById(Integer questionId) {
		questionRepo.deleteById(questionId);
	}
	
	public void deleteAllQuestionsByTestId(Integer testId) {
		questionRepo.deleteByTestId(testId);
	}
}
