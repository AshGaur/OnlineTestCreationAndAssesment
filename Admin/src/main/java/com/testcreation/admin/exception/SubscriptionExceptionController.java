package com.testcreation.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class SubscriptionExceptionController {
	
	
	    // this will handle all custom exceptions and will return message related to particular exception
			@ExceptionHandler(value = SubscriptionException.class)
			public ResponseEntity<Object> exception(SubscriptionException theException){
				return new ResponseEntity<>(theException.getMessage(),HttpStatus.BAD_REQUEST);
			}
}
