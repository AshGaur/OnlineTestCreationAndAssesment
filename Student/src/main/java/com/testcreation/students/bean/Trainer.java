package com.testcreation.students.bean;

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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="trainers")
public class Trainer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable = false)
	private Integer id;
	
	private String name;
	
	private String endServiceDate;
	
	private Integer testsLeft;
	
	public String toString() {
		return String.format("{ \"name\":%s }", this.name);
	}
	
	public Trainer(String name) {
		this.name = name;
	}
	
	@OneToOne
	User user;
	
	public Trainer(Integer id){
		this.id = id;
	}
	@ManyToOne
	private Subscription subscription;
}
