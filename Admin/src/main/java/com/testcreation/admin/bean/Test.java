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

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name="tests")
public class Test {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable = false)
	private Integer id;
	
	private String specialInstructions;
	
	@Column(nullable = false)
	private String fromDateString;
	
	@Column(nullable = false)
	private String toDateString;
	
	@Column(nullable = false)
	private String title;
	
	private Double maxMarks;
	
	@Column(nullable = false)
	private Integer duration;	//minutes
	
	@ManyToOne
	private Trainer trainer;
	
	@ManyToOne
	private Category category;
	
	public Test(Integer testId) {
		this.id = testId;
	}
}

	
	
	
	