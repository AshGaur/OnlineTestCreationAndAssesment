package com.testcreation.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	
	@Column(unique=true)
	private String email;
	
	private String password;
	
	@Column(unique=true)
	private String phone;
	
	private String endServiceDate;
	
	private Integer testsLeft;
	
	public Trainer(Integer id){
		this.id = id;
	}
	@ManyToOne
	private Subscription subscription;
}
