package com.testcreation.zuul.bean;

public class Trainer implements User{

	Integer id;
	
	String email;
	
	String password;
	
	String phone;
	
	String roles = "ROLE_TRAINER";
	
	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getPhone() {
		return this.phone;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getRoles() {
		return this.roles;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
