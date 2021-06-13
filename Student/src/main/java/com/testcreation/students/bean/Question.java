package com.testcreation.students.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="questions")
public class Question {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private	Integer id;
	
	@Column(nullable=false)
	private String questionString;
	
	@Column(nullable=false)
	private String answerString;
	
	private	String questionType;
	
	@Column(nullable=false)
	private String subCategory = "";
	
	@Column(nullable=false)
	private Double score;
	
	private String option_1;
	private String option_2;
	private String option_3;
	private	String option_4;
	private double negativeMarking=0;
	
	@ManyToOne
	private Test test;
	
	public Question(Integer questionId) {
		this.id = questionId;
	}
	
}
