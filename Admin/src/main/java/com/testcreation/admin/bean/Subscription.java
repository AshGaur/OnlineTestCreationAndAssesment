package com.testcreation.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="subscriptions")
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false)
	private String code;
	
	@Column(nullable=false)
	private String  role;
	
	@Column(nullable=false)
	private String description;
	
	private Integer testNumberLimit=0;	//tests
	private Integer testAvailability=0;		//days
	private Integer serviceUsageLimit=0; 	//days
	
	@Column(nullable=false)
	private Double price=0.0;

	public Subscription(Integer id) {
		this.id = id;
	}
	
}
