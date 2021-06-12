package com.testcreation.students.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Attempt {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	@ManyToMany
	List<Result> resultList = new ArrayList<>();
	
	@ManyToMany
	List<Question> questionList = new ArrayList<>();
	
	Boolean correct;
	
	String attemptString;
	
	public Attempt(Integer resultId,Integer questionId) {
		this.resultList.add(new Result(resultId));
		this.questionList.add(new Question(questionId));
	}
}
