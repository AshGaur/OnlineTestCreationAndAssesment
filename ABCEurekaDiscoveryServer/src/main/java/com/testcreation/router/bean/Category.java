package com.testcreation.router.bean;

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
	
	public Category(int i,String categoryName) {
		//this.categoryName = categoryName;
	}
	
}