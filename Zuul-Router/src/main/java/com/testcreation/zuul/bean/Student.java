package com.testcreation.zuul.bean;

public class Student implements User{

	Integer id;
	
	String email;
	
	String password;
	
	String phone;
	
	String roles = "ROLE_STUDENT";
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", email=" + email + ", password=" + password + ", phone=" + phone + ", roles="
				+ roles + "]";
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
