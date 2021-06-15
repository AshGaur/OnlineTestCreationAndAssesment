package com.testcreation.router.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Test {

	private Integer id;
	
	private String specialInstructions;
	
	private String fromDateString;
	
	private String toDateString;
	
	private String title;
	
	private Double maxMarks;
	
	private Integer duration;	//minutes
	
	private Trainer trainer;
	
	private Category category;
	
	public Test(Integer testId) {
		this.id = testId;
	}
}

	
	
	
	