package com.testcreation.zuul.bean;

public interface User {
	Integer getId();

	String getEmail();

	String getPhone();
	
	String getPassword();

	String getRoles();

	boolean isActive();
}
