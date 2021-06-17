package com.testcreation.router.bean;

import lombok.AllArgsConstructor;

//import javax.persistence.Entity;
//import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

//@Entity(name="categories")
public class Category {

//	@Id
	String categoryName;
	
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
}