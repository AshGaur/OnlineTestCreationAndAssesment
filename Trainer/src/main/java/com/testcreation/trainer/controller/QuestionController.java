package com.testcreation.trainer.controller;

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

import com.testcreation.trainer.bean.Question;
import com.testcreation.trainer.bean.Test;
import com.testcreation.trainer.exception.TestException;
import com.testcreation.trainer.exception.TrainerException;
import com.testcreation.trainer.graphql.QuestionGraphQLService;
import com.testcreation.trainer.service.QuestionService;
import com.testcreation.trainer.service.TestService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/questions")
public class QuestionController {
			
	@Autowired
	QuestionService questionService;

	@Autowired
	TestService testService;
	
	@Autowired
	QuestionGraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getAllQLAdmins(@RequestBody String query){
		ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(executionResult,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	Iterable<Question> getAllQuestions(){
		return questionService.getAllQuestions();
	}
	
	//Get All Questions by TestID
	@GetMapping("/test/{testId}")
	List<Question> getAllQuestionsByTestId(@PathVariable Integer testId) {
//		if(questionService.getQuestionsByTestId(testId).size()==0)
//			throw new TrainerException("No Questions present for the given TestID !"); 
		return questionService.getQuestionsByTestId(testId);
	}
	
	//Get Question by questionID
	@GetMapping("/{questionId}")
	Optional<Question> getQuestionById(@PathVariable Integer questionId) {
		if(questionService.getQuestionById(questionId).isEmpty())
			throw new TrainerException("Unknown Question ID !");
		return questionService.getQuestionById(questionId);
	}
	
//	@GetMapping("/subCategory/{subCategory}/test/{testId}")
//	public List<Question> getQuestionsBySubCategoryTestId(@PathVariable String subCategory,@PathVariable Integer testId){
//		return questionService.getQuestionsBySubCategoryTestId(subCategory, testId);
//	}

	//Add Question to a test ID
	@PostMapping("/add/test/{testId}")
	void addNewQuestion(@RequestBody Question tempQuestion, @PathVariable Integer testId) {
		Test test = testService.getTestById(testId).isPresent()?testService.getTestById(testId).get():null;
		if(test==null) {
			throw new TestException("Unknown Test ID !");
		}
		tempQuestion.setTest(new Test(testId));
		switch(tempQuestion.getQuestionType()) {
			case "one-answer-type" : {
				tempQuestion.setOption_1(null);
				tempQuestion.setOption_2(null);
				tempQuestion.setOption_3(null);
				tempQuestion.setOption_4(null);
				break;
			}							    
			case "one-correct" :    {
				if(tempQuestion.getOption_1()!=null && tempQuestion.getOption_2()!=null)
					break;
				throw new TrainerException("Atleast two options required !");
			} 
			case "multiple-correct" : {
				if(tempQuestion.getOption_1()!=null && tempQuestion.getOption_2()!=null)
					break;
				  throw new TrainerException("Atleast two options required !");
			}
			default : throw new TrainerException("Unknown Question Type !");
		}
		
		//maxMarks initialization
		double maxMarks = test.getMaxMarks();
		maxMarks+=tempQuestion.getScore();
		test.setMaxMarks(maxMarks);
		questionService.addNewQuestion(tempQuestion);
	}
	
	//Update a question by id
	@PutMapping("/update/{questionId}")
	void updateQuestion(@RequestBody Question tempQuestion, @PathVariable Integer questionId) {
		Question question =  questionService.getQuestionById(questionId).isPresent()?questionService.getQuestionById(questionId).get():null;
		if(question==null)
			throw new TrainerException("Question ID not found !");
		//Updating maxMarks for the test
		 if(tempQuestion.getScore()!=null) {
			 Test test = question.getTest();
			 Double maxMarks = test.getMaxMarks();
			 maxMarks-=question.getScore();
			 maxMarks+=tempQuestion.getScore();
			 test.setMaxMarks(maxMarks);
		 }
		//Update question
		tempQuestion.setId(questionId);
		tempQuestion.setTest(new Test(question.getTest().getId()));
		questionService.updateQuestion(tempQuestion);
	}
	
	//Delete Question by questionId
	@DeleteMapping("/delete/{questionId}")
	void deleteQuestionById(@PathVariable Integer questionId) {
		Question question =  questionService.getQuestionById(questionId).isPresent()?questionService.getQuestionById(questionId).get():null;
		if(question==null)
			throw new TrainerException("Question ID not found !");
		//Max marks update in test
		Test test = question.getTest();
		test.setMaxMarks(test.getMaxMarks()-question.getScore());
		testService.updateTest(test);
		//saving the question
		questionService.deleteQuestionById(questionId);
	}
	
	//Delete All Questions by TestId
	@DeleteMapping("/delete/test/{testId}")
	void deleteAllQuestionsFromTestId(@PathVariable Integer testId) {
		Test test = testService.getTestById(testId).isPresent()?testService.getTestById(testId).get():null;
		if(test==null) {
			throw new TestException("Unknown test Id !");
		}
		if(questionService.getQuestionsByTestId(testId).size()==0)
			throw new TrainerException("No Questions Present across testId to delete !");
		test.setMaxMarks(0.0);
		questionService.deleteAllQuestionsByTestId(testId);
	}
}
