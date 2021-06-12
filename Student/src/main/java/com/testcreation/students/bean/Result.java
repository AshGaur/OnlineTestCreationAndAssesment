package com.testcreation.students.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name="results")
public class Result {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable = false)
	private Integer id;
	
	@Column(nullable = false)
	private Double score=0.0;
	private Integer numberOfAttempts=1;
	
	@OneToOne
	private Student student;

	@OneToOne
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
