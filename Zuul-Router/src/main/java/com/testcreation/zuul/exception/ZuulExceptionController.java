package com.testcreation.zuul.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ZuulExceptionController {

	@ExceptionHandler(value = BadCredentialsException.class)
	public ResponseEntity<Object> exception(BadCredentialsException e){
		return new ResponseEntity<>("Invalid Username or password !",HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<Object> exception(ValidationException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
