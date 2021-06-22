package com.testcreation.zuul.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	String name;
	
	String role;
	
	String email;
	
	String password;
	
	Integer subscriptionId;
	
	String phone;
	
}
