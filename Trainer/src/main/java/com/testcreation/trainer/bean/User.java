package com.testcreation.trainer.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name="users")
public class User {

	@Id
	String email;
	
	@Column(unique=true)
	private String phone;
	
	@Column(nullable=false)
	String password;
	
	@Column(nullable=false)
	String roles;
	
	boolean active;
	
	public User(String email) {
		this.email = email;
	}
}
