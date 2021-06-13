package com.testcreation.students.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AttemptDto {

	Integer resultId;
	
	Integer questionId;
	
	String attemptString;
}
