package com.testcreation.students.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonIgnoreProperties({"correct","optionA","optionB","optionC","optionD" })
public class AttemptDto {

	Integer resultId;
	
	Integer questionId;
	
	String attemptString;
	
	Boolean markedForReview;
}
