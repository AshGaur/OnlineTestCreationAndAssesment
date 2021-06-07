package com.testcreation.admin.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class AdminExceptionController {

	@ExceptionHandler(value =  AdminException.class)
	public ResponseEntity<Object> exception( AdminException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Object> exception( DataIntegrityViolationException e){
		return new ResponseEntity<>("Constraint not followed :"+e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<Object> exception( ValidationException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}


