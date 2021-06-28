package com.testcreation.students.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testcreation.students.bean.Category;
import com.testcreation.students.bean.Trainer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"resultList" })
public class TestDto {

	private Integer id;
	
	private String specialInstructions;
	
	private String fromDateString;
	
	private String toDateString;
	
	private String title;
	
	private Double maxMarks;
	
	private Integer duration;	//minutes
	
	private Trainer trainer;
	
	private Category category;

}
