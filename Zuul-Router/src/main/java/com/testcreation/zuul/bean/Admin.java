package com.testcreation.zuul.bean;

public class Admin implements User{

	Integer id;
	
	String email;
	
	String password;
	
	String roles = "ROLE_ADMIN";
	
	@Override
	public boolean isActive() {
		return true;
	}

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
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getRoles() {
		return this.roles;
	}
	
}
