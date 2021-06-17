package com.testcreation.router.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subscription {

	private Integer id;

	private String code;
	
	private String  role;
	
	private String description;
	
	private Integer testNumberLimit=0;	//tests
	private Integer testAvailability=0;		//days
	private Integer serviceUsageLimit=0; 	//days
	
	private Double price=0.0;

	public Subscription(Integer id) {
		this.id = id;
	}

	public Subscription(int id, String code, String description) {
		this.id = id;
		this.code = code;
		this.description = description;
	}
	
}
