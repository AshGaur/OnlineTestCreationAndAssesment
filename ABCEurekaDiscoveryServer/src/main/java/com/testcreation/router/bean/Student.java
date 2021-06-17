package com.testcreation.router.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Student {
	
	private Integer id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String phone;
	
	Subscription subscription;

	public Student(Integer studentId){
		this.id = studentId;
	}
}