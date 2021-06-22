package com.testcreation.trainer.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable = false)
	private Integer id;
	
	private String name;
	
	@ManyToOne
	Subscription subscription;
	
	@OneToOne
	User user;
	
	Integer testsLeft;
	
	String endServiceDate;
	
	public Student(String name) {
		this.name = name;
	}
	
	public Student(Integer studentId){
		this.id = studentId;
	}
	public void Subscription(Integer subscriptionId){
		this.id = subscriptionId;
	}
}