package com.testcreation.router.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attempt {

	Integer id;
	
	List<Result> resultList = new ArrayList<>();
	
	List<Question> questionList = new ArrayList<>();
	
	Boolean correct;
	
	String attemptString;
	
	public Attempt(Integer resultId,Integer questionId,Boolean correct,String attemptString) {
		this.resultList.add(new Result(resultId));
		this.questionList.add(new Question(questionId));
		this.correct = correct;
		this.attemptString = attemptString;
	}
}
