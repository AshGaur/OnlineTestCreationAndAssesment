package com.testcreation.zuul.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

	private Integer userId;
	private String jwt;
	private String name;
	private String role;
}
