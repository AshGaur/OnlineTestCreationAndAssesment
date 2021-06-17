package com.testcreation.router.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Question {

	private	Integer id;
	
	private String questionString;
	
	private String answerString;
	
	private	String questionType;
	
	private String subCategory = "";
	
	private Double score;
	
	private String option_1;
	private String option_2;
	private String option_3;
	private	String option_4;
	private double negativeMarking=0;
	
	private Test test;
	
	public Question(Integer questionId) {
		this.id = questionId;
	}
	
}
