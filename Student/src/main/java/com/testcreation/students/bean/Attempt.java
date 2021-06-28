package com.testcreation.students.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
	Boolean markedForReview;
	
	public Attempt(Integer resultId,Integer questionId,Boolean correct,String attemptString) {
		this.resultList.add(new Result(resultId));
		this.questionList.add(new Question(questionId));
		this.correct = correct;
		this.attemptString = attemptString;
	}
}
