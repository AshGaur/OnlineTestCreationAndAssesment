package com.testcreation.students.exception;

import org.springframework.stereotype.Component;

@Component
public class StringValidators {
	private final String EMAILREGEX = "([a-z]|[0-9]){2,30}@[a-z]{2,10}\\.(com|org|in)";
	private final String NAMEREGEX = "(([A-Z]|[a-z]){1,5}\\.\\W?)?([A-Z]|[a-z]|\\.{1,5}){2,20}(\\W([A-Z]|[a-z]){1,10})?";
	private final String PHONEREGEX = "[6-9][0-9]{9}";
	private final String PASSWORDREGEX = "([0-9]|[A-Z]|[a-z]){8,15}";
	
	public void validateEmail(String email) {
		if(email == null || !email.matches(EMAILREGEX)) {
			throw new ValidationException("Invalid Email !");
		}
	}
	
	public void validateName(String name) {
		if(name == null || !name.matches(NAMEREGEX)) {
			throw new ValidationException("Invalid Name !");
		}
	}
	
	public void validatePhone(String phone) {
		if(phone==null || !phone.matches(PHONEREGEX)) {
			throw new ValidationException("Invalid Phone Number !");
		}
	}
	
	public void validatePassword(String password) {
		if(password==null || !password.matches(PASSWORDREGEX)) {
			throw new ValidationException("Password is not in valid format !");
		}
	}
}
