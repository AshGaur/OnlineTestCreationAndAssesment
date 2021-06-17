package com.testcreation.router.bean;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Result {
	
	private Integer id;
	
	private Double score=0.0;
	private Integer numberOfAttempts=1;
	
	private Boolean completed=false;
	
	private String endString;
	
	private Student student;

	private Test test;
	
	public Result(Integer resultId) {
		this.id = resultId;
	}
	
	public Result(Double score,Integer numberOfAttempts,Integer studentId,Integer testId) {
		this.score = score;
		this.numberOfAttempts = numberOfAttempts;
		this.student = new Student(studentId);
		this.test = new Test(testId);
	}
}
