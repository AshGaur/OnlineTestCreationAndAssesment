package com.testcreation.students.dto;

import lombok.Getter;

@Getter
public class ResultDto {

	private Integer studentId;
	
	private Integer testId;
	
	private Double score = 0.0;
	
	private Integer numberOfAttempts=1;
	
}
