package com.testcreation.router.controller;

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

import com.testcreation.router.service.QuestionRouterService;
//import com.testcreation.trainer.bean.Question;
//import com.testcreation.trainer.bean.Test;
//import com.testcreation.trainer.exception.TrainerException;
//import com.testcreation.trainer.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionRouterController {
			
	@Autowired
	QuestionRouterService questionService;

	//Get All Questions by TestID
	@GetMapping("/test/{testId}")
	List<Object> getAllQuestionsByTestId(@PathVariable Integer testId) {
		return questionService.getAllQuestionsByTestId(testId);
	}
	
	//Get Question by questionID
	@GetMapping("/{questionId}")
	Object getQuestionById(@PathVariable Integer questionId) {
		return questionService.getQuestionById(questionId);
	}
	
	//Add Question to a test ID
//	@PostMapping("/add/test/{testId}")
//	void addNewQuestion(@RequestBody Question tempQuestion, @PathVariable Integer testId) {
//		tempQuestion.setTest(new Test(testId));
//		switch(tempQuestion.getQuestionType()) {
//			case "one-answer-type" : {
//				tempQuestion.setOption_1(null);
//				tempQuestion.setOption_2(null);
//				tempQuestion.setOption_3(null);
//				tempQuestion.setOption_4(null);
//				break;
//			}							    
//			case "one-correct" :    {
//				if(tempQuestion.getOption_1()!=null && tempQuestion.getOption_2()!=null)
//					break;
//				throw new TrainerException("Atleast two options required !");
//			} 
//			case "multiple-correct" : {
//				if(tempQuestion.getOption_1()!=null && tempQuestion.getOption_2()!=null)
//					break;
//				  throw new TrainerException("Atleast two options required !");
//			}
//			default : throw new TrainerException("Unknown Question Type !");
//		}
//		questionService.addNewQuestion(tempQuestion);
//	}
//	
//	//Update a question by id
//	@PutMapping("/update/{questionId}")
//	void updateQuestion(@RequestBody Question tempQuestion, @PathVariable Integer questionId) {
//		 if(questionService.getQuestionById(questionId).isEmpty())
//			throw new TrainerException("Question ID not found !"); 
//		questionService.updateQuestion(tempQuestion);
//	}
//	
//	//Delete Question by questionId
//	@DeleteMapping("/delete/{questionId}")
//	void deleteQuestionById(@PathVariable Integer questionId) {
//		if(questionService.getQuestionById(questionId).isEmpty())
//			throw new TrainerException("Question ID not found !");
//		questionService.deleteQuestionById(questionId);
//	}
//	
//	//Delete All Questions by Id
//	@DeleteMapping("/delete/test/{testId}")
//	void deleteAllQuestionsFromTestId(@PathVariable Integer testId) {
//		if(questionService.getAllQuestionsByTestId(testId).size()==0)
//			throw new TrainerException("No Questions Present across testId to delete !");
//		questionService.deleteAllQuestionsByTestId(testId);
//	}
}
