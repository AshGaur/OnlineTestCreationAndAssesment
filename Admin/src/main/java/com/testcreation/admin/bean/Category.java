package com.testcreation.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity(name="categories")
public class Category {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(updatable = false)
    Integer id;
	
	@Column(nullable=false,unique=true)
	String categoryName;
	
	
	
}