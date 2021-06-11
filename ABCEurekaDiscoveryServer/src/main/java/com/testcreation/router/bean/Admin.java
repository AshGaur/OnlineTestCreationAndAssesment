package com.testcreation.router.bean;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@Entity(name="admins")
public class Admin {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(updatable = false)
//	Integer id;
	
//	@Column(unique=true,nullable=false)
	String email;
	
//	@Column(nullable=false)
	String password;
}