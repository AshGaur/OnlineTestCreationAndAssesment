package com.testcreation.students.dto;

import com.fasterxml.jackson.databind.JsonNode;

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
public class QuestionDto {
	
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
	
	private JsonNode test;
	
}
